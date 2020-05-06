package utils;

/*
    Author:leia
    Write The Code Change The World    
*/public class JsonBean {

    /**
     * Count : 5468.88
     * Message : HOPE永存
     * Player : 黑菜
     */

    private String Count;
    private String Message;
    private String Player;

    @Override
    public String toString() {
        return "JsonBean{" +
                "Count='" + Count + '\'' +
                ", Message='" + Message + '\'' +
                ", Player='" + Player + '\'' +
                '}';
    }

    public JsonBean() {
    }

    public JsonBean(String count, String message, String player) {
        Count = count;
        Message = message;
        Player = player;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String Count) {
        this.Count = Count;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getPlayer() {
        return Player;
    }

    public void setPlayer(String Player) {
        this.Player = Player;
    }

}
