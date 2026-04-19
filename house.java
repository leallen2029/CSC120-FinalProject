public class House extends Scene {

    private String location;
    private boolean talkedToLestrade;

    public House(Player player) {
        super("House", "You arrive outside the house where the death occurred.", player);
        location = "outside";
        talkedToLestrade = false;

        System.out.println("Stepping under the crime scene tape, you find yourself standing outside the house where the death occurred.");
    }

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

    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("Look around first.");
            return;
        }

        if (location.equals("murderroom")) {
            if (target.equalsIgnoreCase("body")) {
                System.out.println("The body lies still. Something about it feels wrong.");
                System.out.println("What else stands out to you about the body? You can inspect specific parts of the body, like the hands, face, nails, or clothing.");
            } 
            else if (target.equalsIgnoreCase("floor")) {
                System.out.println("There are marks on the floor next to the body.");
                System.out.println("It looks like someone carved the letters RACHE into the floor. How did they get there?");
            } 
            else if (target.equalsIgnoreCase("nails")) {
                System.out.println("You look closely at the victim's nails and notice they are broken.");
                System.out.println("It appears that carving the letters may have been the last thing she did.");
            }
            else if (target.equalsIgnoreCase("hands")) {
                System.out.println("The victim's hands look strained, as if she used the last of her strength to leave something behind.");
            }
            else if (target.equalsIgnoreCase("face")) {
                System.out.println("Her expression is tense and frightened, frozen in her final moment.");
            }
            else if (target.equalsIgnoreCase("clothing")) {
                System.out.println("Her clothing is disordered, but not enough to suggest a direct struggle.");
            }
            else if (target.equalsIgnoreCase("bedroom")) {
                System.out.println("The bedroom looks disturbed, but not randomly.");
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

    public void explore() {
        if (location.equals("downstairs") && !talkedToLestrade) {
            System.out.println("As you try to move further in, Lestrade steps into your path.");
            System.out.println("\"Before you go wandering off,\" he says, \"you should hear what we know so far.\"");
            talkToLestrade();
        } 
        else if (location.equals("downstairs")) {
            System.out.println("You glance around the downstairs area, but the important evidence seems to be upstairs.");
        } 
        else {
            System.out.println("There is nowhere else to explore right now.");
        }
    }
    public void leave(String choice) {
        if (!location.equals("murderroom")) {
            System.out.println("Are you ready to leave? You can 'run' or 'walk' out with Watson.");
            return;
        }

        if (choice.equalsIgnoreCase("run")) {
            System.out.println("You rush out of the house, your mind racing.");

            if (getPlayer().hasTookCab()) {
                System.out.println("As you reach the street, you remember the pink suitcase you saw from the cab.");
                System.out.println("You can 'return suitcase' or 'leave watson'.");
            } else {
                completeScene();
            }
        } 
        else if (choice.equalsIgnoreCase("walk")) {
            System.out.println("You leave calmly with Watson, discussing the case as you go.");
            completeScene();
        } 
        else {
            System.out.println("Leave how? (leave run / walk)");
        }
    }

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
        if (command.equalsIgnoreCase("look") || command.equalsIgnoreCase("look around")) {
            lookAround();
        } 
        else if (command.startsWith("inspect ")) {
            String target = command.substring(8);
            inspect(target);
        } 
        else if (command.equalsIgnoreCase("talk lestrade")) {
            talkToLestrade();
        } 
        else if (command.equalsIgnoreCase("explore")) {
            explore();
        } 
        else if (command.equalsIgnoreCase("go") 
                || command.equalsIgnoreCase("continue") 
                || command.equalsIgnoreCase("go upstairs")) {
            go();
        } 
        else {
            System.out.println("Unknown command.");
        }
    }
}