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
/// sets location and tracks the player's interactions with Lestrade, as well as their choice to run out of the house and whether they go back for the suitcase or not.
    public House(Player player) {
        super("House", "You arrive outside the house where the death occurred.", player);
        location = "outside";
        talkedToLestrade = false;
        inspectedFloor = false;
        inspectedNails = false;
        inspectedHands = false;
        inspectedFace = false;
        inspectedClothing = false;
        inspectedBedroom = false;
        waitingForLeaveChoice = false;

        System.out.println("Stepping under the crime scene tape, you find yourself standing outside the house where the death occurred.");
    }
/// handles the player's interactions with the house, including looking around, inspecting specific details, talking to Lestrade, exploring the downstairs area, leaving the house, and moving through the different areas of the house. The player's choices and interactions will affect how the scene progresses and what information they uncover.
    @Override
    public void lookAround() {
        setLookedAround(true);

        if (location.equals("outside")) {
            System.out.println("John Watson looks around and says, \"This place gives me the creeps.\"");
            System.out.println("Sergeant Donovan is walking around the yard, looking for clues.");
            System.out.println("You notice the front door. It's slightly open.");
            System.out.println("You can go inside when you're ready.");
        } 
        else if (location.equals("downstairs")) {
            System.out.println("You step just inside the doorway and pause.");
            System.out.println("Policemen are quietly getting ready and moving through the downstairs rooms.");
            System.out.println("The air feels tense and stale.");
            System.out.println("Lestrade is here, watching everything carefully.");
        } 
        else if (location.equals("stairwell")) {
            System.out.println("You pause at the foot of the stairs.");
            System.out.println("The stairwell is narrow and dim, and every creak seems louder than it should.");
            System.out.println("Watson stays close behind you as Lestrade leads the way upward.");
        } 
        else if (location.equals("murderroom")) {
            System.out.println("You stand in the room where the victim died.");
            System.out.println("The silence is heavy. Every detail feels important.");
            System.out.println("You notice the body, the floor, and the bedroom space around you.");
        }
    }
/// ensures the player knows to look around before inspecting and provides different descriptions based on the player's location in the house and what they choose to inspect.
    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("Look around first.");
            return;
        }

        if (location.equals("murderroom")) {
            if (target.equalsIgnoreCase("body")) {
                System.out.println("The body lies faced down. The victim is dressed in a pink trench coat.");
                System.out.println("What else stands out to you about the body? You can inspect specific parts of the body, like the hands, face, nails, or clothing.");
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
            if (!hasLookedAround()) {
                System.out.println("You should look around first.");
                return;
            }

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

        if (cmd.equals("look") || cmd.equals("look around")) {
            lookAround();
        } 
        else if (cmd.startsWith("inspect ")) {
            String target = cmd.substring(8).trim();
            inspect(target);
        } 
        else if (cmd.equals("talk lestrade")) {
            talkToLestrade();
        } 
       else if (cmd.equals("leave")) {
            leave("ask");
        }
        else if (waitingForLeaveChoice) {
            leave(cmd);
        }
        else if (cmd.equals("go") 
                || cmd.equals("continue") 
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
        System.out.println("\nHouse commands:");
        System.out.println("- talk lestrade");
        System.out.println("- leave");
        System.out.println("- leave with watson");
        System.out.println("- leave without watson");
        System.out.println("- return suitcase with watson");
        System.out.println("- return suitcase without watson");
        System.out.println("- go inside");
        System.out.println("- go through door");
        System.out.println("- enter house");
        System.out.println("- look around");
    }
}