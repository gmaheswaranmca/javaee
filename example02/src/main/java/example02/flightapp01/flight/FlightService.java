package example02.flightapp01.flight;

import java.util.List;
import java.util.Optional;

public class FlightService {
	FlightIRepository repository = new FlightRepository();
	
	public Flight save(Flight flight) {
		return repository.save(flight);
	}
	
	public Optional<Flight> findById(int id){
		return repository.findById(id);
	}
	
	public List<Flight> findAll(){
		return repository.findAll();
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}
}
