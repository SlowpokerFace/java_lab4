import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Resident> waiters = new ArrayList<Resident>();
        Random random = new Random();
        Elevator elevator1 = new Elevator(1000, 1, 0);
        Elevator elevator2 = new Elevator(450, 1, 0);
        int floorCount = random.nextInt(3, 25); // выбираем количество этажей от 3 до 24
        Building building = new Building(floorCount, new ArrayList<>());
        building.elevators.add(elevator1);
        building.elevators.add(elevator2);

        for (int i = 0; i < random.nextInt(3, 13); i++) { // выбирается случайное количество пассажиров (от 3 до 12 вкл.)
            int w = random.nextInt(30, 121); // выбирается рандомный вес, пока что он ни на что не влияет
            int from = random.nextInt(1, building.floorNumber + 1); // выбирается, с какого этажа едет человек
            int to = random.nextInt(1, building.floorNumber + 1); // выбирается, на какой этаж едет человек
            if (from != to && from != 1) { // проверка, чтобы начальный и кончный этаж не совпадают
                waiters.add(new Resident(w, from, to));
            } else if (from != to) {
                waiters.add(new Resident(w, from, to, true));
            }
        }

        for (Resident elem : waiters) {
            elem.info(); // вывод информации о сгенерированных жителях, ожидающих лифт
        }

        System.out.println("///////////////");
        while (!(building.elevators.getFirst().passengers.isEmpty() && building.elevators.getLast().passengers.isEmpty() && waiters.isEmpty())) {
            // запускаем цикл, который работает до тех пор, пока кто-то есть в лифтах или в очереди
            if (!waiters.isEmpty()) { // проверка очереди на непустоту
                building.chooseElevator(waiters.getFirst()); // если не пустая, то распределяем человека в подходящий лифт
                waiters.remove(waiters.getFirst()); // распределив, удаляем из очереди
            }

            Thread thread1 = new Thread(() -> { // создаем первый поток
                if (!building.elevators.getFirst().passengers.isEmpty()) { // проверка на непустоту
                    System.out.print("first elevator: ");
                    building.elevators.getFirst().moveElevator(); // просчитываем действие лифта
                    for (int i = 0; i < floorCount; i++) { // скромная визуализация в консоли
                        System.out.printf("floor%d#", floorCount - i);
                        if (floorCount - i == building.elevators.getFirst().currentFloor) {
                            System.out.print(" 1");
                            switch (building.elevators.getFirst().direction) {
                                case 1:
                                    System.out.print("↑");
                                    break;
                                case -1:
                                    System.out.print("↓");
                                    break;
                            }
                        }

                        if (floorCount - i == building.elevators.getLast().currentFloor) {
                            System.out.print(" 2");
                            switch (building.elevators.getLast().direction) {
                                case 1:
                                    System.out.print("↑");
                                    break;
                                case -1:
                                    System.out.print("↓");
                                    break;
                            }

                        }
                        System.out.print("\n");
                    }

                }
            });

            Thread thread2 = new Thread(() -> { // создаем второй поток, далее аналогично
                if (!building.elevators.getLast().passengers.isEmpty()) {
                    System.out.print("second elevator: ");
                    building.elevators.getLast().moveElevator();
                    for (int i = 0; i < floorCount; i++) {
                        System.out.printf("floor%d#", floorCount - i);
                        if (floorCount - i == building.elevators.getFirst().currentFloor) {
                            System.out.print(" 1");
                            switch (building.elevators.getFirst().direction) {
                                case 1:
                                    System.out.print("↑");
                                    break;
                                case -1:
                                    System.out.print("↓");
                                    break;
                            }

                        }
                        if (floorCount - i == building.elevators.getLast().currentFloor) {
                            System.out.print(" 2");
                            switch (building.elevators.getLast().direction) {
                                case 1:
                                    System.out.print("↑");
                                    break;
                                case -1:
                                    System.out.print("↓");
                                    break;
                            }
                        }

                        System.out.print("\n");
                    }

                }

            });

            thread1.start(); // запускаем первый поток
            try {
                Thread.sleep(10); // так как потоки действительно работают параллельно,
            } catch (InterruptedException e) { // добавляем ожидание на 10 мс, чтобы
                throw new RuntimeException(e); // вывод в консоль выглядел нормальным образом
            } // если убрать sleep, можно убедиться в параллельности потоков
            thread2.start(); // запускаем второй поток
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                thread1.join(); // Ждем, пока первый поток завершится
                thread2.join(); // Ждем, пока второй поток завершится
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}