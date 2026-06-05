package ServerWeb.conf.d;

public class ConfigSite {
    private int port;
    private String DocumentRoot, DefaultIndex, AccessLog, ErrorLog;

    public int getPort() {
        return port;
    }

    public String getDocumentRoot() {
        return DocumentRoot;
    }

    public String getDefaultIndex() {
        return DefaultIndex;
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
    public void setDocumentRoot(String documentRoot) {
        this.DocumentRoot = documentRoot;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setDefaultIndex(String defaultIndex) {
        DefaultIndex = defaultIndex;
    }
}
