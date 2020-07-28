package ECoffee.entities;

import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "SimpleUser")
public class SimpleUser extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;
    @Column(name = "CreatedAt")
    private DateTimeFormat CreatedAt;
    @Column(name = "UpdatedAt")
    private DateTimeFormat UpdatedAt;

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

    public DateTimeFormat getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(DateTimeFormat createdAt) {
        CreatedAt = createdAt;
    }

    public DateTimeFormat getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(DateTimeFormat updatedAt) {
        UpdatedAt = updatedAt;
    }
}

