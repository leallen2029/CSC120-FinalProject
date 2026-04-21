public class Intro extends Scene {

    private String wakingUp;
    private String transportOption;
    private boolean walkingPath;
    private boolean suitcaseFound;
    private boolean lockFound;
    private boolean lockChecked;
    private boolean suitcaseTaken;
    private boolean transportChosen;
/// sets locaiton and gives player the option to walk or take a cab, with different consequences for each choice. Also introduces the pink suitcase as a potential clue that can be found if the player chooses to walk.
    public Intro(Player player) {
    super("Intro", "You wake up in the dark of your 221B Baker Street flat.", player);
        wakingUp = """
        As you pick up the phone, you hear Detective Inspector Lestrade
        tell you there has been another death that he wants you to come look at.
        You get out of bed and get ready to go to the crime scene.
        You grab your coat, hat, and John Watson, heading out the door.
        """;

        transportOption = "How would you like to get to the crime scene? You can take a cab or walk";
/// sets options flase so you can discover these things as you play through the scene, and they will be used to determine what happens in later scenes based on the player's choices in this scene.
        walkingPath = false;
        suitcaseFound = false;
        lockFound = false;
        lockChecked = false;
        suitcaseTaken = false;
        transportChosen = false;
    }
/// displays text
    public void showIntroText() {
        System.out.println(wakingUp);
        System.out.println(transportOption);
    }
/// handles the player's choice of transport and the consequences of that choice, as well as allowing the player to look around and discover the suitcase if they chose to walk.
    public void chooseTransport(String transportChoice) {
    if (transportChoice.equalsIgnoreCase("cab")) {
        walkingPath = false;
        getPlayer().setTookCab(true);

        System.out.println("You hail a cab and head to the crime scene.");
        System.out.println("You spot a pink suitcase in an alleyway, but it is too far away to inspect.");
        System.out.println("You have arrived at the crime scene.");
        completeScene();
    }
    else if (transportChoice.equalsIgnoreCase("walk")) {
        walkingPath = true;
        getPlayer().setTookCab(false);

        System.out.println("You decide to walk to the crime scene.");
        System.out.println("As you walk, something catches your eye in a nearby alley.");
        System.out.println("You might want to look around.");
}
        else {
            System.out.println("Invalid choice. Please choose 'cab' or 'walk'.");
        }
    }
/// deals with commands on the way to the house
    @Override
    public void lookAround() {
        setLookedAround(true);

        if (walkingPath) {
            if (!suitcaseFound) {
                System.out.println("You take a careful look around the alley.");
                System.out.println("You notice a pink suitcase sitting by itself near the wall.");
                suitcaseFound = true;
            } else {
                System.out.println("You look around again, but nothing else stands out beyond the suitcase.");
            }
        } else {
            System.out.println("221B Baker Street is dim and cluttered.");
            System.out.println("Watson is waking up, and Lestrade's call still lingers in your mind.");
        }
    }
/// ensures the player knows to look around before inspecting 
    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("You should look around first before inspecting anything.");
            return;
        }

        if (target.equalsIgnoreCase("suitcase")) {
            if (suitcaseFound && !lockFound) {
                System.out.println("It is a pink suitcase with a lock on it.");
                lockFound = true;
            } else if (suitcaseFound) {
                System.out.println("You do not notice anything else about the suitcase.");
            } else {
                System.out.println("There is no suitcase here.");
            }
        } 
        else if (target.equalsIgnoreCase("lock")) {
            if (lockFound && !lockChecked) {
                System.out.println("The lock uses a five-letter code.");
                lockChecked = true;
            } else if (lockFound) {
                System.out.println("You do not notice anything else about the lock.");
            } else {
                System.out.println("You have not noticed a lock yet.");
            }
        } 
        else {
            System.out.println("There is nothing important about the " + target + ".");
        }
    }
/// allows the player to take the suitcase if they found it, and ensures they can't take it multiple times or take it if they haven't found it yet.
    public void takeSuitcase() {
        if (suitcaseFound && !suitcaseTaken) {
            if (getPlayer().takeItem("suitcase")) {
                suitcaseTaken = true;
            }
        } else if (suitcaseTaken) {
            System.out.println("You already took the suitcase.");
        } else {
            System.out.println("There is no suitcase here to take.");
        }
    }
/// allows the player to leave the suitcase if they found it but decide not to take it, and ensures they can't leave it if they haven't found it or if they already took it.
    public void leaveSuitcase() {
        if (suitcaseFound && !suitcaseTaken) {
            System.out.println("You decide to leave the suitcase and continue on.");
            completeScene();
        } else if (suitcaseTaken) {
            System.out.println("You already took the suitcase.");
        } else {
            System.out.println("There is no suitcase here to leave.");
        }
    }

    @Override
    public void go() {
        System.out.println("You continue toward the crime scene.");
        completeScene();
    }
/// commands on the way to house
    @Override
    public void handleCommand(String command) {

        if (!transportChosen) {
            if (command.equalsIgnoreCase("cab") || command.equalsIgnoreCase("walk")) {
                chooseTransport(command);
                transportChosen = true;
            } else {
                System.out.println("Type 'cab' or 'walk' to choose how you travel.");
            }
            return;
        }

        if (command.equalsIgnoreCase("look") || command.equalsIgnoreCase("look around")) {
            lookAround();
        } 
        else if (command.startsWith("inspect ")) {
            String target = command.substring(8);
            inspect(target);
        } 
        else if (command.equalsIgnoreCase("take suitcase")) {
            takeSuitcase();
        } 
        else if (command.equalsIgnoreCase("leave suitcase")) {
            leaveSuitcase();
        } 
        else if (command.equalsIgnoreCase("go") || command.equalsIgnoreCase("continue")) {
            go();
        } 
        else {
            System.out.println("Unknown command.");
        }
    }
}