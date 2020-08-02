package ECoffee.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Room")
public class Room {
    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private Integer idRoom;
    @Column(name = "Name")
    private String Name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DateCreation")
    private Date DateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EndingDate")
    private Date EndingDate;

    //join
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UsersRoom", joinColumns = {@JoinColumn(name = "room", referencedColumnName = "room_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "usersRoom_id", referencedColumnName = "usersRoom_id")})
    private Set<UsersRoom> Ur;
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
