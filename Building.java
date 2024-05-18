import java.util.ArrayList;
import java.util.List;


public class Building {
    int floorNumber;
    public List<Elevator> elevators;

    public  Building(int floorNumber, ArrayList<Elevator> elevators){
        this.floorNumber = floorNumber;
        this.elevators = elevators;
    }

    public void chooseElevator(Resident passenger) {
        if (elevators.getFirst().currentFloor == passenger.from_floor){
            passenger.entered = true;
            elevators.getFirst().addPassenger(passenger);
        }
        else if (elevators.getLast().currentFloor == passenger.from_floor){
            passenger.entered = true;
            elevators.getLast().addPassenger(passenger);
        }
        else if (elevators.getFirst().direction == 0 && elevators.getLast().direction == 0) {
            if (Math.abs(elevators.getFirst().currentFloor - passenger.from_floor) <= Math.abs(elevators.getLast().currentFloor - passenger.from_floor)) {
                elevators.getFirst().addPassenger(passenger);
            } else {
                elevators.getLast().addPassenger(passenger);
            }
        } else if (elevators.getFirst().direction != 0 && elevators.getLast().direction == 0) {
            elevators.getLast().addPassenger(passenger);
        } else if (elevators.getFirst().direction == 0 && elevators.getLast().direction != 0) {
            elevators.getFirst().addPassenger(passenger);
        }

        else{
            elevators.getLast().addPassenger(passenger);
        }


    }


}