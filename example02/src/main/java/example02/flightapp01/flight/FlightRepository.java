package example02.flightapp01.flight;

import java.util.List;
import java.util.Optional;

import example02.util.mysql.DbMysqlBase;
import example02.util.mysql.DbMysqlIAddParam;
import example02.util.mysql.DbMysqlIGetEntity;

public class FlightRepository implements FlightIRepository{
	private Flight create(Flight flight) {
		String sql = "INSERT INTO flight(airline_name, number, source, destination) VALUES(?,?,?,?)";
		DbMysqlIAddParam<Flight> addParam = (statement, flightValue)->{
			statement.setString(1,flightValue.getAirlineName());
			statement.setString(2,flightValue.getNumber());
			statement.setString(3,flightValue.getSource());
			statement.setString(4,flightValue.getDestination());
		};
		//boolean dbResult = DbMysqlBase.factory().write(sql, flight, addParam); //UNCOMMENT I
		DbMysqlBase.factory().write(sql, flight, addParam);//COMMENT I
		
		Flight newFlight = flight; //???? call readOne function II
		
		return newFlight;
	}
	private Flight update(Flight flight) {
		String sql = "UPDATE flight SET airline_name=?, number=?, source=?, destination=? WHERE (id=?)";
		DbMysqlIAddParam<Flight> addParam = (statement, flightValue)->{
			statement.setString(1,flightValue.getAirlineName());
			statement.setString(2,flightValue.getNumber());
			statement.setString(3,flightValue.getSource());
			statement.setString(4,flightValue.getDestination());
			statement.setInt(5,flightValue.getId());
		};
		boolean dbResult = DbMysqlBase.factory().write(sql, flight, addParam);
		
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
		DbMysqlIAddParam<Flight> addParam = (statement, flightValue)->{
			statement.setInt(1,id);
		};
		DbMysqlIGetEntity<Flight> getEntity = (resultSet)->{
			Flight flight = new Flight();
			//...
			flight.setId(resultSet.getInt(1));
			flight.setNumber(resultSet.getString(2));
			flight.setAirlineName(resultSet.getString(3));
			flight.setSource(resultSet.getString(4));
			flight.setDestination(resultSet.getString(5));
			//...
			return flight;
		};
		
		Optional<Flight> flightOptional = DbMysqlBase.factory().readOne(sql, getEntity, addParam);
		
		return flightOptional;
	}
	@Override
	public List<Flight> findAll(){
		String sql = "SELECT id, number, airline_name, source, destination FROM flight";
		DbMysqlIAddParam<Flight> addParam = (statement, flightValue)->{
		};
		DbMysqlIGetEntity<Flight> getEntity = (resultSet)->{
			Flight flight = new Flight();
			//...
			flight.setId(resultSet.getInt(1));
			flight.setNumber(resultSet.getString(2));
			flight.setAirlineName(resultSet.getString(3));
			flight.setSource(resultSet.getString(4));
			flight.setDestination(resultSet.getString(5));
			//...
			return flight;
		};
		
		List<Flight> flights = DbMysqlBase.factory().readAll(sql, getEntity, addParam);
		
		return flights;
	}
	@Override
	public void deleteById(int id) {
		String sql = "DELETE FROM flight WHERE (id=?)";
		DbMysqlIAddParam<Object> addParam = (statement, empty)->{
			statement.setInt(1,id);
		};
		boolean dbResult = DbMysqlBase.factory().write(sql, null, addParam);
	}
	
}
