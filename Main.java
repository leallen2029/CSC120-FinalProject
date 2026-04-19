import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Scene currentScene = new Intro();
        currentScene.displayScene();
        ((Intro) currentScene).showIntroText();

        while (true) {
            System.out.print("\nWhat do you want to do? ");
            String command = input.nextLine();

            currentScene.handleCommand(command);

            if (currentScene.isCompleted()) {
                if (currentScene instanceof Intro) {
                    currentScene = new House();
                    currentScene.displayScene();
                } else {
                    break;
                }
            }
        }

        input.close();
    }
}