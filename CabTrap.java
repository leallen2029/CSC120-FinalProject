public class CabTrap extends Scene {
/// final scene where the player confronts the cabbie, with different outcomes based on the player's choices and interactions in previous scenes.
//  The player can choose to look around, inspect the cabbie and the room, confront the cabbie, or try to move on, but their options and the consequences of their actions will depend on how they have navigated the case up to this point.
    private boolean arrivalShown;
    private boolean lookedAroundFinal;
    private boolean confrontationStarted;
    private boolean endingReached;

    public CabTrap(Player player) {
        super("Final Scene", "The last thread of the case leads you into a final confrontation.", player);
        arrivalShown = false;
        lookedAroundFinal = false;
        confrontationStarted = false;
        endingReached = false;
    }

/// shows the arrival text and sets the stage for the player's confrontation with the cabbie, including the tension in the room and the significance of this final encounter. The player's choices in this scene will determine how the confrontation unfolds and what information they uncover about the cabbie and his role in the case.
    public void showArrival() {
        if (arrivalShown) {
            return;
        }

        if (getPlayer().hasToldPolice()) {
            System.out.println("The police move in before you can act on the final clue yourself.");
            System.out.println("By the time you arrive, the cabbie has disappeared into the confusion.");
            System.out.println("You gave away the evidence too soon.");
            System.out.println("FAIL ENDING: The case slips out of your hands.");
            endingReached = true;
            completeScene();
            arrivalShown = true;
            return;
        }

        System.out.println("You have followed the trail as far as it will go.");
        System.out.println("The cabbie is waiting.");
        System.out.println("The room feels charged, as though one wrong move could end everything.");
        System.out.println("You should look around before you confront him.");
        arrivalShown = true;
    }

/// allows the player to look around the room and take in the atmosphere of the confrontation, which will affect how they approach the situation and what they notice about the cabbie and the room. The player's observations will influence their strategy for confronting the cabbie and how they interpret his behavior.
    @Override
    public void lookAround() {
        if (endingReached) {
            System.out.println("There is nothing more to do.");
            return;
        }

        setLookedAround(true);
        lookedAroundFinal = true;

        System.out.println("The cabbie stands with unsettling calm, like he expected you.");
        System.out.println("Two pills sit between you like a dare.");
        System.out.println("The tension in the room is immediate and absolute.");
        System.out.println("This is no longer just about clues. This is the end of the hunt.");
    }

/// allows the player to inspect specific details about the cabbie and the room, which will provide insights into the cabbie's motivations and the significance of this final confrontation. The player's deductions from these inspections will affect how they approach the confrontation and what they expect from the cabbie.
    @Override
    public void inspect(String target) {
        if (endingReached) {
            System.out.println("It is over now.");
            return;
        }

        if (!hasLookedAround()) {
            System.out.println("Look around first.");
            return;
        }

        if (target.equalsIgnoreCase("cabbie")) {
            System.out.println("His expression is steady, almost amused.");
            System.out.println("He looks less like a driver and more like someone who has been steering the case all along.");
        }
        else if (target.equalsIgnoreCase("room")) {
            System.out.println("The room offers no comfort, only pressure.");
            System.out.println("Everything has narrowed down to this single encounter.");
        }
        else if (target.equalsIgnoreCase("pills")) {
            System.out.println("Two pills. One choice. One trap.");
            System.out.println("The cabbie is trying to make the ending feel like a game.");
        }
        else {
            System.out.println("Nothing else matters as much as the man in front of you.");
        }
    }

/// handles the player's confrontation with the cabbie, which will be influenced by their choices and interactions in previous scenes. The outcome of this confrontation will depend on how well the player has prepared and what information they have uncovered, as well as how they choose to face the cabbie in this moment.
    public void confront() {
        if (endingReached) {
            System.out.println("It is already finished.");
            return;
        }

        if (!lookedAroundFinal) {
            System.out.println("You should take in the situation first.");
            return;
        }

        confrontationStarted = true;

        if (getPlayer().hasToldPolice()) {
            System.out.println("The police took control of the evidence before you could finish the case yourself.");
            System.out.println("The cabbie slips away, and the truth becomes harder to prove.");
            System.out.println("FAIL ENDING: You lose the chance to solve the crime.");
            endingReached = true;
            completeScene();
            return;
        }

        if (!getPlayer().hasToldWatson()) {
            System.out.println("You came here without truly trusting Watson with what you knew.");
            System.out.println("No one is ready. No one is watching closely enough.");
            System.out.println("The cabbie exploits your isolation, pushing the game until it is too late.");
            System.out.println("DEATH ENDING: You die with the cabbie, and the case ends with you.");
            endingReached = true;
            completeScene();
            return;
        }

        if (getPlayer().hasSolvedSuitcase() && getPlayer().hasToldWatson() && !getPlayer().hasToldPolice()) {
            System.out.println("You hold your ground as the final pieces click into place.");
            System.out.println("The cabbie pushes the game as far as he dares.");
            System.out.println("But this time Watson is part of the chain of events, not shut out from it.");
            System.out.println("A shot breaks the moment.");
            System.out.println("The confrontation ends before the cabbie can finish his trap.");
            System.out.println("BEST ENDING: You survive. The case is solved.");
            endingReached = true;
            completeScene();
            return;
        }

        System.out.println("You have reached the cabbie, but the case is not fully in your control.");
        System.out.println("You survive the confrontation, but too much remains uncertain.");
        System.out.println("INCOMPLETE ENDING: You live, but the case never fully resolves in your favor.");
        endingReached = true;
        completeScene();
    }

/// deals with go() for this situation
    @Override
    public void go() {
        if (endingReached) {
            System.out.println("The case is over.");
            completeScene();
            return;
        }

        if (!confrontationStarted) {
            confront();
        } else {
            System.out.println("There is nowhere else to go now.");
        }
    }

/// handles the player's commands in this final scene, allowing them to look around, inspect the cabbie and the room, confront the cabbie, or try to move on. The player's options and the consequences of their actions will depend on how they have navigated the case up to this point, and their choices in this scene will determine how the confrontation unfolds and what information they uncover about the cabbie and his role in the case.
    @Override
    public void handleCommand(String command) {
        if (handleBasicCommand(command)) {
            return;
        }

        String cmd = command.toLowerCase().trim();

        if (!arrivalShown) {
            showArrival();
            if (endingReached) {
                return;
            }
        }

        if (cmd.equals("look") || cmd.equals("look around")) {
            lookAround();
        }
        else if (cmd.startsWith("inspect ")) {
            inspect(cmd.substring(8).trim());
        }
        else if (cmd.equals("confront") || cmd.equals("face cabbie")) {
            confront();
        }
        else if (cmd.equals("go")) {
            go();
        }
        else {
            System.out.println("Unknown command.");
        }
        
    }
    @Override
    public void help() {
        super.help();

        System.out.println("\nYou pause and consider your options...");

        if (endingReached) {
            System.out.println("- the case is over");
        }
        else if (!arrivalShown) {
            System.out.println("- look around");
        }
        else if (!lookedAroundFinal) {
            System.out.println("- look around");
        }
        else if (!confrontationStarted) {
            System.out.println("- inspect cabbie");
            System.out.println("- inspect room");
            System.out.println("- inspect pills");
            System.out.println("- confront");
        }
        else {
            System.out.println("- go");
        }
    }
}