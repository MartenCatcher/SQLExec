package sql.exec.record;

import sql.exec.exception.RecorderException;

import java.io.*;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class FileOutRecorder implements Recorder {
    
    private String pathToFile;
    private Writer writer;

    public FileOutRecorder(String pathToFile) throws IOException {
        this.pathToFile = pathToFile;
        writer = new BufferedWriter(new FileWriter(pathToFile));
    }

    @Override
    public void write(String row) throws RecorderException {
        try {
            writer.write(row);
        } catch (IOException e) {
            throw new RecorderException("Error writing record '" + row +"' to file '" + pathToFile + "': " + e.getMessage(), e);
        }
    }

    @Override
    public void writeError(String row) {
        System.err.println(row);
    }

    @Override
    public void close() throws IOException {
        if (writer != null)   
            writer.close();
    }
}
