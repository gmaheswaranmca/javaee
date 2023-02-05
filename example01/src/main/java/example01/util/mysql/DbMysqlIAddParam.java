package example01.util.mysql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface DbMysqlIAddParam<T> {
	void doAdParams(PreparedStatement statement, T entity) throws SQLException ;
}
