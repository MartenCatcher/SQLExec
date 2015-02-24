package sql.exec.config;

import sql.exec.db.QueryData;
import sql.exec.exception.ArgumentException;

/**
 * Created by MartenCatcher on 2/15/2015.
 */
public class ArgumentsAnalyzer {
    
    public QueryData analyze(String[] args) throws ArgumentException {
        QueryData query = new QueryData();

        //defaults
        query.setQuotes("");
        query.setDelimiter(",");
        query.setPrintHeaders(false);
        for (int i = 0; i < args.length; i++) {
            //TODO: add params out of range checking
            switch (args[i]) {
                case "-h":
                    break;
                case "-cs":
                    i++;
                    query.setConnectionString(args[i]);
                    break;
                case "-q":
                    i++;
                    query.setQuery(args[i]);
                    break;
                //TODO: implement
                case "-in":
                    i++;
                    query.setQuery(args[i]);
                    break;
                case "-ph":
                    query.setPrintHeaders(true);
                    break;
                case "-d":
                    i++;
                    query.setDelimiter(args[i]);
                    break;
                case "-qu":
                    i++;
                    query.setQuotes(args[i]);
                    break;
                case "-c":
                    i++;
                    query.setClassName(args[i]);
                    break;
                default:
                    throw new ArgumentException(Info.invalidPosition(i, args[i]));
            }
        }
        validate(query);
        return query;
    }
    
    private void validate(QueryData queryData) throws ArgumentException {
        if(queryData.getConnectionString() == null || queryData.getConnectionString().isEmpty()) {
            throw new ArgumentException(Info.argumentNotSet("connection string", "-cs"));
        }
        if(queryData.getQuery() == null || queryData.getQuery().isEmpty()) {
            throw new ArgumentException(Info.argumentNotSet("query", "-q"));
        }
        if(queryData.getClassName() == null || queryData.getClassName().isEmpty()) {
            throw new ArgumentException(Info.argumentNotSet("class name", "-c"));
        }
    }
}
