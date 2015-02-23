package sql.exec.config;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public enum ArgumentName {
    //TODO: add info & usage
    h("-h", false, null, null),                     //help
    cs("-cs", false, null, String.class),           //connection string
    q("-q", false, null, String.class),             //sql query
    in("-in", true, null, String.class),            //input file with query
    ph("-ph", true, "true", Boolean.class),         //print headers
    d("-d", true, ",", String.class),               //delimiter
    qu("-qu", true, "'", String.class),             //quotes
    c("-c", true, null, String.class);              //driver class name
    
    private String consoleParam;
    private boolean nextIsValue;
    private String defaultValue;
    private Class type;

    ArgumentName(String consoleParam, boolean nextIsValue, String defaultValue, Class type) {
        this.consoleParam = consoleParam;
        this.nextIsValue = nextIsValue;
        this.defaultValue = defaultValue;
        this.type = type;
    }

    public String getConsoleParam() {
        return consoleParam;
    }

    public boolean isNextIsValue() {
        return nextIsValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Class getType() {
        return type;
    }
}
