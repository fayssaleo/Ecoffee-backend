package ECoffee.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "usersroom")
public class UsersRoom implements Serializable {
    @EmbeddedId
    private UsersRoomId id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;
    @Column(name = "owner")
    private boolean Owner;
    //Join
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @MapsId("room_id")
    @JoinColumn(name="room_id")
    private Room room;

    //Constructors

    public UsersRoom() {
    }
    public UsersRoom(UsersRoom Ur) {
        this.date = Ur.getDate();
        this.Owner = Ur.isOwner();
    }

    //getters & setters


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
