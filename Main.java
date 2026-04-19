import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Scene currentScene = new Intro();

        currentScene.displayScene();

        while (!currentScene.isCompleted()) {
            System.out.print("\nWhat do you want to do? ");
            String command = input.nextLine();
            currentScene.handleCommand(command);
        }

        input.close();
    }
}