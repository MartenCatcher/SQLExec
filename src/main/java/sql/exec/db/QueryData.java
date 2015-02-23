package sql.exec.db;

/**
 * Created by mstupin on 2/22/2015.
 */
public class QueryData {
    private String connectionString;
    private String className;
    private String user;
    private String password;
    private String query;
    private String inFilePath;
    private boolean printHeaders;
    private String delimiter;
    private String quotes;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getInFilePath() {
        return inFilePath;
    }

    public void setInFilePath(String inFilePath) {
        this.inFilePath = inFilePath;
    }

    public Boolean getPrintHeaders() {
        return printHeaders;
    }

    public void setPrintHeaders(Boolean printHeaders) {
        this.printHeaders = printHeaders;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryData{");
        sb.append("connectionString='").append(connectionString).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", query='").append(query).append('\'');
        sb.append(", inFilePath='").append(inFilePath).append('\'');
        sb.append(", printHeaders=").append(printHeaders);
        sb.append(", delimiter='").append(delimiter).append('\'');
        sb.append(", quotes='").append(quotes).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
