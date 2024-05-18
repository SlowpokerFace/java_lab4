import java.util.concurrent.LinkedBlockingQueue;


public class Elevator {
    int max_weight;
    int currentFloor;
    int direction; //1 - up; -1 - down; 0 - stands still
    public LinkedBlockingQueue<Resident> passengers; // Список пассажиров
    public Elevator(int max_weight, int currentFloor, int direction){
        this.max_weight = max_weight;
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.passengers = new LinkedBlockingQueue<Resident>();
    }
    public void addPassenger(Resident passenger) {
        passengers.add(passenger);
    }

    public void removePassenger(Resident passenger) {
        passengers.remove(passenger);
    }

    public LinkedBlockingQueue<Resident> getPassengers() {
        return passengers;
    }
    public void moveElevator(){
        if (this.passengers.isEmpty()) {
            System.out.println("there is no passengers");
            return;
        }
        if (this.direction == 0){
            if (this.currentFloor < this.passengers.peek().from_floor){
                this.direction = 1;
                System.out.println("elevator goes up");
            }
            else if (this.currentFloor > this.passengers.peek().from_floor){
                this.direction = -1;
                System.out.println("elevator goes down");
            }
            else if (this.currentFloor == this.passengers.peek().from_floor){
                if (this.currentFloor < this.passengers.peek().to_floor){
                    this.direction = 1;
                    System.out.println("elevator goes up");
                }
                else if (this.currentFloor > this.passengers.peek().to_floor){
                    this.direction = -1;
                    System.out.println("elevator goes down");
                }
            }
        }
        else{
            if (this.currentFloor == this.passengers.peek().from_floor) {
                this.currentFloor = this.passengers.peek().to_floor;
                this.direction = 0;
                System.out.printf("floor now is %d\n", this.currentFloor);
                this.removePassenger(this.passengers.peek());
            }
            else{
                this.currentFloor = this.passengers.peek().from_floor;
                this.direction = 0;
                System.out.printf("floor now is %d\n", this.currentFloor);
            }
        }
    }
}
