package idv.tlliu.springboot.carol.model.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

/**
 * The Class User.
 */
@Entity
//@Table(name="CAROL_USER") //User is reserved keyword in h2 so we change table name here. 
public class User extends AbstractAuditableEntity implements Serializable{

  /** The id. */
  @Id
  @Column(name = "USER_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  /** The user name. */
  @Column(name="USER_NAME")
  @NotNull
  private String userName;

  /** The password. */
  private String password;

  /** The first name. */
  private String firstName;

  /** The last name. */
  private String lastName;

  /** The email. */
  private String email;
  
  /** The phone number*/
  @Column(name="PHONE_NUMBER")
  private String phoneNumber;
  
  @OneToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="id")
  private UserProfile userProfile;

  /** The role type id. */
  private Integer role;
  
  // @ManyToOne(optional=false)
  // @JoinColumn(name="COUNTRY")
  @Column(name = "COUNTRY")
  private Integer country;
  
  // @ManyToMany(mappedBy ="groups", fetch=FetchType.LAZY)
  @ManyToMany
  @JoinTable(name = "USER_AUTHGROUP", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
      @JoinColumn(name = "GROUP_ID") })
  private List<AuthGroup> groups;
  /**
   * Instantiates a new user.
   */
  public User() {
  }

  /**
   * Instantiates a new user.
   *
   * @param userName  the user name
   * @param password  the password
   * @param firstName the first name
   * @param lastName  the last name
   * @param email     the email
   */
  public User(String userName, String password, String firstName, String lastName, String email, 
      String phoneNumber, Integer role, int countryId) {

    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.country = countryId;

  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long getId() {
    return id;
  }

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets the user name.
   *
   * @return the user name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets the user name.
   *
   * @param userName the new user name
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   *
   * @param firstName the new first name
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName the new last name
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the email.
   *
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email.
   *
   * @param email the new email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the role.
   *
   * @return the role
   */
  public Integer getRole() {
    return role;
  }

  /**
   * Sets the role.
   *
   * @param role the new role
   */
  public void setRole(Integer role) {
    this.role = role;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Integer getCountry() {
    return country;
  }

  public void setCountry(Integer country) {
    this.country = country;
  }

  public List<AuthGroup> getGroups() {
    return groups;
  }

  public void setGroups(List<AuthGroup> groups) {
    this.groups = groups;
  }

}
