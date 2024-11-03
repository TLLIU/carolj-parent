package idv.tlliu.springboot.carol.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.tlliu.springboot.carol.model.entity.User;
import idv.tlliu.springboot.carol.model.entity.UserProfile;

@DataJpaTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:carol_model_testdb;NON_KEYWORDS=user",
    "spring.jpa.hibernate.ddl-auto=create-drop"
}, showSql = true, 
    includeFilters=@ComponentScan.Filter(
        type=FilterType.ASSIGNABLE_TYPE,
        classes = UserRepository.class))
@Transactional(propagation =Propagation.NOT_SUPPORTED) // Prohibit transaction rollback in unit test cases
public class UserRepositoryTest {
  
  @Autowired
  private UserRepository userRepository;
  
  private User testUser;
  
  @BeforeEach
  public void setup() {
    UserProfile testProfile = new UserProfile();
    testProfile.setAlias("alias_test1");
    testUser = new User("userName_test1", "password_test1", "firstName_test1", "lastName_test1", "test1@email.com", 
        "12345678", 1, 1, testProfile);
    
    userRepository.save(testUser);
//    testUser.set
  }
  
  //测试插入操作，检查输出的user
  @Test
  void givenUser_whenSaved_thenCanBeFoundById() {
    User savedUser = userRepository.findById(testUser.getId()).orElse(null);
    assertNotNull(savedUser);
    assertEquals(testUser.getUserName(), savedUser.getUserName());
    assertEquals(testUser.getPassword(), savedUser.getPassword());
    System.out.println("Value of saved User Profile: " + savedUser.getUserProfile());
    System.out.println("Value of saved User Profile: " + savedUser.getUserProfile().getId());
    System.out.println("Value of saved User Profile: " + savedUser.getUserProfile().getAlias());
  }
  
  //测试更新操作
  @Test
  void givenUser_whenUpdated_thenCanBeFoundByIdWithUpdatedData() {
      testUser.setUserName("updatedUsername_test1");
      userRepository.save(testUser);

      User updatedUser = userRepository.findById(testUser.getId()).orElse(null);

      assertNotNull(updatedUser);
      assertEquals("updatedUsername_test1", updatedUser.getUserName());
  }  
  
  //测试 findByUsername() 方法
  @Test
  void givenUser_whenFindByUsernameCalled_thenUserIsFound() {
    PageRequest pageRequest = PageRequest.of(0, 20);
    List<User> foundUser = userRepository.findByUserNameIn(List.of("userName_test1", "testUser"), pageRequest);

    assertNotNull(foundUser);
    Assertions.assertEquals(1, foundUser.size());
    assertEquals("userName_test1", foundUser.get(0).getUserName());
  }
  
  @AfterEach
  public void tearDown() {
    userRepository.delete(testUser);
    
  }

}
