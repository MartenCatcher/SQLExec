package sql.exec.config;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class Info {
    public static String helpInfo() {
        return "Usage: Exec\n" +
                " [-h]\t\t\t\t\t\tUse for print this message.\n" +
                " [-cs <connection string>]\tJDBC connection string.\n" +
                " [-q <sql query>]\t\t\tSQL query.\n" +
                " [-in <path to in file>]\tPath to file contains query (don't work now).\n" +
                " [-ph]\t\t\t\t\t\tIf you need print headers.\n" +
                " [-d <delimiter>]\t\t\tDelimiter, default comma ','.\n" +
                " [-qu <quotes>]\t\t\t\tQuotes, default none.\n" +
                " [-c <class>]\t\t\t\tName of java driver class.\n";
    }

    public static String wrongParameter(int position, String value) {
        return "Wrong parameter in position " + position + ": '" + value + "'";
    }

    public static String argumentNotSet(String name, String flag) {
        return "Argument '" + name + "' must be filled, use flag '" + flag + "'";
    }
    
    public static String invalidPosition(int i, String value) {
        return "Invalid argument on position [" + i + "]: [" + value + "], " +
                "command argument expected, use '-h' flag for help.";
    }
}
