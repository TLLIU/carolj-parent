package idv.tlliu.springboot.carol.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CAROL_USER_PROFILE")
public class UserProfile implements Serializable{
  
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="id")
  private BigDecimal id;
  
  @Column(name="ALIAS")
  private String alias; 
  
  //@OneToOne
  //private User user;

  public UserProfile() {
    
  }
  
  public UserProfile(BigDecimal id, String alias) {
    super();
    this.id = id;
    this.alias = alias;
  }

  public BigDecimal getId() {
    return id;
  }

  public void setId(BigDecimal id) {
    this.id = id;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

//  public User getUser() {
//    return user;
//  }
//
//  public void setUser(User user) {
//    this.user = user;
//  }

}
