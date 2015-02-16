package sql.exec.config;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public enum ArgumentName {
    HELP("-h", false),
    CONNECTION_STRING("-cs", false),
    CONFIG_PATH("-cp", false),
    QUERY("-q", false),
    INPUT_FILE("-if", true),
    OUTPUT_FILE("-of", true);
    
    private String consoleParam;
    private boolean nextIsValue;

    ArgumentName(String consoleParam, boolean nextIsValue) {
        this.consoleParam = consoleParam;
        this.nextIsValue = nextIsValue;
    }

    public String getConsoleParam() {
        return consoleParam;
    }

    public boolean isNextIsValue() {
        return nextIsValue;
    }
}
