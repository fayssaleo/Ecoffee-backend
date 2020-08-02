package ECoffee.entities;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Set;


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
    @Transient
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "confirmation_token")
    private String confirmationToken;

    //join
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UsersRoom", joinColumns = {@JoinColumn(name = "user", referencedColumnName = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "usersRoom_id", referencedColumnName = "usersRoom_id")})
    private Set<UsersRoom> Ur;

    //constructor
    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.confirmationToken = user.confirmationToken;
    }

    //getters & setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //implementation of UserDetails methodes
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}