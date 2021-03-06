package ECoffee.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name = "user")
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = "Please provide your username")
    private String username;

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "Please provide your Email")
    private String email;


    private String password;
    @Column(name = "enabled")
    private boolean enabled;


    @Column(name = "firstname")
    @NotEmpty(message = "Please provide your First name")
    private String firstname;

    @Column(name = "lastname")
    @NotEmpty(message = "Please provide your last name")
    private String lastname;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdat")
    private Date CreatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedat")
    private Date UpdatedAt;

    //join
    @OneToMany(mappedBy = "room")
    private Set<UsersRoom> rooms = new HashSet<UsersRoom>();
    //join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;



    public void setUsername(String username) {
        this.username = username;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //constructor
    public User() {
    }

    public User(Integer id, @NotEmpty(message = "Please provide your username") String username, @NotEmpty(message = "Please provide your Email") String email, String password, String firstname, String lastname, String country, String city, Date birthday, boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.firstname = firstname;
        this.lastname = lastname;
        this.country = country;
        this.city = city;
        this.birthday = birthday;

    }

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    //getters & setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    //implementation of UserDetails methodes
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList(new SimpleGrantedAuthority(role.getName().toUpperCase()));

    }

    @JsonProperty("role")
    public String getStringAuthorities() {
        return "ROLE_"+role.getName().toUpperCase();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname=" + firstname +
                ", lastname=" + lastname +
                ", birthday=" + birthday +
                ", country=" + country +
                ", city=" + city +
                ", enabled=" + enabled +

                ", accessToken='" + accessToken + '\'' +
                ", confirmationToken='" + confirmationToken + '\'' +
                ", CreatedAt=" + CreatedAt +
                ", UpdatedAt=" + UpdatedAt +
                ", rooms=" + rooms +
                ((role != null)? ", role=" + role.getName().toUpperCase() : "role=null") +
                '}';
    }

    public void setUdateAncCreatedAt(){
        this.CreatedAt=new Date();
        this.UpdatedAt=new Date();
    }
}