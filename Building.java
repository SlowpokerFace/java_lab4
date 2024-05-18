import java.util.ArrayList;
import java.util.List;


public class Building {
    int floorNumber;
    public List<Elevator> elevators;

    public Building() {
        this.elevators = new ArrayList<Elevator>();
    }
    public  Building(int floorNumber, ArrayList<Elevator> elevators){
        this.floorNumber = floorNumber;
        this.elevators = elevators;
    }

    public void chooseElevator(Resident passenger) {
        if (elevators.getFirst().direction == 0 && elevators.getLast().direction == 0) {
            if (Math.abs(elevators.getFirst().currentFloor - passenger.from_floor) <= Math.abs(elevators.getLast().currentFloor - passenger.from_floor)) {
                elevators.getFirst().addPassenger(passenger);
            } else {
                elevators.getLast().addPassenger(passenger);
            }
        } else if (elevators.getFirst().direction != 0 && elevators.get(1).direction == 0) {
            elevators.getLast().addPassenger(passenger);
        } else if (elevators.getFirst().direction == 0 && elevators.get(1).direction != 0) {
            elevators.getFirst().addPassenger(passenger);
        } else if (Math.max(elevators.getFirst().passengers.peek().from_floor, elevators.getFirst().passengers.peek().to_floor) >= passenger.from_floor && Math.min(elevators.getFirst().passengers.peek().from_floor, elevators.getFirst().passengers.peek().to_floor) <= passenger.from_floor) {
            elevators.getFirst().addPassenger(passenger);
        } else if (Math.max(elevators.getLast().passengers.peek().from_floor, elevators.getLast().passengers.peek().to_floor) >= passenger.from_floor && Math.min(elevators.getLast().passengers.peek().from_floor, elevators.getLast().passengers.peek().to_floor) <= passenger.from_floor) {
            elevators.getLast().addPassenger(passenger);
        } else if (Math.abs(elevators.getFirst().passengers.peek().to_floor - passenger.from_floor) <= Math.abs(elevators.getLast().passengers.peek().to_floor - passenger.from_floor))
        {
            elevators.getFirst().addPassenger(passenger);

        }
        else if (Math.abs(elevators.getFirst().passengers.peek().to_floor - passenger.from_floor) > Math.abs(elevators.getLast().passengers.peek().to_floor - passenger.from_floor))
        {
            elevators.getLast().addPassenger(passenger);
        }
        else{
            elevators.getLast().addPassenger(passenger);
        }


    }


}
