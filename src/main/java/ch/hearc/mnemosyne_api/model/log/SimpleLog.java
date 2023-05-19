package ch.hearc.mnemosyne_api.model.log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleLog implements Serializable {
    private String action;
    private String timestamp;
    private LogType type;
    private String message;

    public SimpleLog(@JsonProperty("type") LogType type, @JsonProperty("message") String message,
            @JsonProperty("action") String action) {
        this.type = type;
        this.message = message;
        this.action = action;

        this.timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new java.util.Date());
    }

    public String getAction() {
        return action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LogType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(LogType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SimpleLog [action=" + action + ", message=" + message + ", timestamp=" + timestamp + ", type=" + type
                + "]";
    }
}
