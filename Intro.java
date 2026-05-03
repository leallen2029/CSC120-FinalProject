public class Intro extends Scene {
// attributes
    private String wakingUp;
    private String transportOption;
    private boolean walkingPath;
    private boolean suitcaseFound;
    private boolean lockFound;
    private boolean lockChecked;
    private boolean suitcaseTaken;
    private boolean transportChosen;
    private boolean watsonNoticedSuitcase;
    private boolean inCab;

    public Intro(Player player) {
        super("Intro", "You wake up in the dark of your 221B Baker Street flat.", player);
       
        wakingUp = """
        As you pick up the phone, you hear Detective Inspector Lestrade
        tell you there has been another death that he wants you to come look at.
        You get out of bed and get ready to go to the crime scene.
        You grab your coat, scarf, and John Watson, heading out the door.
        """;

        transportOption = "How would you like to get to the crime scene? You can take a cab or walk.";
// constructor
        walkingPath = false;
        suitcaseFound = false;
        lockFound = false;
        lockChecked = false;
        suitcaseTaken = false;
        transportChosen = false;
        watsonNoticedSuitcase = false;
        inCab = false;
    }
// prints intro
    public void showIntroText() {
        System.out.println(wakingUp);
        System.out.println(transportOption);
    }
// helps reader make choices about transportation, which will affect how they experience the scene and what clues they encounter on the way to the crime scene. The player's choice will influence how they interact with Watson and what they notice about their surroundings, setting the stage for how they approach the investigation and what information they gather along the way.
    public void chooseTransport(String transportChoice) {
        if (transportChoice.equalsIgnoreCase("cab")) {
            walkingPath = false;
            inCab = true;
            getPlayer().setTookCab(true);

            System.out.println("You hail a cab and head toward the crime scene.");
            System.out.println("Rain streaks across the window as the city slides past.");
            System.out.println("You can look around, inspect Watson, or go when you are ready.");
        }
        else if (transportChoice.equalsIgnoreCase("walk")) {
            walkingPath = true;
            inCab = false;
            getPlayer().setTookCab(false);

            System.out.println("You decide to walk to the crime scene.");
            System.out.println("Watson complains about his leg but follows anyway.");
            System.out.println("As you pass an alleyway, something bright catches your eye.");
            System.out.println("You might want to look around.");
        }
        else {
            System.out.println("Invalid choice. Please choose 'cab' or 'walk'.");
        }
    }
// overrides look around to provide specific descriptions based on the player's transportation choice, which will help them notice important details and set the tone for their investigation. The player's observations will influence how they approach the crime scene and what clues they prioritize as they begin their investigation.
    @Override
    public void lookAround() {
        setLookedAround(true);

        if (inCab) {
            System.out.println("Rain streaks across the cab window.");
            System.out.println("Watson sits beside you, watching the city blur past.");
            System.out.println("For one moment, a pink suitcase flashes in the alley behind you.");
            System.out.println("It is strange enough that you may want to remember it.");
            getPlayer().setSawSuitcase(true);
            suitcaseFound = true;
            watsonNoticedSuitcase = true;
        }
        else if (walkingPath) {
            if (!suitcaseFound) {
                System.out.println("You scan the alley carefully.");
                System.out.println("A pink suitcase sits alone near the wall, far too clean to belong there by accident.");
                System.out.println("Watson slows down beside you. \"That seems... odd,\" he says.");
                System.out.println("You might want to inspect the suitcase.");
                getPlayer().setSawSuitcase(true);
                suitcaseFound = true;
                watsonNoticedSuitcase = true;
            } else {
                System.out.println("You look around the alley again.");
                System.out.println("Nothing else stands out beyond the pink suitcase.");
            }
        }
        else {
            System.out.println("221B Baker Street is dim and cluttered.");
            System.out.println("Watson is still waking up, and Lestrade's call hangs in the air.");
        }
    }
// overrides inspect to provide interactions based on the player's transportation choice and what they have observed, which will help them gather important clues and make informed decisions as they approach the crime scene.
    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("You should look around first before inspecting anything.");
            return;
        }

        if (inCab) {
            if (target.equalsIgnoreCase("suitcase")) {
                System.out.println("You only saw it for a second through the cab window: pink, abandoned, and out of place.");
                getPlayer().setSawSuitcase(true);
                suitcaseFound = true;
                watsonNoticedSuitcase = true;
            }
            else if (target.equalsIgnoreCase("watson")) {
                System.out.println("Watson looks uneasy. \"Did you see something back there?\" he asks.");
            }
            else {
                System.out.println("There is not much to inspect from inside the moving cab.");
            }
            return;
        }

        if (target.equalsIgnoreCase("suitcase")) {
            if (suitcaseFound && !lockFound) {
                System.out.println("The suitcase is pink, expensive-looking, and abandoned.");
                System.out.println("There is a small lock built into the handle.");
                System.out.println("This does not feel random.");
                lockFound = true;
            } else if (suitcaseFound) {
                System.out.println("The suitcase is still there. The lock is the only detail that matters now.");
            } else {
                System.out.println("There is no suitcase here.");
            }
        }
        else if (target.equalsIgnoreCase("lock")) {
            if (lockFound && !lockChecked) {
                System.out.println("The lock needs a five-letter code.");
                System.out.println("Maybe you want to write this down.");
                getPlayer().writeNote("The pink suitcase has a five-letter lock.");
                lockChecked = true;
            } else if (lockFound) {
                System.out.println("The lock still needs a five-letter code.");
            } else {
                System.out.println("You have not noticed a lock yet.");
            }
        }
        else if (target.equalsIgnoreCase("watson")) {
            if (watsonNoticedSuitcase) {
                System.out.println("Watson looks from you to the suitcase.");
                System.out.println("\"Should we do something about it?\" he asks.");
            } else {
                System.out.println("Watson is watching you, waiting to see what you do next.");
            }
        }
        else {
            System.out.println("There is nothing important about the " + target + ".");
        }
    }
