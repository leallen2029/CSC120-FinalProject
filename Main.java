import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Key commands:");
        System.out.println("\n(Type 'help' at any time to see available commands.)");
        System.out.println("Type 'read journal' to read your journal.");
        System.out.println("Type 'write journal' then you can add something to your journal.");
        
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