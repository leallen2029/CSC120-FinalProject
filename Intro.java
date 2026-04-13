import java.util.Scanner;

public class Intro extends narrative {
    public static void main(String[] args) {
        Intro intro = new Intro();
        System.out.println(intro.narratives.get(0));
        System.out.println(intro.narratives.get(1));
        Scanner input = new Scanner(System.in);
        String transportChoice = input.nextLine();
        if (transportChoice.equalsIgnoreCase("cab")) {
            System.out.println("You hail a cab and head to the crime scene. You spot a pink suitcase in an alleyway but it's too far away to investigate.");
        } else if (transportChoice.equalsIgnoreCase("walk")) {
            System.out.println("You decide to walk to the crime scene. As you walk, you notice a pink suitcase in an alleyway. Do you want to investigate it? (yes/no)");
            String investigateChoice = input.nextLine();
            if (investigateChoice.equalsIgnoreCase("yes")) {
                System.out.println("What do you want to do with the suitcase?");
                
            } else {
                System.out.println("You decide not to investigate the suitcase and continue to the crime scene.");
            }
        } else {
            System.out.println("Invalid choice. Please choose 'cab' or 'walk'.");
        }
        input.close();
    }

    
}
