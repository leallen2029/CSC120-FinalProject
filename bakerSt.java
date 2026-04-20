public class BakerSt extends Scene {

    private boolean arrivalShown;
    private boolean deductionMade;

    public BakerSt(Player player) {
        super("Baker Street", "You arrive back at 221B Baker Street.", player);
        arrivalShown = false;
        deductionMade = false;
    }

    public void showArrival() {
        if (arrivalShown) return;

        if (getPlayer().hasReturnedToSuitcase()) {
            System.out.println("You return to Baker Street with the suitcase still on your mind.");
            System.out.println("Watson watches you pace. \"That thing you saw... it matters, doesn't it?\"");
        } 
        else if (getPlayer().hasRanOut()) {
            System.out.println("You return quickly, your thoughts still tangled in the crime scene.");
            System.out.println("\"You saw something,\" Watson says. \"I can tell.\"");
        } 
        else {
            System.out.println("You and Watson return calmly to Baker Street.");
            System.out.println("The case sits heavily in your mind.");
        }

        System.out.println("You should 'think' about the case.");
        arrivalShown = true;
    }

    @Override
    public void lookAround() {
        setLookedAround(true);

        System.out.println("The familiar chaos of 221B surrounds you.");
        System.out.println("Notes, books, and chemicals cover every surface.");
        System.out.println("This is where ideas begin to connect.");
    }

    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("Look around first.");
            return;
        }

        if (target.equalsIgnoreCase("room")) {
            System.out.println("This room has solved countless cases—but only if you think clearly.");
        } 
        else if (target.equalsIgnoreCase("watson")) {
            System.out.println("Watson watches you, waiting for your reasoning.");
        } 
        else if (target.equalsIgnoreCase("case") || target.equalsIgnoreCase("clues")) {
            System.out.println("You recall:");
            System.out.println("- The word RACHE carved into the floor");
            System.out.println("- No signs of forced entry");
            
            if (getPlayer().hasReturnedToSuitcase()) {
                System.out.println("- The mysterious pink suitcase");
            }
        } 
        else {
            System.out.println("Nothing important there.");
        }
    }

    public void think() {
        System.out.println("You begin organizing the facts...");

        System.out.println("Victim alone. No forced entry.");
        System.out.println("A message left behind: RACHE.");

        if (getPlayer().hasReturnedToSuitcase()) {
            System.out.println("And that suitcase... placed deliberately.");
        }

        System.out.println("What does it mean?");
        System.out.println("Type 'deduce <your idea>'");
    }

    public void deduce(String idea) {

        if (deductionMade) {
            System.out.println("You have already made your deduction.");
            return;
        }

        // VERY SIMPLE keyword logic (you can expand this later)
        if (idea.toLowerCase().contains("revenge") || idea.toLowerCase().contains("rache")) {
            System.out.println("Watson nods slowly.");
            System.out.println("\"Rache... German for revenge,\" he says.");
            System.out.println("The message was not random.");
            System.out.println("You are onto something.");

            deductionMade = true;
        } 
        else if (idea.toLowerCase().contains("suitcase")) {
            System.out.println("You focus on the suitcase.");
            System.out.println("Watson frowns. \"It might matter—but how does it connect?\"");
        } 
        else {
            System.out.println("That line of thinking doesn't seem strong enough.");
        }
    }

    public void talkToWatson() {
        if (!deductionMade) {
            System.out.println("\"You need to piece it together,\" Watson says.");
            System.out.println("\"Start with what we *know*, not what we guess.\"");
        } else {
            System.out.println("\"If it's revenge,\" Watson says, \"then the killer had a reason.\"");
            System.out.println("\"Now we just need to find who.\"");
        }
    }

    @Override
    public void go() {
        if (!deductionMade) {
            System.out.println("You feel like you are missing something. Think it through first.");
            return;
        }

        System.out.println("You grab your coat.");
        System.out.println("\"We have a direction now,\" you say.");
        System.out.println("The investigation continues...");
        completeScene();
    }

    @Override
    public void handleCommand(String command) {

        if (!arrivalShown) {
            showArrival();
        }

        if (command.equalsIgnoreCase("look") || command.equalsIgnoreCase("look around")) {
            lookAround();
        } 
        else if (command.startsWith("inspect ")) {
            inspect(command.substring(8));
        } 
        else if (command.equalsIgnoreCase("think")) {
            think();
        } 
        else if (command.startsWith("deduce ")) {
            deduce(command.substring(7));
        }
        else if (command.equalsIgnoreCase("talk watson")) {
            talkToWatson();
        }
        else if (command.equalsIgnoreCase("journal")) {
            getPlayer().showJournal();
        }
        else if (command.startsWith("write ")) {
            getPlayer().writeNote(command.substring(6));
        }
        else if (command.equalsIgnoreCase("inventory")) {
            getPlayer().showInventory();
        }
        else if (command.equalsIgnoreCase("go") || command.equalsIgnoreCase("continue")) {
            go();
        } 
        else {
            System.out.println("Unknown command.");
        }
    }
}