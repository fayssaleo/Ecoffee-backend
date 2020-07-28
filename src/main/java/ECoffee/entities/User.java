package ECoffee.entities;

import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;


@Entity
@Table(name = "user")
public class User  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "UserName")
    @NotEmpty(message = "Please provide your first name")
    private String UserName;
    @org.springframework.data.annotation.Transient
    private String password;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "confirmation_token")
    private String confirmationToken;


    //constructor
    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.UserName = user.getUsername();
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
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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