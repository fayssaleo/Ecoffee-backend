package ECoffee.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsersRoomId implements Serializable {
    @Column(name = "user_id")
    private Integer idUser;
    @Column(name = "room_id")
    private Integer idRoom;


}
