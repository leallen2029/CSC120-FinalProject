import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Player player = new Player();

        Scene currentScene = new Intro(player);
        currentScene.displayScene();
        ((Intro) currentScene).showIntroText();

        while (true) {
            System.out.print("\nWhat do you want to do? ");
            String command = input.nextLine();

            currentScene.handleCommand(command);

            if (currentScene.isCompleted()) {
                if (currentScene instanceof Intro) {
                    currentScene = new House(player);
                    currentScene.displayScene();
                } 
                else if (currentScene instanceof House) {
                    currentScene = new BakerSt(player);
                    currentScene.displayScene();
                    ((BakerSt) currentScene).showArrival();
                } 
                else {
                    break;
                }
            }
        }

        input.close();
    }
}