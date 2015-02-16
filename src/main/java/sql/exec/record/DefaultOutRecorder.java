package sql.exec.record;

import java.io.IOException;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class DefaultOutRecorder implements Recorder {
    @Override
    public void write(String row) {
        System.out.print(row);
    }

    @Override
    public void writeError(String row) {
        System.err.print(row);
    }

    @Override
    public void close() throws IOException {
        //do nothing
    }
}
