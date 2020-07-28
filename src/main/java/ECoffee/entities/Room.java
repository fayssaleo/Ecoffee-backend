package ECoffee.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Room")
public class Room {
    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Room_id")
    private Integer idRoom;
    @Column(name = "Name")
    private String Name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreation")
    private Date DateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EndingDate")
    private Date EndingDate;
    //Constructors
    public Room() {
    }
    public Room(Room room) {
        this.idRoom = room.getIdRoom();
        this.Name = room.getName();
        this.DateCreation = room.getDateCreation();
        this.EndingDate = room.getEndingDate();
    }
    //getters & setters

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public Date getEndingDate() {
        return EndingDate;
    }

    public void setEndingDate(Date endingDate) {
        EndingDate = endingDate;
    }
}
