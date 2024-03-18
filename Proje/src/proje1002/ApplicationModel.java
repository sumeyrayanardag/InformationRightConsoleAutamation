
import java.sql.*;
import java.util.*;

class ApplicationModel implements ModelInterface {

    public ResultSet select(Map<String, Object> whereParameters) throws Exception {
        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("     AppID,Details");//getting necessary fields
        sql.append(" FROM Application ");

        List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);
        sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));

        // execute constructed SQL statement
        Connection connection = DatabaseUtilities.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
        ResultSet result = preparedStatement.executeQuery();

        return result;
    }

    public int insert(String fieldNames, List<Object> rows) throws Exception {

        // construct SQL statement
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO Application (" + fieldNames + ") ");
        sql.append(" VALUES ");

        String[] fieldList = fieldNames.split(",");

        int rowCount = 0;
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i) instanceof Application) {
                rowCount++;

                Application app = (Application) rows.get(i);

                sql.append("(");
                for (int j = 0; j < fieldList.length; j++) {
                    String fieldName = fieldList[j].trim();

                    if (app.getByName(fieldName) == null) {

                        sql.append(DatabaseUtilities.formatField(Types.NULL));//null
                    } else {
                        sql.append(DatabaseUtilities.formatField(app.getByName(fieldName)));
                    }
                    if (j < fieldList.length - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");

                if (i < rows.size() - 1) {
                    sql.append(", ");
                }
            }
        }

        // execute constructed SQL statement
        if (rowCount > 0) {
            Connection connection = DatabaseUtilities.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            rowCount = preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        return rowCount;
    }

    public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
        return 0;
    }

    public int delete(Map<String, Object> whereParameters) throws Exception {
      
        return 0;
    }

    @Override
    public String toString() {
        return "Application Model";
    }
}
