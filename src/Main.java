import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static Queue<Integer> floorQueue = new LinkedList();
    private static final int waitDoorsInSeconds = 10;
    private static final int waitMoveInSeconds = 5;
    private static int previousFloor = -1;
    private static int totalSeconds = 0;

    public static void main(String[] args) {
        lift();
    }

    public static void lift() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Ожидаю ввода этажа: (для завершения введите 0)");
            String input = scanner.nextLine();

            if ("0".equals(input)) {
                break;
            }

            try {
                int floor = Integer.parseInt(input);
                addFloorToQueue(floor);
            } catch(NumberFormatException err) {
                System.out.println("Введено не число! Повторите ввод");
            }
        }
        showQueue();
    }

    public static void addFloorToQueue(int floor) {
        if (floor > 25 || floor < 1) {
            System.out.println("Такого этажа нет в доме");
            return;
        }
        floorQueue.offer(floor);
    }

    public static void showQueue() {
        StringBuilder sb = new StringBuilder();
        while(floorQueue.peek() != null) {
            calcTripTime(floorQueue.peek());
            if (!sb.isEmpty()) {
                sb.append( " -> ");
            }
            sb.append("этаж " +  floorQueue.poll());
        }
        System.out.println(sb);
        System.out.println("Время затраченное лифтом на маршрут: " + totalSeconds + " с.");
    }

    public static void calcTripTime(int floor) {
        if (previousFloor != -1) {
            totalSeconds += Math.abs(floor - previousFloor) * waitMoveInSeconds + waitDoorsInSeconds;
        } else {
            totalSeconds = floor * waitMoveInSeconds + waitDoorsInSeconds;
        }
        previousFloor = floor;
    }
}
