package sql.exec.db;

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
        try(Connection dbConnection = connectionBuilder.getConnection();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(connectionBuilder.getQueryData().getQuery())) {
            
            ResultSet rs = preparedStatement.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
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
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage(), e);
        }
    }
}
