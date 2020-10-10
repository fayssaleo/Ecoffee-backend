package ECoffee.webSocketData;

public class SubData {
    String socketId;
    String room;
    String to;
    String sender;
    String candidate;
    String description;
    String msg;

    public String getSocketId() {
        return socketId;
    }

    @Override
    public String toString() {
        return "{" +
                "\"socketId\" : '" + socketId + '\'' +
                ", \"room\" : '" + room + '\'' +
                ", \"to\" :'" + to + '\'' +
                ", \"sender\" :'" + sender + '\'' +
                ", \"candidate\" :'" + candidate + '\'' +
                ", \"description\" : '" + description + '\'' +
                ", \"msg\" :'" + msg + '\'' +
                '}';
    }

    public void setSocketId(String socketId) {
        this.socketId = socketId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SubData(String socketId, String room, String to, String sender, String candidate, String description, String msg) {
        this.socketId = socketId;
        this.room = room;
        this.to = to;
        this.sender = sender;
        this.candidate = candidate;
        this.description = description;
        this.msg = msg;
    }
}