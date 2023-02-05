package example01.flightapp01.flight;

import java.util.List;
import java.util.Optional;

public interface FlightIRepository {
	//CRUD functions
	Flight save(Flight flight) ;
	Optional<Flight> findById(int id);
	List<Flight> findAll();
	void deleteById(int id);
}