// the following methods allow the player to interact with the suitcase they find, giving them the option to take it with them or leave it behind.
    public void takeSuitcase() {
        if (inCab) {
            System.out.println("You only caught a glimpse of it from the cab. You can't stop now.");
            return;
        }
        if (suitcaseFound && !suitcaseTaken) {
            if (getPlayer().takeItem("suitcase")) {
                suitcaseTaken = true;
            }
        } 
        else if (suitcaseTaken) {
            System.out.println("You already took the suitcase.");
        } 
        else {
            System.out.println("There is no suitcase here to take.");
        }
    }

    public void leaveSuitcase() {
        if (suitcaseFound && !suitcaseTaken) {
            System.out.println("You decide to leave the suitcase where it is.");
            System.out.println("Watson glances back once as you go toward the crime scene.");
            getPlayer().writeNote("I saw a pink suitcase in the alley but left it behind.");
            completeScene();
        } else if (suitcaseTaken) {
            System.out.println("You already took the suitcase.");
        } else {
            System.out.println("There is no suitcase here to leave.");
        }
    }

    @Override
    public void go() {
        if (inCab) {
            System.out.println("The cab pulls up outside the crime scene.");
            System.out.println("You step out with Watson and approach the house.");
            completeScene();
            return;
        }

        if (walkingPath && suitcaseFound && !suitcaseTaken) {
            System.out.println("You hesitate before leaving.");
            System.out.println("Do you want to take the suitcase or leave it?");
            return;
        }

        System.out.println("You go toward the crime scene.");
        System.out.println("You have arrived at the house.");
        completeScene();
    }
// makes basic commands scene-specific
    @Override
    public void handleCommand(String command) {
        if (handleBasicCommand(command)) {
            return;
        }

        String cmd = command.toLowerCase().trim();

        if (!transportChosen) {
            if (cmd.equals("cab") || cmd.equals("walk")) {
                chooseTransport(cmd);
                transportChosen = true;
            } else {
                System.out.println("Type 'cab' or 'walk' to choose how you travel.");
                help();
            }
            return;
        }

        if (cmd.equals("look") || cmd.equals("look around")) {
            lookAround();
        }
        else if (cmd.startsWith("inspect ")) {
            String target = cmd.substring(8).trim();
            inspect(target);
        }
        else if (cmd.equals("take suitcase")) {
            takeSuitcase();
        }
        else if (cmd.equals("leave suitcase")) {
            leaveSuitcase();
        }
        else if (cmd.equals("go")) {
            go();
        }
        else {
            System.out.println("That does not help right now.");
            help();
        }
    }
// makes help for the most individuated parts of the scene so the reader can get guidance if need be.
    @Override
    public void help() {
        System.out.println("\nYou pause and consider your options...");

        if (!transportChosen) {
            System.out.println("- cab");
            System.out.println("- walk");
        }
        else if (inCab && !suitcaseFound) {
            System.out.println("- look around");
            System.out.println("- inspect watson");
            System.out.println("- go");
        }
        else if (inCab && suitcaseFound && !suitcaseTaken) {
            System.out.println("- inspect suitcase");
            System.out.println("- go");
        }
        else if (walkingPath && !suitcaseFound) {
            System.out.println("- look around");
        }
        else if (suitcaseFound && !lockFound && !suitcaseTaken) {
            System.out.println("- inspect suitcase");
            System.out.println("- take suitcase");
            System.out.println("- leave suitcase");
            System.out.println("- go");
        }
        else if (lockFound && !lockChecked && !suitcaseTaken) {
            System.out.println("- inspect lock");
            System.out.println("- take suitcase");
            System.out.println("- leave suitcase");
            System.out.println("- go");
        }
        else if (suitcaseFound && !suitcaseTaken) {
            System.out.println("- take suitcase");
            System.out.println("- leave suitcase");
            System.out.println("- go");
        }
        else {
            System.out.println("- go");
        }
    }
}