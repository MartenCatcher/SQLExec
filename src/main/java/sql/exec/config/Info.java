package sql.exec.config;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class Info {
    public static String helpInfo() {
        return "Usage: Exec [" + ArgumentName.HELP.getConsoleParam() + "]\n" +
                "           [" + ArgumentName.CONNECTION_STRING.getConsoleParam() + " <connection string>]\n" +
                "           [" + ArgumentName.CONFIG_PATH.getConsoleParam() + " <config path>]\n" +
                "           [" + ArgumentName.QUERY.getConsoleParam() + " <sql query>]\n" +
                "           [" + ArgumentName.INPUT_FILE.getConsoleParam() + " [<path to in file>]]\n" +
                "           [" + ArgumentName.OUTPUT_FILE.getConsoleParam() + " [<path to out file>]]\n";
    }

    public static String wrongParameter(int position, String value) {
        return "Wrong parameter in position " + position + ": '" + value + "'";
    }
}
