package ECoffee.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Entity
@Table(name = "UsersRoom")
public class UsersRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usersRoom_id")
    private Integer id;
    @Column(name = "user_id")
    private Integer idUser;
    @Column(name = "room_id")
    private Integer idRoom;
    @Column(name = "date")
    private DateTimeFormat Date;
    @Column(name = "owner")
    private boolean Owner;

    //Constructors

    public UsersRoom() {
    }
    public UsersRoom(UsersRoom Ur) {
        this.id = Ur.getId();
        this.idUser = Ur.getIdUser();
        this.idRoom = Ur.getIdRoom();
        this.Date = Ur.getDate();
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

    public DateTimeFormat getDate() {
        return Date;
    }

    public void setDate(DateTimeFormat date) {
        Date = date;
    }

    public boolean isOwner() {
        return Owner;
    }

    public void setOwner(boolean owner) {
        Owner = owner;
    }
}
