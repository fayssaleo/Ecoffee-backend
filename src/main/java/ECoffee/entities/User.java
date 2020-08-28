package ECoffee.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "User")
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = "Please provide your first name")
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

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt")
    private Date CreatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedAt")
    private Date UpdatedAt;

    //join
    @OneToMany(mappedBy = "room")
    private Set<UsersRoom> rooms = new HashSet<UsersRoom>();
    //join
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> roles;

    //constructor
    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();

    }

    private String getEmail() {
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
        return roles.stream().map(role-> new SimpleGrantedAuthority(
                "ROLE_"+role.getName().toUpperCase()
        )).collect(Collectors.toList());
    }

    @JsonProperty("authorities")
    public Collection<String> getStringAuthorities() {
        return roles.stream().map(role-> "ROLE_"+role.getName().toUpperCase()).collect(Collectors.toList());
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
                ", enabled=" + enabled +
                ", accessToken='" + accessToken + '\'' +
                ", confirmationToken='" + confirmationToken + '\'' +
                ", CreatedAt=" + CreatedAt +
                ", UpdatedAt=" + UpdatedAt +
                ", rooms=" + rooms +
                ", roles=" + roles +
                '}';
    }

    public void setUdateAncCreatedAt(){
        this.CreatedAt=new Date();
        this.UpdatedAt=new Date();
    }
}