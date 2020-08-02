package ECoffee.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "UsersRoom")
@IdClass(UsersRoomId.class)
public class UsersRoom implements Serializable {
    @Id
    @Column(name = "user")
    private Integer idUser;
    @Id
    @Column(name = "room")
    private Integer idRoom;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @Column(name = "owner")
    private boolean Owner;
    //Join
    @ManyToOne
    @PrimaryKeyJoinColumn(name="user_id", referencedColumnName="id")
    private User user;
    @ManyToOne
    @PrimaryKeyJoinColumn(name="room_id", referencedColumnName="id")
    private Room room;

    //Constructors

    public UsersRoom() {
    }
    public UsersRoom(UsersRoom Ur) {
        this.idUser = Ur.getIdUser();
        this.idRoom = Ur.getIdRoom();
        this.date = Ur.getDate();
        this.Owner = Ur.isOwner();
    }

    //getters & setters

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
