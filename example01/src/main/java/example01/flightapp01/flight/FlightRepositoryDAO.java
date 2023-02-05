package example01.flightapp01.flight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import example01.util.mysql.DbMySqlIConfig;

public class FlightRepositoryDAO implements FlightIRepository{
	private Flight create(Flight flight) {
		String sql = "INSERT INTO flight(airline_name, number, source, destination) VALUES(?,?,?,?)";
		boolean dbResult = false;
		Connection connection = null;
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DbMySqlIConfig.URL,
					DbMySqlIConfig.USER,
					DbMySqlIConfig.PASSWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,flight.getAirlineName());
			statement.setString(2,flight.getNumber());
			statement.setString(3,flight.getSource());
			statement.setString(4,flight.getDestination());
			
			statement.execute();
			dbResult = true;
			connection.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Flight newFlight = flight; //???? call readOne function II
		
		return newFlight;
	}
	private Flight update(Flight flight) {
		String sql = "UPDATE flight SET airline_name=?, number=?, source=?, destination=? WHERE (id=?)";
		
		boolean dbResult = false;
		Connection connection = null;
		try {			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DbMySqlIConfig.URL,
					DbMySqlIConfig.USER,
					DbMySqlIConfig.PASSWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,flight.getAirlineName());
			statement.setString(2,flight.getNumber());
			statement.setString(3,flight.getSource());
			statement.setString(4,flight.getDestination());
			statement.setInt(5,flight.getId());
			
			statement.execute();
			dbResult = true;
			connection.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		
		Flight newFlight = flight; //???? call readOne function II
		
		return newFlight;
	}
	/**
	 * 
	 */
	@Override
	public Flight save(Flight flight) {
		Flight newFlight = null;
		if(flight.getId() == 0) {
			newFlight = this.create(flight);
		}
		else {
			newFlight = this.update(flight);
		}
		
		return newFlight;
	}
	@Override
	public Optional<Flight> findById(int id){
		String sql = "SELECT id, number, airline_name, source, destination FROM flight WHERE (id=?)";
		Optional<Flight> flightOptional = Optional.empty();
		Connection connection = null;
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DbMySqlIConfig.URL,
					DbMySqlIConfig.USER,
					DbMySqlIConfig.PASSWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,id);
			
			ResultSet resultSet = 	statement.executeQuery();
			if(resultSet.next()) {
				Flight flight = new Flight();
				//...
				flight.setId(resultSet.getInt(1));
				flight.setNumber(resultSet.getString(2));
				flight.setAirlineName(resultSet.getString(3));
				flight.setSource(resultSet.getString(4));
				flight.setDestination(resultSet.getString(5));
				//...
				flightOptional =  Optional.of(flight);
			}
			connection.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		
		return flightOptional;
	}
	@Override
	public List<Flight> findAll(){
		String sql = "SELECT id, number, airline_name, source, destination FROM flight";
		List<Flight> flights = new ArrayList<Flight>();
		
		Connection connection = null;
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DbMySqlIConfig.URL,
					DbMySqlIConfig.USER,
					DbMySqlIConfig.PASSWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			
			ResultSet resultSet = 	statement.executeQuery();
			while(resultSet.next()) {
				Flight flight = new Flight();
				//...
				flight.setId(resultSet.getInt(1));
				flight.setNumber(resultSet.getString(2));
				flight.setAirlineName(resultSet.getString(3));
				flight.setSource(resultSet.getString(4));
				flight.setDestination(resultSet.getString(5));
				//...
				flights.add(flight);
			}
			connection.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
		
		return flights;
	}
	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM flight WHERE (id=?)";
		
		boolean dbResult = false;
		Connection connection = null;
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DbMySqlIConfig.URL,
					DbMySqlIConfig.USER,
					DbMySqlIConfig.PASSWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1,id);
			
			statement.execute();
			dbResult = true;
			connection.close();
		}
		catch (SQLException exception) {
			exception.printStackTrace();
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		}
	}
}
