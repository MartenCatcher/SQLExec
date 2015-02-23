package sql.exec;

import sql.exec.config.ArgumentsAnalyzer;
import sql.exec.config.Info;
import sql.exec.db.ConnectionBuilder;
import sql.exec.db.QueryData;
import sql.exec.db.QueryExecutor;
import sql.exec.record.Recorder;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class Exec {
    public static void main(String[] args) throws Exception {
        switch (args.length) {
            case 0:
                Recorder.write(Info.helpInfo());
                break;
            case 1:
                if (args[0].length() > 1 && args[0].equals("-h")) {
                    Recorder.write(Info.helpInfo());
                } else {
                    Recorder.writeError(Info.wrongParameter(1, args[0]));
                }
                break;
            default:
                try {
                    QueryData query = new ArgumentsAnalyzer().analyze(args);
                    QueryExecutor q = new QueryExecutor(new ConnectionBuilder(query));
                    q.executeQuery();
                } catch (Exception e) {
                    Recorder.writeError(e.getMessage() + "\n");
                }
        }
    }
}
