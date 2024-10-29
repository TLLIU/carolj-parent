package idv.tlliu.springboot.security.configure;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
  
  /**
   * Spring Security 过滤器链，用于配置协议端点
   * @param http /
   * @return /
   * @throws Exception /
   */
  @Bean 
  @Order(1)
  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
          throws Exception {
      OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
      http
          // Redirect to the login page when not authenticated from the
          // authorization endpoint
          .exceptionHandling((exceptions) -> exceptions
              .authenticationEntryPoint(
                  new LoginUrlAuthenticationEntryPoint("/login"))
          );

      return http.build();
  }

  /**
   * Spring Security 过滤器链，用于身份验证
   * @param http /
   * @return /
   * @throws Exception /
   */
  @Bean 
  @Order(2)
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
          throws Exception {
//    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
//        new OAuth2AuthorizationServerConfigurer();
//
//    http.apply(authorizationServerConfigurer);

    return http
          .authorizeHttpRequests((authorize) -> authorize
              .anyRequest().authenticated()
          )
//          .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
          //.csrf(CsrfConfigurer::disable)
          // Form login handles the redirect to the login page from the
          // authorization server filter chain
          .formLogin(Customizer.withDefaults())
          .build();

  }

  /**
   * UserDetailsService 实例，用于检索要进行身份验证的用户
   * @return /
   */
  @Bean 
  public UserDetailsService userDetailsService() {
    //创建一个 user/password 用户信息用于测试验证
      UserDetails userDetails = User.withDefaultPasswordEncoder()
              .username("user")
              .password("password")
              .roles("USER")
              .build();

      return new InMemoryUserDetailsManager(userDetails);
  }

  /**
   * RegisteredClientRepository 实例，用于管理客户端
   * @return /
   */
  @Bean 
  public RegisteredClientRepository registeredClientRepository() {
      RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
              .clientId("messaging-client")
              .clientSecret("{noop}secret")
              .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
              .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
              .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
              .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
              .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
              .redirectUri("http://127.0.0.1:8080/authorized")
              .scope(OidcScopes.OPENID)
              .scope("message.read")
              .scope("message.write")
              .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
              .build();

      return new InMemoryRegisteredClientRepository(registeredClient);
  }

  /**
   * com.nimbusds.jose.jwk.source.JWKSource 实例，用于签署访问令牌
   * @return /
   */
  @Bean 
  public JWKSource<SecurityContext> jwkSource() {
      KeyPair keyPair = generateRsaKey();
      RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
      RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
      RSAKey rsaKey = new RSAKey.Builder(publicKey)
              .privateKey(privateKey)
              .keyID(UUID.randomUUID().toString())
              .build();
      JWKSet jwkSet = new JWKSet(rsaKey);
      return new ImmutableJWKSet<>(jwkSet);
  }

  /**
   * java.security.KeyPair 实例，加载 JWT 公钥和私钥
   * @return /
   */
  private static KeyPair generateRsaKey() { 
      KeyPair keyPair;
      try {
          KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
          keyPairGenerator.initialize(2048);
          keyPair = keyPairGenerator.generateKeyPair();
      }
      catch (Exception ex) {
          throw new IllegalStateException(ex);
      }
      return keyPair;
  }
  
  @Bean 
  public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
      return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
  }


  /**
   * （REQUIRED）包含OAuth2授权服务器的配置设置。它指定协议端点的URI以及 issuer 标识符。
   * @return /
   */
  @Bean 
  public AuthorizationServerSettings provideSettings() {
      return AuthorizationServerSettings.builder()
          .issuer("https://example.com")
          .authorizationEndpoint("/oauth2/v1/authorize")
          .tokenEndpoint("/oauth2/v1/token")
          .tokenIntrospectionEndpoint("/oauth2/v1/introspect")
          .tokenRevocationEndpoint("/oath2/v1/revoke")
          .jwkSetEndpoint("/oauth2/v1/jwks")
          .oidcUserInfoEndpoint("/connect/v1/userinfo")
          .oidcClientRegistrationEndpoint("/connect/v1/register")
          .build();
  }
  
//  @Bean
//  public TokenEndpoint tokenEndpoint() {
//    
//  }

//
//  @Bean
//  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//      OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
//          new OAuth2AuthorizationServerConfigurer<>();
//      http.apply(authorizationServerConfigurer);
//
//      authorizationServerConfigurer
//          // 用于管理新的和现有的客户端。
//          .registeredClientRepository(registeredClientRepository) 
//          // 用于管理新的和现有的授权。
//          .authorizationService(authorizationService) 
//          // 用于管理新的和现有的授权许可。
//          .authorizationConsentService(authorizationConsentService)
//          // 用于自定义 OAuth2 授权服务器的配置设置。
//          .providerSettings(providerSettings) 
//          // 用于生成 OAuth2 授权服务器支持的令牌。
//          .tokenGenerator(tokenGenerator) 
//          // OAuth2 客户端身份认证的配置器。
//          .clientAuthentication(clientAuthentication -> { })  
//          // OAuth2 授权端点的配置器。
//          .authorizationEndpoint(authorizationEndpoint -> { })    
//          // OAuth2 token 端点的配置器。
//          .tokenEndpoint(tokenEndpoint -> { })    
//          // OAuth2 token 内省端点的配置器。
//          .tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> { })  
//          // OAuth2 token 撤销端点的配置器。
//          .tokenRevocationEndpoint(tokenRevocationEndpoint -> { })    
//          .oidc(oidc -> oidc
//               // OpenID Connect 1.0 UserInfo 端点的配置器。
//              .userInfoEndpoint(userInfoEndpoint -> { })  
//               // OpenID Connect 1.0 客户端注册端点的配置器。
//              .clientRegistrationEndpoint(clientRegistrationEndpoint -> { })  
//          );
//
//      return http.build();
//  }
  
//  @Bean
//  public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//      OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer =
//          new OAuth2AuthorizationServerConfigurer<>();
//      http.apply(authorizationServerConfigurer);
//
//      authorizationServerConfigurer
//          .clientAuthentication(clientAuthentication ->
//              clientAuthentication
//                  // 尝试从 HttpServletRequest 提取客户端凭证到 OAuth2ClientAuthenticationToken 实例时使用的 AuthenticationConverter(预处理器)。
//                  .authenticationConverter(authenticationConverter)   
//                  // 用于对 OAuth2ClientAuthenticationToken 进行身份验证的 AuthenticationProvider(主处理器)。(可以添加一个或多个替换默认值。)
//                  .authenticationProvider(authenticationProvider) 
//                  // AuthenticationSuccessHandler(后处理器)用于处理成功的客户端身份验证并将 OAuth2ClientAuthenticationToken 关联到SecurityContext。
//                  .authenticationSuccessHandler(authenticationSuccessHandler) 
//                  // AuthenticationFailureHandler(后处理器)，用于处理客户端身份验证失败并返回 OAuth2Error 响应。
//                  .errorResponseHandler(errorResponseHandler) 
//          );
//
//      return http.build();
//  }
  
}
