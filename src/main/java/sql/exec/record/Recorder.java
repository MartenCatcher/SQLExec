package sql.exec.record;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class Recorder {
    public static void write(String row) {
        System.out.print(row);
    }

    public static void writeError(String row) {
        System.err.print(row);
    }
}
