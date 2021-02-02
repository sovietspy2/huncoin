public class MessageEvent {

    private String key;
    private String ip;
    private String message;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageEvent(String key, String ip, String message) {
        this.key = key;
        this.ip = ip;
        this.message = message;
    }
}
