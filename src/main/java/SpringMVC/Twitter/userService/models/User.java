/*
This is a User object. It has 5 properties
1) id          <long>      unique identifier of user
2) firstname   <String>    first name of user
3) lastname    <String>    last name of user
4) email       <String>    email of user
5) date        <Date>      date when user was created
 */
package SpringMVC.Twitter.userService.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @NotNull
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "first_name")
    private String firstname;

    @NotNull
    @Column(name = "last_name")
    private String lastname;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column(name = "date_created")
    private Date date;

    public User() { }

    public User(String userId, String firstname, String lastname, String email) {
        this.setId(userId);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setDate();
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

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }
}
