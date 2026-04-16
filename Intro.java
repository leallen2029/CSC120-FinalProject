import java.util.Scanner;

public class Intro {
    String wakingUp = """
    You wake up up in the dark of you 221B Baker Street flat. 
    As you pick up the phone as you hear Detective Inspector Lestrade tell you there has been another death that he wants you to come look at. 
    You get out of bed and get ready to go to the crime scene. 
    You grab your coat, hat, and John Watson, heading out the door.
    """;

    public static void main(String[] args) {
        Intro intro = new Intro();
        System.out.println(wakingUp);

        
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


    
}
