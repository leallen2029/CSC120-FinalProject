import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create your scene
        House house = new House();

        // Show intro
        house.displayScene();

        while (!house.isCompleted()) {

            System.out.print("\nWhat do you want to do? ");
            String command = input.nextLine();

            if (command.equalsIgnoreCase("look")) {
                house.lookAround();
            }

            else if (command.startsWith("inspect ")) {
                String target = command.substring(8);
                house.inspect(target);
            }

            else if (command.equalsIgnoreCase("continue")) {
                house.completeScene();
            }

            else {
                System.out.println("Unknown command.");
            }
        }

        System.out.println("You leave the house...");

        input.close();
    }
}