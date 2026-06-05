package ServerWeb.config;

public class ConfigSite {
    private int port;
    private String root, Index, AccessLog, ErrorLog;

    public int getPort() {
        return port;
    }

    public String getRoot() {
        return root;
    }

    public String getIndex() {
        return Index;
    }

    public String getAccessLog() {
        return AccessLog;
    }
    public String getErrorLog() {
        return ErrorLog;
    }

    public void setAccessLog(String accessLog) {
        AccessLog = accessLog;
    }
    public void setErrorLog(String errorLog) {
        ErrorLog = errorLog;
    }
    public void setRoot(String root) {
        this.root = root;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIndex(String index) {
        Index = index;
    }
}
