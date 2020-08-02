package ECoffee.entities;

import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SimpleUser")
public class SimpleUser extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedAt")
    private Date CreatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UpdatedAt")
    private Date UpdatedAt;

    //constructor
    public SimpleUser() {
        super();
    }

    public SimpleUser(SimpleUser user){
        super(user);
        this.CreatedAt = user.getCreatedAt();
        this.UpdatedAt = user.getUpdatedAt();
    }

    //getters & setters

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        UpdatedAt = updatedAt;
    }
}

