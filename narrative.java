import java.util.ArrayList;

public class Narrative {
    ArrayList<String> Narratives = new ArrayList<String>();

    String wakingUp = """
    You wake up up in the dark of you 221B Baker Street flat. 
    As you pick up the phone as you hear Detective Inspector Lestrade tell you there has been another death that he wants you to come look at. 
    You get out of bed and get ready to go to the crime scene. 
    You grab your coat, hat, and John Watson, heading out the door.
    """;

    String transportOption = "How would you like to get to the crime scene? You can take a cab or walk";

    public Narrative() {
        Narratives.add(wakingUp);
        Narratives.add(transportOption);
    }

}