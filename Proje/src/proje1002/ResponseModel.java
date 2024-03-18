
import java.sql.*;
import java.util.*;
import java.sql.Types;


class ResponseModel implements ModelInterface {
	
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("	RejectedOrNot");
		sql.append(" FROM Response ");

		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		
		

		
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);
		ResultSet result = preparedStatement.executeQuery();
		
		return result;
	}
		
	public int insert(String fieldNames, List<Object> rows) throws Exception
	{
            int rowCount = 0;
            
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO Response (" + fieldNames + ") " );
		sql.append(" VALUES ");

		String[] fieldList = fieldNames.split(",");

		
		for (int i=0; i<rows.size(); i++) {
			if (rows.get(i) instanceof Response) {
				rowCount++;
                             
				Response response = (Response)rows.get(i); 
	
				sql.append("(");
				for (int j=0; j<fieldList.length; j++) {
					String fieldName = fieldList[j].trim();
                                        if(response.getByName(fieldName) == null){
                                            //null value ekleme
                                            sql.append(DatabaseUtilities.formatField(Types.NULL));//"null""NULL"
                                        }
                                        else{
					sql.append(DatabaseUtilities.formatField(response.getByName(fieldName)));
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
	
	public int update(Map<String,Object> updateParameters, Map<String,Object> whereParameters) throws Exception
	{
		// construct SQL statement
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE Response SET ");
		int appendCount = 0;
		for (Map.Entry<String, Object> entry : updateParameters.entrySet()) {
			sql.append(entry.getKey() + " = " + DatabaseUtilities.formatField(entry.getValue()));
			if (++appendCount < updateParameters.size()) {
				sql.append(", ");
			}
		}
		List<Map.Entry<String, Object>> whereParameterList = DatabaseUtilities.createWhereParameterList(whereParameters);		
		sql.append(DatabaseUtilities.prepareWhereStatement(whereParameterList));
		
	
		// execute constructed SQL statement
		Connection connection = DatabaseUtilities.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
		DatabaseUtilities.setWhereStatementParameters(preparedStatement, whereParameterList);		
		int rowCount = preparedStatement.executeUpdate();
		preparedStatement.close();
		
		return rowCount;
	}

	public int delete(Map<String,Object> whereParameters) throws Exception
	{
		
		
		return 0;
	}

	@Override
	public String toString() {
		return "Response Model";
	}		
}
