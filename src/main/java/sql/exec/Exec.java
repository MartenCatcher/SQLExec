package sql.exec;

import sql.exec.config.ArgumentName;
import sql.exec.config.ArgumentsAnalyzer;
import sql.exec.config.Info;
import sql.exec.record.DefaultOutRecorder;
import sql.exec.record.Recorder;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class Exec {
    public static void main(String[] args) throws Exception {
        Recorder recorder = new DefaultOutRecorder();
        switch (args.length) {
            case 0:
                recorder.write(Info.helpInfo());
                break;
            case 1:
                if (args[0].equals(ArgumentName.HELP.getConsoleParam())) {
                    recorder.write(Info.helpInfo()); 
                } else {
                    recorder.writeError(Info.wrongParameter(1, args[0]));
                }
                break;
            default:
                //TODO: complete
                new ArgumentsAnalyzer().analyze(args);
                
                //TODO: complete
                //QueryExecutor q = new QueryExecutor();
                //q.selectRecordsFromTable();
        }
    }
}
