import java.util.Scanner;

public class Intro implements narrative {
    public static void main(String[] args) {
        System.out.println(narratives.get(0));
        System.out.println(narratives.get(1));
        Scanner input = new Scanner(System.in);
        String transportChoice = input.nextLine();
        if (transportChoice.equalsIgnoreCase("cab")) {
            System.out.println("You hail a cab and head to the crime scene.");
        } else if (transportChoice.equalsIgnoreCase("walk")) {
            System.out.println("You decide to walk to the crime scene, taking in the sights of London along the way.");
        } else {
            System.out.println("Invalid choice. Please choose 'cab' or 'walk'.");
        }
        input.close();
    }

    
}
