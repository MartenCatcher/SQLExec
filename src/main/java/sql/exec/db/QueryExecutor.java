package sql.exec.db;

import sql.exec.exception.DBException;
import sql.exec.exception.RecorderException;
import sql.exec.record.DefaultOutRecorder;
import sql.exec.record.Recorder;

import java.sql.*;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class QueryExecutor {
    private String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private String DB_CONNECTION = "jdbc:oracle:thin:SERVICEREGISTRY/SERVICEREGISTRY11@192.168.14.91:1521/OTTHUB";
    private String QUERY = "select * from mcc_mnc";
    private Recorder recorder = new DefaultOutRecorder();
    private int rowAggregateCount = 10;

    public void selectRecordsFromTable() throws DBException, RecorderException {
        try(Connection dbConnection = getDBConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(QUERY)) {
            
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int count = meta.getColumnCount();
            if(count > 0) {
                StringBuilder record = new StringBuilder("'");
                for (int i = 1; i <= count; i++) {
                    record.append(meta.getColumnName(i));
                    if(i < count) {
                        record.append("','");
                    } else {
                        record.append("'\n");
                    }
                }
                recorder.write(record.toString());
                record.setLength(0);

                int counter = 0;
                while (rs.next()) {
                    counter++;
                    record.append("'");
                    for (int i = 1; i <= count; i++) {
                        record.append(rs.getObject(i));
                        if(i < count) {
                            record.append("','");
                        } else {
                            record.append("'\n");
                        }            
                    }
                    if(counter % rowAggregateCount == 0) {
                        recorder.write(record.toString());
                        record.setLength(0);
                        counter = 0;
                    }
                }
                if(count > 0) {
                    recorder.write(record.toString());
                }
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private Connection getDBConnection() {
        Connection dbConnection;
        try {
            Class.forName(DB_DRIVER);
            dbConnection = DriverManager.getConnection(DB_CONNECTION);
        } catch (Exception e) {
            throw new DBException(e);
        }
        return dbConnection;
    }
}
