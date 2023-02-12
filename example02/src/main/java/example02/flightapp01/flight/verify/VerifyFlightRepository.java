package example02.flightapp01.flight.verify;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import example02.flightapp01.flight.Flight;
import example02.flightapp01.flight.FlightIRepository;
import example02.flightapp01.flight.FlightRepository;

public class VerifyFlightRepository {
	static FlightIRepository repository = new FlightRepository(); 
	
	static void testCreate() {
		Scanner scanner = new Scanner(System.in);
		
		Flight newFlight = new Flight();
		System.out.println("Enter flight number:");
		newFlight.setNumber(scanner.nextLine());
		System.out.println("Enter Airline Name:");
		newFlight.setAirlineName(scanner.nextLine());
		System.out.println("Enter Source:");
		newFlight.setSource(scanner.nextLine());
		System.out.println("Enter Destination:");
		newFlight.setDestination(scanner.nextLine());
		
		Flight savedFlight = repository.save(newFlight);
		
		System.out.println("Flight is created!!!");
	}
	static void testReadOne() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter flight id:");
		Optional<Flight> oldFlightOptional = repository.findById(scanner.nextInt());
		if(!oldFlightOptional.isPresent()) {
			System.out.println("Flight is not found.");
			return;
		}
		Flight oldFlight = oldFlightOptional.get();
		System.out.println("The Flight:");
		System.out.println(oldFlight);
		
	}
	static void testReadAll() {
		List<Flight> flights = repository.findAll();
		System.out.println("The Flight:");
		for(Flight flight:flights) {
			System.out.println(flight);
		}
	}
	static void testUpdate() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter flight id:");
		Optional<Flight> oldFlightOptional = repository.findById(scanner.nextInt());
		if(!oldFlightOptional.isPresent()) {
			System.out.println("Flight is not found.");
			return;
		}
		Flight oldFlight = oldFlightOptional.get();
		System.out.println("Old Flight:");
		System.out.println(oldFlight);
		//...
		Flight newFlight = new Flight();
		newFlight.setId(oldFlight.getId());
		System.out.println("Enter flight number:");
		newFlight.setNumber(scanner.nextLine());
		System.out.println("Enter Airline Name:");
		newFlight.setAirlineName(scanner.nextLine());
		System.out.println("Enter Source:");
		newFlight.setSource(scanner.nextLine());
		System.out.println("Enter Destination:");
		newFlight.setDestination(scanner.nextLine());
		
		Flight savedFlight = repository.save(newFlight);
		System.out.println("Flight is updated!!!");
	}
	static void testDelete() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter flight id:");
		Optional<Flight> oldFlightOptional = repository.findById(scanner.nextInt());
		if(!oldFlightOptional.isPresent()) {
			System.out.println("Flight is not found.");
			return;
		}
		Flight oldFlight = oldFlightOptional.get();
		System.out.println("Old Flight:");
		System.out.println(oldFlight);
		
		repository.deleteById(oldFlight.getId());
		
		System.out.println("Flight is deleted!!!");
	}
	static int menu() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("**** Flight CRUD operations test*****");
		System.out.println("The Choices are");
		int choice = -1;
		System.out.println("\t1-Create");
		System.out.println("\t2-Read By Id");
		System.out.println("\t3-Read All");
		System.out.println("\t4-Update");
		System.out.println("\t5-Delete");
		System.out.println("\t0-Exit");
		System.out.println("Your Choice:");
		
		choice = scanner.nextInt();
		return choice;
	}
	public static void main(String[] args) {
		
		int choice = -1;
		do {
			choice = menu();
			switch(choice) {
				case 1:
					testCreate();
					break;
				case 2:
					testReadOne();
					break;
				case 3:
					testReadAll();
					break;
				case 4:
					testUpdate();
					break;
				case 5:
					testDelete();
					break;
				default:
					System.out.println("Wrong choice!!!");
					break;
			}
		}while(choice > 0);
	}

}
