package ECoffee.entities;

import javax.persistence.Column;
import java.io.Serializable;

public class UsersRoomId implements Serializable {
    @Column(name = "user_id")
    private Integer idUser;
    @Column(name = "room_id")
    private Integer idRoom;
}
