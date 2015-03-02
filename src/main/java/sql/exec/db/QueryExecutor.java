package sql.exec.db;

import sql.exec.config.TimeHolder;
import sql.exec.exception.DBException;
import sql.exec.exception.RecorderException;
import sql.exec.record.Recorder;

import java.sql.*;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class QueryExecutor {
    private int rowAggregateCount = 10;
    
    private ConnectionBuilder connectionBuilder;

    public QueryExecutor(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    public void executeQuery() throws DBException, RecorderException {
        QueryData query = connectionBuilder.getQueryData();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        
        try{
            //TODO: verbose
            //Recorder.write("Before connection:\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
            connection = connectionBuilder.getConnection();
            //TODO: verbose
            //Recorder.write("Before statement:\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
            statement = connection.createStatement();

            //TODO: verbose
            //Recorder.write("Before execute:\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
            //TODO: implement procedure call
            statement.execute(connectionBuilder.getQueryData().getQuery());
            //TODO: verbose
            //Recorder.write("Before result:\t\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
            rs = statement.getResultSet();

            //TODO: verbose
            //Recorder.write("Before connection:\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
            ResultSetMetaData meta = rs.getMetaData();
            
            //TODO: verbose
            //Recorder.write("After metadata:\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
            int count = meta.getColumnCount();
            if(count > 0) {
                StringBuilder record = new StringBuilder();
                if(query.getPrintHeaders()) {
                    record.append(query.getQuotes());
                    for (int i = 1; i <= count; i++) {
                        record.append(meta.getColumnName(i));
                        if (i < count) {
                            record.append(query.getQuotes()).append(query.getDelimiter()).append(query.getQuotes());
                        } else {
                            record.append(query.getQuotes()).append("\n");
                        }
                    }
                    Recorder.write(record.toString());
                    record.setLength(0);
                }
                //TODO: verbose
                //Recorder.write("Before print:\t\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
                int counter = 0;
                while (rs.next()) {
                    counter++;
                    record.append(query.getQuotes());
                    for (int i = 1; i <= count; i++) {
                        record.append(rs.getObject(i));
                        if(i < count) {
                            record.append(query.getQuotes()).append(query.getDelimiter()).append(query.getQuotes());
                        } else {
                            record.append(query.getQuotes()).append("\n");
                        }            
                    }
                    if(counter % rowAggregateCount == 0) {
                        Recorder.write(record.toString());
                        record.setLength(0);
                        counter = 0;
                    }
                }
                if(count > 0) {
                    Recorder.write(record.toString());
                }
                //TODO: verbose
                //Recorder.write("After print:\t\t\t\t" + TimeHolder.getInstance().getOffset(System.nanoTime()));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        } finally {
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
