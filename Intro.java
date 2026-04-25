public class Intro extends Scene {

    private String wakingUp;
    private String transportOption;

    private boolean walkingPath;
    private boolean suitcaseFound;
    private boolean lockFound;
    private boolean lockChecked;
    private boolean suitcaseTaken;
    private boolean transportChosen;
    private boolean watsonNoticedSuitcase;

    public Intro(Player player) {
        super("Intro", "You wake up in the dark of your 221B Baker Street flat.", player);

        wakingUp = """
        As you pick up the phone, you hear Detective Inspector Lestrade
        tell you there has been another death that he wants you to come look at.
        You get out of bed and get ready to go to the crime scene.
        You grab your coat, scarf, and John Watson, heading out the door.
        """;

        transportOption = "How would you like to get to the crime scene? You can take a cab or walk.";

        walkingPath = false;
        suitcaseFound = false;
        lockFound = false;
        lockChecked = false;
        suitcaseTaken = false;
        transportChosen = false;
        watsonNoticedSuitcase = false;
    }
    public void showIntroText() {
        System.out.println(wakingUp);
        System.out.println(transportOption);
    }

    
    
    

    public void chooseTransport(String transportChoice) {
        if (transportChoice.equalsIgnoreCase("cab")) {
            walkingPath = false;
            getPlayer().setTookCab(true);

            System.out.println("You hail a cab and head toward the crime scene.");
            System.out.println("Through the window, you briefly spot a pink suitcase abandoned in an alleyway.");
            System.out.println("Before you can react, the cab turns the corner.");
            System.out.println("Maybe you want to write this down? Type 'write journal' to add a note.");
            System.out.println("You have arrived at the crime scene.");
            completeScene();
        }
        else if (transportChoice.equalsIgnoreCase("walk")) {
            walkingPath = true;
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

    @Override
    public void lookAround() {
        setLookedAround(true);

        if (walkingPath) {
            if (!suitcaseFound) {
                System.out.println("You scan the alley carefully.");
                System.out.println("A pink suitcase sits alone near the wall, far too clean to belong there by accident.");
                System.out.println("Watson slows down beside you. \"That seems... odd,\" he says.");
                suitcaseFound = true;
                watsonNoticedSuitcase = true;
            } else {
                System.out.println("You look around the alley again.");
                System.out.println("Nothing else stands out beyond the pink suitcase.");
            }
        } else {
            System.out.println("221B Baker Street is dim and cluttered.");
            System.out.println("Watson is still waking up, and Lestrade's call hangs in the air.");
        }
    }

    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("You should look around first before inspecting anything.");
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
                System.out.println("Maybe you want to write this down? Type 'write journal' to add a note.");
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
                System.out.println("\"Should we take it?\" he asks, clearly unsure whether he wants the answer.");
            } else {
                System.out.println("Watson is watching you, waiting to see what you do next.");
            }
        }
        else {
            System.out.println("There is nothing important about the " + target + ".");
        }
    }

    public void takeSuitcase() {
        if (suitcaseFound && !suitcaseTaken) {
            if (getPlayer().takeItem("suitcase")) {
                suitcaseTaken = true;
                getPlayer().writeNote("I took the pink suitcase from the alley.");
                System.out.println("Watson raises an eyebrow but says nothing.");
            }
        } else if (suitcaseTaken) {
            System.out.println("You already took the suitcase.");
        } else {
            System.out.println("There is no suitcase here to take.");
        }
    }

    public void leaveSuitcase() {
        if (suitcaseFound && !suitcaseTaken) {
            System.out.println("You decide to leave the suitcase where it is.");
            System.out.println("Watson glances back once as you continue toward the crime scene.");
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
        if (walkingPath && suitcaseFound && !suitcaseTaken) {
            System.out.println("You hesitate before leaving.");
            System.out.println("Do you want to take the suitcase or leave it?");
            return;
        }

        System.out.println("You continue toward the crime scene.");
        System.out.println("You have arrived at the house.");
        completeScene();
    }

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
            }
            return;
        }

        if (cmd.equals("look") || cmd.equals("look around")) {
            lookAround();
        }
        else if (cmd.startsWith("inspect ")) {
            String target = command.substring(8).trim();
            inspect(target);
        }
        else if (cmd.equals("take suitcase")) {
            takeSuitcase();
        }
        else if (cmd.equals("leave suitcase")) {
            leaveSuitcase();
        }
        else if (cmd.equals("go") || cmd.equals("continue")) {
            go();
        }
        else {
            System.out.println("Unknown command.");
        }
    }
    @Override
    public void help() {
        super.help();
        System.out.println("\nIntro commands:");
        System.out.println("- cab");
        System.out.println("- walk");
        System.out.println("- take suitcase");
        System.out.println("- leave suitcase");
    }
}