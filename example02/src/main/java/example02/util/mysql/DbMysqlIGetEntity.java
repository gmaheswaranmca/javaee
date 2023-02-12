package example02.util.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface DbMysqlIGetEntity<T> {
	T doGetEntity(ResultSet resultSet) throws SQLException;
}
