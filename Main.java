import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Intro currentScene = new Intro();
        currentScene.displayScene();
        currentScene.showIntroText(); 

        while (!currentScene.isCompleted()) {
            System.out.print("\nWhat do you want to do? ");
            String command = input.nextLine();

            currentScene.handleCommand(command);
        }

        input.close();
    }
}