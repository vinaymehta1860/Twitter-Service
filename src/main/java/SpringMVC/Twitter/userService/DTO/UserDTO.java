package SpringMVC.Twitter.userService.DTO;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {
    private String id;
    private String firstname;
    private String lastname;
    private String email;

    public UserDTO() {}

    public UserDTO(String id, String firstname, String lastname, String email) {
        this.setId(id);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}