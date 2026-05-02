public class House extends Scene {

    private String location;
    private boolean talkedToLestrade;
    private boolean inspectedFloor;
    private boolean inspectedNails;
    private boolean inspectedHands;
    private boolean inspectedFace;
    private boolean inspectedClothing;
    private boolean inspectedBedroom;
    private boolean waitingForLeaveChoice;
    private boolean inspectedTable;
    private boolean inspectedStairs;
    private boolean inspectedCoat;
    private boolean noticedDonovan;
    private boolean noticedTape;
    private boolean noticedWindow;
    private boolean noticedCrowd;
    private boolean pickpocketedDonovan;

/// sets location and tracks the player's interactions with Lestrade, as well as their choice to run out of the house and whether they go back for the suitcase or not.
    public House(Player player) {
        super("House", "You arrive outside the house where the death occurred. You might want to look around.", player);
        location = "outside";
        talkedToLestrade = false;
        inspectedFloor = false;
        inspectedNails = false;
        inspectedHands = false;
        inspectedFace = false;
        inspectedClothing = false;
        inspectedBedroom = false;
        waitingForLeaveChoice = false;
        noticedDonovan = false;
        noticedTape = false;
        noticedWindow = false;
        noticedCrowd = false;
        pickpocketedDonovan = false;
        inspectedTable = false;
        inspectedStairs = false;
        inspectedCoat = false;

        System.out.println("Stepping under the crime scene tape, you find yourself standing outside the house where the death occurred.");
    }

/// handles the player's interactions with the house, including looking around, inspecting specific details, talking to Lestrade, exploring the outside area, leaving the house, and moving through the different areas of the house. The player's choices and interactions will affect how the scene progresses and what information they uncover.
    @Override
    public void lookAround() {
        setLookedAround(true);

        if (location.equals("outside")) {
            System.out.println("Watson shifts beside you. \"Not a pleasant place,\" he mutters.");
            System.out.println("Donovan is pacing the yard, watching everything.");
            System.out.println("The police tape flutters in the wind.");
            System.out.println("Across the street, a few neighbors whisper and stare.");
            System.out.println("One upstairs window is slightly open.");
            System.out.println("The front door stands ajar. You can go inside whenever you're ready.");
        }

        else if (location.equals("downstairs")) {
            System.out.println("You step into the house and let your eyes adjust.");
            System.out.println("Officers move quietly, trying not to disturb anything.");
            System.out.println("A small table sits against the wall, cluttered with notes.");
            System.out.println("A coat hangs by the door, still slightly damp.");
            System.out.println("The staircase leads upward into darkness.");
            System.out.println("Lestrade stands nearby, waiting for you to say something.");
        }

        else if (location.equals("stairwell")) {
            System.out.println("The narrow stairs creak beneath your weight.");
            System.out.println("The house feels tighter here, more suffocating.");
        }

        else if (location.equals("murderroom")) {
            System.out.println("You enter the room where it happened.");
            System.out.println("The body lies still. The silence presses in.");
            System.out.println("The floor, the body, and the room itself demand your attention.");
        }
    }

/// lets the player notice details outside the house without forcing them to follow one exact path.
    @Override
    public void notice(String target) {
        if (!location.equals("outside")) {
            System.out.println("Nothing new stands out right now.");
            return;
        }

        if (target.equalsIgnoreCase("donovan")) {
            System.out.println("Donovan keeps one hand near her coat pocket while pretending not to watch you.");
            System.out.println("Whatever is in there, she does not want attention drawn to it.");
            noticedDonovan = true;
        }
        else if (target.equalsIgnoreCase("tape")) {
            System.out.println("The police tape has been shifted and retied.");
            System.out.println("Someone crossed the boundary after the scene was supposed to be sealed.");
            noticedTape = true;
        }
        else if (target.equalsIgnoreCase("window")) {
            System.out.println("The upstairs window is cracked open.");
            System.out.println("Not wide enough for an easy escape, but enough to suggest someone wanted air.");
            noticedWindow = true;
        }
        else if (target.equalsIgnoreCase("crowd") || target.equalsIgnoreCase("neighbors")) {
            System.out.println("The neighbors pretend not to stare.");
            System.out.println("One of them stops whispering every time you look over.");
            noticedCrowd = true;
        }
        else {
            System.out.println("You do not notice anything useful about the " + target + ".");
        }
    }

/// allows the player to take a risky action outside if they noticed the right detail first.
    public void pickpocket(String target) {
        if (!location.equals("outside")) {
            System.out.println("There is no one here to pickpocket.");
            return;
        }

        if (target.equalsIgnoreCase("donovan")) {
            if (!noticedDonovan) {
                System.out.println("You need to pay closer attention to Donovan first.");
                return;
            }

            if (pickpocketedDonovan) {
                System.out.println("You already took what you could from Donovan.");
                return;
            }

            System.out.println("While Donovan is distracted, you slip a folded police note from her coat pocket.");
            getPlayer().takeItem("police note");
            getPlayer().writeNote("Donovan had a police note about the victim.");
            pickpocketedDonovan = true;
        }
        else {
            System.out.println("You cannot pickpocket " + target + ".");
        }
    }

/// ensures the player knows to look around before inspecting and provides different descriptions based on the player's location in the house and what they choose to inspect.
    @Override
    public void inspect(String target) {
        if (!hasLookedAround() && !location.equals("outside")) {
            System.out.println("Look around first.");
            return;
        }

        if (location.equals("outside")) {
            if (target.equalsIgnoreCase("door")) {
                System.out.println("The front door is slightly open. You can go inside whenever you are ready.");
            }
            else if (target.equalsIgnoreCase("donovan")) {
                notice("donovan");
            }
            else if (target.equalsIgnoreCase("tape")) {
                notice("tape");
            }
            else if (target.equalsIgnoreCase("window")) {
                notice("window");
            }
            else if (target.equalsIgnoreCase("crowd") || target.equalsIgnoreCase("neighbors")) {
                notice("crowd");
            }
            else {
                System.out.println("Nothing useful stands out about the " + target + ".");
            }
        }
        else if (location.equals("murderroom")) {
            if (target.equalsIgnoreCase("body")) {
                System.out.println("The body lies faced down. The victim is dressed in a pink trench coat.");
                System.out.println("What else stands out to you about the body? You can inspect the hands, face, nails, or clothing.");
            } 
            else if (target.equalsIgnoreCase("floor")) {
                System.out.println("There are marks on the floor next to the body.");
                System.out.println("It looks like someone carved the letters RACHE into the floor. How did they get there?");
                inspectedFloor = true;
                resetLeaveWarning();
            } 
            else if (target.equalsIgnoreCase("nails")) {
                System.out.println("You look closely at the victim's nails and notice they are broken.");
                System.out.println("It appears that carving the letters may have been the last thing she did.");
                inspectedNails = true;
                resetLeaveWarning();
            }
            else if (target.equalsIgnoreCase("hands")) {
                System.out.println("The victim's hands look strained, as if she used the last of her strength to leave something behind.");
                inspectedHands = true;
                resetLeaveWarning();
            }
            else if (target.equalsIgnoreCase("face")) {
                System.out.println("Her expression is tense and frightened, frozen in her final moment.");
                inspectedFace = true;
                resetLeaveWarning();
            }
            else if (target.equalsIgnoreCase("clothing")) {
                System.out.println("Her clothing is disordered, but not enough to suggest a direct struggle.");
                inspectedClothing = true;
                resetLeaveWarning();
            }
            else if (target.equalsIgnoreCase("bedroom")) {
                System.out.println("The bedroom looks disturbed, but not randomly.");
                inspectedBedroom = true;
                resetLeaveWarning();
            } 
            else {
                System.out.println("Nothing important there.");
            }
        } 
        else {
            System.out.println("There is nothing specific to inspect here right now.");
        }
    }

    public void talkToLestrade() {
        if (location.equals("downstairs") && !talkedToLestrade) {
            System.out.println("Lestrade lowers his voice.");
            System.out.println("\"Victim's upstairs,\" he says. \"No sign of forced entry. No sign of struggle either.\"");
            System.out.println("\"Same pattern as the others. We kept everyone back for you.\"");
            talkedToLestrade = true;
        } 
        else if (location.equals("stairwell")) {
            System.out.println("As you climb, Lestrade adds, \"Still no witnesses. And nobody saw anyone leave.\"");
        } 
        else if (location.equals("murderroom")) {
            System.out.println("Lestrade glances at the body. \"This is what we called you in for,\" he says.");
        } 
        else {
            System.out.println("Lestrade has nothing new to add right now.");
        }
    }

/// deals with the player leaving the house, ensuring they uncover at least some things    
    public void leave(String choice) {
        if (!location.equals("murderroom")) {
            System.out.println("You can only leave from the murder room.");
            return;
        }

        if (!confirmLeaveIfNeeded()) {
            return;
        }

        if (choice.equals("ask")) {
            waitingForLeaveChoice = true;
            System.out.println("Where do you want to go?");

            if (getPlayer().hasItem("suitcase") || getPlayer().hasReturnedToSuitcase()) {
                System.out.println("- leave with watson");
                System.out.println("- leave without watson");
            } else {
                System.out.println("- leave with watson");
                System.out.println("- leave without watson");
                System.out.println("- return suitcase with watson");
                System.out.println("- return suitcase without watson");
            }

            return;
        }

        if (choice.equals("leave with watson")) {
            waitingForLeaveChoice = false;
            System.out.println("You leave calmly with Watson and return to Baker Street.");
            getPlayer().setRanOut(false);
            completeScene();
        }
        else if (choice.equals("leave without watson")) {
            waitingForLeaveChoice = false;
            System.out.println("You rush out without Watson, your mind racing.");
            getPlayer().setRanOut(true);
            completeScene();
        }
        else if (choice.equals("return suitcase with watson")) {
            waitingForLeaveChoice = false;
            System.out.println("You and Watson return to the alley and recover the suitcase.");
            getPlayer().setRanOut(false);
            getPlayer().setReturnedToSuitcase(true);
            getPlayer().takeItem("suitcase");
            completeScene();
        }
        else if (choice.equals("return suitcase without watson")) {
            waitingForLeaveChoice = false;
            System.out.println("You run back alone and recover the suitcase.");
            getPlayer().setRanOut(true);
            getPlayer().setReturnedToSuitcase(true);
            getPlayer().takeItem("suitcase");
            completeScene();
        }
        else {
            System.out.println("Choose one of the listed options.");
        }
    }

/// changes go() to fit the house situation
    @Override
    public void go() {
        if (location.equals("outside")) {
            System.out.println("You walk through the front door and into the house.");
            location = "downstairs";
            setLookedAround(false);
        } 
        else if (location.equals("downstairs")) {
            if (!talkedToLestrade) {
                System.out.println("Before you head farther in, Lestrade steps toward you.");
                talkToLestrade();
                return;
            }

            System.out.println("Lestrade gestures toward the stairs, and you, Watson, and Lestrade begin to head up.");
            location = "stairwell";
            setLookedAround(false);
        } 
        else if (location.equals("stairwell")) {
            System.out.println("You follow Lestrade and Watson into the room of the murder.");
            location = "murderroom";
            setLookedAround(false);
        } 
        else if (location.equals("murderroom")) {
            System.out.println("You are already in the murder room.");
        }
    }

    @Override
    public void handleCommand(String command) {
        if (handleBasicCommand(command)) {
            return;
        }

        String cmd = command.toLowerCase().trim();

        if (waitingForLeaveChoice) {
            leave(cmd);
            return;
        }

        if (cmd.equals("look") || cmd.equals("look around")) {
            lookAround();
        } 
        else if (cmd.startsWith("inspect ")) {
            String target = cmd.substring(8).trim();
            inspect(target);
        }
        else if (cmd.startsWith("notice ")) {
            String target = cmd.substring(7).trim();
            notice(target);
        }
        else if (cmd.startsWith("pickpocket ")) {
            String target = cmd.substring(11).trim();
            pickpocket(target);
        }
        else if (cmd.equals("talk lestrade")) {
            talkToLestrade();
        } 
        else if (cmd.equals("leave")) {
            leave("ask");
        }
        else if (cmd.equals("go")
                || cmd.equals("go upstairs")
                || cmd.equals("go inside")
                || cmd.equals("go through door")
                || cmd.equals("enter")
                || cmd.equals("enter house")) {
            go();
        }
        else if (cmd.startsWith("go ")) {
            go();
        }
        else {
            super.handleCommand(command);
        }
    }

    @Override
    public boolean foundImportantClues() {
        int cluesFound = 0;

        if (inspectedFloor) {
            cluesFound++;
        }
        if (inspectedNails) {
            cluesFound++;
        }
        if (inspectedHands) {
            cluesFound++;
        }
        if (inspectedFace) {
            cluesFound++;
        }
        if (inspectedClothing) {
            cluesFound++;
        }
        if (inspectedBedroom) {
            cluesFound++;
        }

        return cluesFound >= 3;
    }

    @Override
    public String missingClueWarning() {
        return "You feel like you may have missed something important in the murder room.";
    }

    @Override
    public void help() {
        super.help();

        System.out.println("\nWhat you can do right now:");

        if (location.equals("outside")) {
            System.out.println("- first 'look around'and then you can:");
            System.out.println("- inspect donovan");
            System.out.println("- inspect window");
            System.out.println("- inspect crowd");
            System.out.println("- go inside");

            if (noticedDonovan && !pickpocketedDonovan) {
                System.out.println("- pickpocket donovan (risky)");
            }
        }

        else if (location.equals("downstairs")) {
            System.out.println("- look around");
            System.out.println("- inspect table");
            System.out.println("- inspect coat");
            System.out.println("- inspect stairs");

            if (!talkedToLestrade) {
                System.out.println("- talk lestrade");
            }

            System.out.println("- go upstairs");
        }

        else if (location.equals("stairwell")) {
            System.out.println("- continue upstairs");
        }

        else if (location.equals("murderroom")) {
            System.out.println("- inspect body");
            System.out.println("- inspect floor");
            System.out.println("- inspect hands / face / nails / clothing");

            if (foundImportantClues()) {
                System.out.println("- leave (you've seen enough)");
            } else {
                System.out.println("- keep inspecting (you might be missing something)");
            }
        }
    }
}