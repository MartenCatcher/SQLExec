package sql.exec.db;

import sql.exec.exception.DBException;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by mstupin on 2/22/2015.
 */
public class ConnectionBuilder {
    private QueryData queryData;

    public ConnectionBuilder(QueryData query) {
        this.queryData = query;
    }

    public QueryData getQueryData() {
        return queryData;
    }

    public Connection getConnection() {
        Connection dbConnection;
        try {
            Class.forName(queryData.getClassName());
            dbConnection = DriverManager.getConnection(queryData.getConnectionString());
        } catch (Exception e) {
            throw new DBException(e);
        }
        return dbConnection;
    }
}
