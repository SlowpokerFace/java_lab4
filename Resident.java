public class Resident{
    int weight;
    int from_floor;
    int to_floor;
    boolean entered;
    void info() {
        System.out.printf("weight: %d; from %d floor; to %d floor\n", weight, from_floor, to_floor);}
    Resident (int weight, int from_floor, int to_floor, boolean entered){
        this.weight = weight;
        this.from_floor = from_floor;
        this.to_floor = to_floor;
        this.entered = entered;
    }
    Resident (int weight, int from_floor, int to_floor){
        this.weight = weight;
        this.from_floor = from_floor;
        this.to_floor = to_floor;
        this.entered = false;
    }
}
