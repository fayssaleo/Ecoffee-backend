package ECoffee.entities;

import org.apache.tomcat.jni.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "UsersRoom")
public class UsersRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usersRoom_id")
    private Integer id;
    @Column(name = "user")
    private Integer idUser;
    @Column(name = "room")
    private Integer idRoom;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @Column(name = "owner")
    private boolean Owner;
    //Join
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "User", joinColumns = {@JoinColumn(name = "usersRoom_id", referencedColumnName = "usersRoom_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user", referencedColumnName = "user_id")})
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "Room", joinColumns = {@JoinColumn(name = "usersRoom_id", referencedColumnName = "usersRoom_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "room", referencedColumnName = "room_id")})
    private Room room;

    //Constructors

    public UsersRoom() {
    }
    public UsersRoom(UsersRoom Ur) {
        this.id = Ur.getId();
        this.idUser = Ur.getIdUser();
        this.idRoom = Ur.getIdRoom();
        this.date = Ur.getDate();
        this.Owner = Ur.isOwner();
    }

    //getters & setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        date = date;
    }

    public boolean isOwner() {
        return Owner;
    }

    public void setOwner(boolean owner) {
        Owner = owner;
    }
}
