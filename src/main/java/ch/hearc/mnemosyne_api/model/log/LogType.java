package ch.hearc.mnemosyne_api.model.log;

public enum LogType {
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR"),
    DEBUG("DEBUG");

    private String type;

    LogType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
