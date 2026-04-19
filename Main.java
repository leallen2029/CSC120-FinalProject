import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Intro intro = new Intro();

        intro.displayScene();
        intro.showIntroText();

        boolean transportChosen = false;

        while (!intro.isCompleted()) {
            System.out.print("\nWhat do you want to do? ");
            String command = input.nextLine();

            if (!transportChosen) {
                if (command.equalsIgnoreCase("cab") || command.equalsIgnoreCase("walk")) {
                    intro.chooseTransport(command);
                    transportChosen = true;
                } else {
                    System.out.println("Type 'cab' or 'walk' to choose how you travel.");
                }
            } else {
                if (command.equalsIgnoreCase("look")) {
                    intro.lookAround();
                } 
                else if (command.startsWith("look around ")) {
                    String target = command.substring(12);
                    intro.lookAround(target);
                } 
                else if (command.equalsIgnoreCase("take suitcase")) {
                    intro.takeSuitcase();
                } 
                else if (command.equalsIgnoreCase("leave suitcase")) {
                    intro.leaveSuitcase();
                } 
                else if (command.equalsIgnoreCase("continue")) {
                    intro.completeScene();
                } 
                else {
                    System.out.println("Unknown command.");
                }
            }
        }

        System.out.println("\nThe intro scene is over.");
        input.close();
    }
}