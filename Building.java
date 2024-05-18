import java.util.ArrayList;
import java.util.List;


public class Building {
    int floorNumber;
    public List<Elevator> elevators;

    public Building(int floorNumber, ArrayList<Elevator> elevators) {
        this.floorNumber = floorNumber;
        this.elevators = elevators;
    }

    public void chooseElevator(Resident passenger) {
        int passengerDirection;
        if (passenger.from_floor < passenger.to_floor) {
            passengerDirection = 1;
        } else {
            passengerDirection = -1;
        }
        if (elevators.getFirst().currentFloor == passenger.from_floor && passengerDirection == elevators.getFirst().direction) {
            passenger.entered = true;
            elevators.getFirst().addPassenger(passenger);
        } else if (elevators.getLast().currentFloor == passenger.from_floor && passengerDirection == elevators.getLast().direction) {
            passenger.entered = true;
            elevators.getLast().addPassenger(passenger);
        } else if (elevators.getFirst().direction == elevators.getLast().direction) {
            if (Math.abs(elevators.getFirst().currentFloor - passenger.from_floor) <= Math.abs(elevators.getLast().currentFloor - passenger.from_floor)) {
                elevators.getFirst().addPassenger(passenger);
            } else {
                elevators.getLast().addPassenger(passenger);
            }
        } else if (elevators.getFirst().direction != 0 && elevators.getLast().direction == 0) {
            elevators.getLast().addPassenger(passenger);
        } else if (elevators.getFirst().direction == 0 && elevators.getLast().direction != 0) {
            elevators.getFirst().addPassenger(passenger);
        } else {
            if (Math.abs(elevators.getFirst().currentFloor - passenger.from_floor) <= Math.abs(elevators.getLast().currentFloor - passenger.from_floor)) {
                elevators.getFirst().addPassenger(passenger);
            } else {
                elevators.getLast().addPassenger(passenger);
            }
        }


    }


}