public class Intro extends Scene {

    private String wakingUp;
    private String transportOption;

    private boolean walkingPath;
    private boolean suitcaseFound;
    private boolean lockFound;
    private boolean lockChecked;
    private boolean suitcaseTaken;
    private boolean transportChosen;

    public Intro() {
        super("Intro", "You wake up in the dark of your 221B Baker Street flat.");

        wakingUp = """
        As you pick up the phone, you hear Detective Inspector Lestrade
        tell you there has been another death that he wants you to come look at.
        You get out of bed and get ready to go to the crime scene.
        You grab your coat, hat, and John Watson, heading out the door.
        """;

        transportOption = "How would you like to get to the crime scene? You can take a cab or walk";

        walkingPath = false;
        suitcaseFound = false;
        lockFound = false;
        lockChecked = false;
        suitcaseTaken = false;
    }

    public void showIntroText() {
        System.out.println(wakingUp);
        System.out.println(transportOption);
    }

    public void chooseTransport(String transportChoice) {
        if (transportChoice.equalsIgnoreCase("cab")) {
            walkingPath = false;
            System.out.println("You hail a cab and head to the crime scene.");
            System.out.println("You spot a pink suitcase in an alleyway, but it is too far away to investigate.");
            System.out.println("You have arrived at the crime scene.");
            completeScene();
        } 
        else if (transportChoice.equalsIgnoreCase("walk")) {
            walkingPath = true;
            System.out.println("You decide to walk to the crime scene.");
            System.out.println("As you walk, something catches your eye in a nearby alley.");
            System.out.println("You might want to look around.");
        } 
        else {
            System.out.println("Invalid choice. Please choose 'cab' or 'walk'.");
        }
    }

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

    @Override
    public void lookAround(String target) {
        if (!hasLookedAround()) {
            System.out.println("You should look around first before focusing on something specific.");
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
            System.out.println("You do not notice anything special about the " + target + ".");
        }
    }

    public void takeSuitcase() {
        if (suitcaseFound && !suitcaseTaken) {
            System.out.println("You take the suitcase with you.");
            suitcaseTaken = true;
        } else if (suitcaseTaken) {
            System.out.println("You already took the suitcase.");
        } else {
            System.out.println("There is no suitcase here to take.");
        }
    }

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

    @Override
    public void handleCommand(String command) {

        // FIRST: handle cab/walk
        if (!transportChosen) {
            if (command.equalsIgnoreCase("cab") || command.equalsIgnoreCase("walk")) {
                chooseTransport(command);
                transportChosen = true;
            } else {
                System.out.println("Type 'cab' or 'walk' to choose how you travel.");
            }
            return; // 
        }

        // THEN: normal commands
        if (command.equalsIgnoreCase("look around")) {
            lookAround(); 
        } 
        else if (command.startsWith("inspect ")) {
            String target = command.substring(8);
            lookAround(target);
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