package example02.util.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbMysqlBase {
	/**
	 * Singleton 
	 */
	private DbMysqlBase() {
		
	}
	private static DbMysqlBase instance = null;
	public static DbMysqlBase factory(){
		if(instance == null) {
			instance = new DbMysqlBase();
		}
		return instance;
	}
	
	/**
	 * DB Connection 
	 */
	private Connection connection = null;
	private void open() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection(DbMySqlIConfig.URL,
				DbMySqlIConfig.USER,
				DbMySqlIConfig.PASSWORD);
	}
	private void close() throws SQLException {
		if(this.connection == null) {
			return;
		}
				
		this.connection.close();
	}
	/**
	 * read / write operations
	 */
	public <T>List<T> readAll(String sql, 
			DbMysqlIGetEntity<T> fnGetEntity, 
			DbMysqlIAddParam<T> fnSetParams){
		List<T> entities = new ArrayList<T>();
		try {			
			this.open();
			PreparedStatement statement = this.connection.prepareStatement(sql);
			if(fnSetParams != null) {
				T empty = null;
				fnSetParams.doAdParams(statement, empty);
			}
			ResultSet resultSet = 	statement.executeQuery();
			while(resultSet.next()) {
				entities.add(fnGetEntity.doGetEntity(resultSet));
			}
			this.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return entities;
	}
	public <T>List<T> readAll(String sql, 
			DbMysqlIGetEntity<T> fnGetEntity){
		return readAll(sql, fnGetEntity, null);
	}
	public <T>boolean write(String sql, T entity,
			DbMysqlIAddParam<T> fnSetParams) {
		boolean dbResult = false;
		
		try {			
			this.open();
			PreparedStatement statement = this.connection.prepareStatement(sql);
			if(fnSetParams != null) {
				fnSetParams.doAdParams(statement, entity);
			}
			statement.execute();
			dbResult = true;
			this.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return dbResult;
	}
	public <T>boolean write(String sql,
			T entity) {
		return write(sql, entity, null);
	}
	public <T>Optional<T> readOne(String sql, 
			DbMysqlIGetEntity<T> fnGetEntity, 
			DbMysqlIAddParam<T> fnSetParams){
		Optional<T> entityOptional = Optional.empty();
		try {			
			this.open();
			PreparedStatement statement = this.connection.prepareStatement(sql);
			if(fnSetParams != null) {
				T empty = null;
				fnSetParams.doAdParams(statement, empty);
			}
			ResultSet resultSet = 	statement.executeQuery();
			if(resultSet.next()) {
				T entity = fnGetEntity.doGetEntity(resultSet); 
				entityOptional =  Optional.of(entity);
			}
			this.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return entityOptional;
	}
	public <T>Optional<T> readOne(String sql, 
			DbMysqlIGetEntity<T> fnGetEntity){
		return readOne(sql, fnGetEntity, null);
	}
}
