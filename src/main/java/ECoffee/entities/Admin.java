package ECoffee.entities;

import javax.persistence.*;

@Entity
@Table(name = "Admin")
public class Admin extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    //constructor
    public Admin() {
        super();
    }
    public Admin(Admin admin) {
        super(admin);
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
}
