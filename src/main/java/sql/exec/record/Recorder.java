package sql.exec.record;

import sql.exec.exception.RecorderException;

import java.io.Closeable;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public interface Recorder extends Closeable {
    public void write(String row) throws RecorderException;
    public void writeError(String row) throws RecorderException;
}
