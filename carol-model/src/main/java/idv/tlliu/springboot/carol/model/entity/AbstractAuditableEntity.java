package idv.tlliu.springboot.carol.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditableEntity implements Serializable{
//  @Id
//  @Column(name="id")
//  private String entity_id;
  
  @Column(name = "created_user", nullable = false)
  @CreatedBy
  private String createdUser;

  @Column(name = "updated_user", nullable = false)
  @LastModifiedBy
  private String updatedUser;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_time", nullable = false)
  @CreatedDate
  private ZonedDateTime createdTime;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_time", nullable = false)
  @LastModifiedDate
  private ZonedDateTime updatedTime;
  

  public String getCreatedUser() {
    return this.createdUser;
    
  }
  public String getUpdatedUser() {
    return this.updatedUser;
    
  }
  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  public ZonedDateTime getCreatedTime() {
    return this.createdTime;
  }
  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  public ZonedDateTime getUpdatedTime() {
    return this.updatedTime;
  }
  public void setCreatedUser(String createdUser) {
    this.createdUser = createdUser;
  }
  public void setUpdatedUser(String updatedUser) {
    this.updatedUser = updatedUser;
  }
  public void setCreatedTime(ZonedDateTime createdTime) {
    this.createdTime = createdTime;
  }
  public void setUpdatedTime(ZonedDateTime updatedTime) {
    this.updatedTime = updatedTime;
  }
  
  
}