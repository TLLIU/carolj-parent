package idv.tlliu.springboot.carol.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")  //表示开启正向工程,运行后会自动常见roles这个表
public class Roles {
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)  //自增长
  @Column(name="id")
  private Integer id;
  @Column(name="ROLE_NAME")
  private String roleName;
  
  @OneToOne(mappedBy="roles")
  private User user;
  
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }
  public Integer getId() {
    return id;
  }
  public void setRoleId(Integer id) {
    this.id = id;
  }
  public String getRoleName() {
    return roleName;
  }
  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }
  @Override
  public String toString() {
    return "Roles [roleId=" + id + ", roleName=" + roleName + "]";
  }
  public Roles(Integer roleId, String roleName) {
    super();
    this.id = id;
    this.roleName = roleName;
  }
  public Roles() {
    super();
  }
  
  
  
}