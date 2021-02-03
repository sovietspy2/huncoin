package stream.wortex.network;

import java.util.List;

public class Message {

    public Message(MessageType type, List<String> knownHosts) {
        this.type = type;
        this.knownHosts = knownHosts;
    }

    private MessageType type;
    private List<String> knownHosts;

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public List<String> getKnownHosts() {
        return knownHosts;
    }

    public void setKnownHosts(List<String> knownHosts) {
        this.knownHosts = knownHosts;
    }
}
