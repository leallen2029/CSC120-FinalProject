public class CabTrap extends Scene {

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

    public void showArrival() {
        if (arrivalShown) {
            return;
        }

        if (getPlayer().hasToldPolice()) {
            System.out.println("The police move in before you can act on the final clue yourself.");
            System.out.println("By the time you arrive, the confrontation has already slipped out of your hands.");
            System.out.println("You failed to see the case through on your own terms.");
            endingReached = true;
            completeScene();
            arrivalShown = true;
            return;
        }

        System.out.println("You have followed the trail as far as it will go.");
        System.out.println("The cabbie is waiting.");
        System.out.println("The room feels charged, as though one wrong move could end everything.");
        arrivalShown = true;
    }

    @Override
    public void lookAround() {
        if (endingReached) {
            System.out.println("There is nothing more to do.");
            return;
        }

        setLookedAround(true);
        lookedAroundFinal = true;

        System.out.println("The cabbie stands with unsettling calm, like he expected you.");
        System.out.println("The tension in the room is immediate and absolute.");
        System.out.println("This is no longer just about clues. This is the end of the hunt.");
    }

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
        else {
            System.out.println("Nothing else matters as much as the man in front of you.");
        }
    }

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

        if (!getPlayer().hasToldWatson()) {
            System.out.println("You came here without truly trusting Watson with what you knew.");
            System.out.println("The cabbie exploits your isolation, and with no one ready to act in time, the confrontation ends in disaster.");
            System.out.println("You die with the cabbie, and the case ends with you.");
            endingReached = true;
            completeScene();
            return;
        }

        if (getPlayer().hasSolvedSuitcase() && getPlayer().hasToldWatson() && !getPlayer().hasToldPolice()) {
            System.out.println("You hold your ground as the final pieces click into place.");
            System.out.println("The cabbie pushes the game as far as he dares, but this time Watson is part of the chain of events, not shut out from it.");
            System.out.println("The confrontation breaks in your favor.");
            System.out.println("It ends much as it should: tense, brilliant, and alive with the satisfaction of seeing the truth at the last possible second.");
            System.out.println("You survive. The case is solved.");
            endingReached = true;
            completeScene();
            return;
        }

        System.out.println("You have enough to face him, but not enough to control the outcome cleanly.");
        System.out.println("The confrontation slips away from certainty, and the ending feels incomplete.");
        System.out.println("You survive, but the case never fully resolves in your favor.");
        endingReached = true;
        completeScene();
    }

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

    @Override
    public void handleCommand(String command) {
        if (!arrivalShown) {
            showArrival();
            if (endingReached) {
                return;
            }
        }

        if (command.equalsIgnoreCase("look") || command.equalsIgnoreCase("look around")) {
            lookAround();
        }
        else if (command.startsWith("inspect ")) {
            inspect(command.substring(8));
        }
        else if (command.equalsIgnoreCase("confront") || command.equalsIgnoreCase("face cabbie")) {
            confront();
        }
        else if (command.equalsIgnoreCase("go") || command.equalsIgnoreCase("continue")) {
            go();
        }
        else {
            System.out.println("Unknown command.");
        }
    }
}