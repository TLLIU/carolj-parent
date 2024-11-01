package idv.tlliu.springboot.carol.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Department extends AbstractAuditableEntity{
  @Id
  public long id;
  public String name;
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  
  
}
