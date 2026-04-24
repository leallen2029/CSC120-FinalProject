import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Player player = new Player();

        Scene currentScene = new Intro(player);
        currentScene.displayScene();
        ((Intro) currentScene).showIntroText();
        System.out.println("\n(Type 'help' at any time to see available commands.)");
        System.out.println("Tip: Type 'write <your note>' to add something to your journal.");
        System.out.println("Type 'journal' to read your journal.");

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
                else if (currentScene instanceof BakerSt) {
                    currentScene = new CabTrap(player);
                    currentScene.displayScene();
                    ((CabTrap) currentScene).showArrival();
                }
                else {
                    break;
                }
            }
        }
        System.out.println("\n=== GAME OVER ===");
        

        input.close();
    }
}