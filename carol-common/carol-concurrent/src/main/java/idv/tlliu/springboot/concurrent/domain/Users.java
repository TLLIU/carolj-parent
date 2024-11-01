package idv.tlliu.springboot.concurrent.domain;

public class Users {

    private Long id;
    private String firstname;
    private String lastname;
    public Long getId() {
      return id;
    }
    public void setId(Long id) {
      this.id = id;
    }
    public String getFirstname() {
      return firstname;
    }
    public void setFirstname(String firstname) {
      this.firstname = firstname;
    }
    public String getLastname() {
      return lastname;
    }
    public void setLastname(String lastname) {
      this.lastname = lastname;
    }
    public Users(Long id, String firstname, String lastname) {
      super();
      this.id = id;
      this.firstname = firstname;
      this.lastname = lastname;
    }
    public Users() {
      super();

    }    
    
}