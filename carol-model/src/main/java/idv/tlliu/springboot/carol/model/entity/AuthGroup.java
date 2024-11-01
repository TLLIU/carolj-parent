package idv.tlliu.springboot.carol.model.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="AUTH_GROUP")
public class AuthGroup {
    @Id
    @Column(name="GROUP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigDecimal id;
    
    public BigDecimal getId() {
        return id;
    }


    public void setId(BigDecimal id) {
        this.id = id;
    }


    @Column(name="NAME")
    private String name;
    
    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @ManyToMany(mappedBy ="groups", fetch=FetchType.LAZY)
    //@JoinTable(name="USER_AUTHGROUP", joinColumns={@JoinColumn(name="GROUP_ID")}, inverseJoinColumns={@JoinColumn(name="USER_ID")})
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }


    public void setUsers(List<User> users) {
        this.users = users;
    }

    
}
