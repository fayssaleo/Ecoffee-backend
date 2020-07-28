package ECoffee.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

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
    @Column(name = "DateCreation")
    private DateTimeFormat DateCreation;
    @Column(name = "EndingDate")
    private DateTimeFormat EndingDate;
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

    public DateTimeFormat getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(DateTimeFormat dateCreation) {
        DateCreation = dateCreation;
    }

    public DateTimeFormat getEndingDate() {
        return EndingDate;
    }

    public void setEndingDate(DateTimeFormat endingDate) {
        EndingDate = endingDate;
    }
}
