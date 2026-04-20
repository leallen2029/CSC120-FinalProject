public class BakerSt extends Scene {

    private boolean arrivalShown;
    private boolean suitcaseOpened;
    private boolean phoneFound;
    private boolean phoneOpened;
    private boolean watsonArrived;
    private boolean toldWatson;
    private boolean hidSuitcase;
    private boolean policeArrived;
    private boolean noticedPhoneLight;
    private boolean lostSuitcaseToPolice;
    private boolean deductionMade;

    public BakerSt(Player player) {
        super("Baker Street", "You arrive back at 221B Baker Street.", player);

        arrivalShown = false;
        suitcaseOpened = false;
        phoneFound = false;
        phoneOpened = false;
        watsonArrived = false;
        toldWatson = false;
        hidSuitcase = false;
        policeArrived = false;
        noticedPhoneLight = false;
        lostSuitcaseToPolice = false;
        deductionMade = false;
    }

    public void showArrival() {
        if (arrivalShown) {
            return;
        }

        System.out.println("You return to Baker Street.");
        
        if (getPlayer().hasItem("suitcase")) {
            System.out.println("The pink suitcase is with you now, sitting in the room like a challenge.");
            System.out.println("You cannot shake the feeling that it matters.");
        } else {
            System.out.println("Without the suitcase, you feel that something important has slipped from your grasp.");
            System.out.println("If the police take possession of it, you may lose your best chance to solve the crime properly.");
            lostSuitcaseToPolice = true;
        }

        System.out.println("The sitting room is quiet for the moment.");
        System.out.println("You should probably look around and think.");
        arrivalShown = true;
    }

    @Override
    public void lookAround() {
        setLookedAround(true);

        System.out.println("The familiar chaos of 221B Baker Street surrounds you.");
        System.out.println("Books, notes, and chemical equipment crowd every surface.");

        if (getPlayer().hasItem("suitcase") && !suitcaseOpened) {
            System.out.println("The pink suitcase stands out immediately.");
        }

        if (phoneFound && policeArrived && !noticedPhoneLight) {
            System.out.println("Out of the corner of your eye, you notice something lighting up inside the suitcase.");
            System.out.println("The phone is glowing.");
            System.out.println("Your cabby is here.");
            noticedPhoneLight = true;
        }

        if (watsonArrived) {
            System.out.println("Watson is here with you, watching your reaction carefully.");
        }
    }

    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("You should look around first before inspecting anything.");
            return;
        }

        if (target.equalsIgnoreCase("suitcase")) {
            if (getPlayer().hasItem("suitcase")) {
                if (!suitcaseOpened) {
                    System.out.println("You study the lock again.");
                    System.out.println("Then it hits you: RACHE.");
                    System.out.println("The same word from the floor at the crime scene could be the code.");
                    deductionMade = true;
                } else {
                    System.out.println("The suitcase lies open. The phone inside is the real prize now.");
                }
            } else {
                System.out.println("You do not have the suitcase.");
            }
        }
        else if (target.equalsIgnoreCase("phone")) {
            if (!phoneFound) {
                System.out.println("There is no phone to inspect yet.");
            } else if (!phoneOpened) {
                System.out.println("The phone is old and plain, but it clearly matters.");
                System.out.println("You should probably open it.");
            } else {
                System.out.println("The phone confirms what you feared: this connects back to the cabbie.");
            }
        }
        else if (target.equalsIgnoreCase("watson")) {
            if (watsonArrived) {
                System.out.println("Watson looks concerned, like he knows you are keeping something back.");
            } else {
                System.out.println("Watson is not in the room yet.");
            }
        }
        else if (target.equalsIgnoreCase("room") || target.equalsIgnoreCase("baker street")) {
            System.out.println("This room is where scattered facts become deductions.");
        }
        else {
            System.out.println("Nothing important stands out about the " + target + ".");
        }
    }

    public void open(String target) {
        if (target.equalsIgnoreCase("suitcase")) {
            if (!getPlayer().hasItem("suitcase")) {
                System.out.println("You do not have the suitcase.");
                return;
            }

            if (suitcaseOpened) {
                System.out.println("The suitcase is already open.");
                return;
            }

            if (!deductionMade) {
                System.out.println("You stare at the lock, but the code does not come to you yet.");
                System.out.println("Maybe inspect the suitcase more carefully.");
                return;
            }

            System.out.println("You enter RACHE into the lock.");
            System.out.println("The suitcase clicks open.");
            System.out.println("Inside is a phone.");
            suitcaseOpened = true;
            phoneFound = true;
            getPlayer().writeNote("The suitcase code was RACHE.");
        }
        else if (target.equalsIgnoreCase("phone")) {
            if (!phoneFound) {
                System.out.println("There is no phone to open.");
                return;
            }

            if (phoneOpened) {
                System.out.println("The phone is already open.");
                return;
            }

            System.out.println("You open the phone and check its contents.");
            System.out.println("It points you toward the cabbie.");
            System.out.println("This was not random. The suitcase connects directly to the case.");
            phoneOpened = true;
            watsonArrived = true;
            getPlayer().writeNote("The phone inside the suitcase points to the cabbie.");
            System.out.println("A moment later, Watson comes into the room.");
            System.out.println("You can 'tell watson' or 'hide suitcase'.");
        }
        else {
            System.out.println("You cannot open " + target + ".");
        }
    }

    public void tellWatson() {
        if (!watsonArrived) {
            System.out.println("Watson is not here yet.");
            return;
        }

        if (toldWatson) {
            System.out.println("You have already told Watson what you found.");
            return;
        }

        System.out.println("You tell Watson what you found in the suitcase.");
        System.out.println("He listens carefully, then looks troubled.");
        System.out.println("\"If this is tied to the cabbie,\" he says, \"then we're closer than we thought.\"");
        toldWatson = true;

        if (!policeArrived) {
            policeArrived = true;
            System.out.println("Before either of you can say more, you hear movement outside.");
            System.out.println("The police have arrived.");
        }
    }

    public void hideSuitcase() {
        if (!watsonArrived) {
            System.out.println("There is no reason to hide it yet.");
            return;
        }

        if (!getPlayer().hasItem("suitcase")) {
            System.out.println("You do not have the suitcase.");
            return;
        }

        if (hidSuitcase) {
            System.out.println("You have already hidden the suitcase.");
            return;
        }

        System.out.println("You quickly hide the suitcase before Watson can press you any further.");
        System.out.println("You only tell him part of what you found.");
        hidSuitcase = true;

        if (!policeArrived) {
            policeArrived = true;
            System.out.println("Then you hear footsteps on the stairs.");
            System.out.println("The police have arrived.");
        }
    }

    public void surrenderSuitcase() {
        if (!getPlayer().hasItem("suitcase")) {
            System.out.println("You do not have the suitcase to hand over.");
            return;
        }

        System.out.println("You hand the suitcase over to the police.");
        System.out.println("With it goes your best chance to solve the crime yourself.");
        getPlayer().dropItem("suitcase");
        lostSuitcaseToPolice = true;
    }

    public void think() {
        System.out.println("You force yourself to connect the pieces.");

        if (getPlayer().hasItem("suitcase") && !suitcaseOpened) {
            System.out.println("The word RACHE keeps returning to you.");
            System.out.println("Maybe the lock and the murder scene are connected.");
        }

        if (phoneOpened) {
            System.out.println("The cabbie is no longer just background. He is part of this.");
        }

        if (lostSuitcaseToPolice) {
            System.out.println("Without the suitcase, the investigation feels like it is slipping away from you.");
        }
    }

    @Override
    public void go() {
        if (lostSuitcaseToPolice) {
            System.out.println("Without the suitcase lead, you lose your best chance to solve the crime properly.");
            completeScene();
            return;
        }

        if (phoneOpened && noticedPhoneLight) {
            System.out.println("You realize the cabbie is here.");
            System.out.println("There is no more time to sit and think. You have to move.");
            completeScene();
            return;
        }

        System.out.println("You are not ready to move on yet.");
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
            String target = command.substring(8);
            inspect(target);
        }
        else if (command.startsWith("open ")) {
            String target = command.substring(5);
            open(target);
        }
        else if (command.equalsIgnoreCase("tell watson")) {
            tellWatson();
        }
        else if (command.equalsIgnoreCase("hide suitcase")) {
            hideSuitcase();
        }
        else if (command.equalsIgnoreCase("give to police") || command.equalsIgnoreCase("hand over suitcase")) {
            surrenderSuitcase();
        }
        else if (command.equalsIgnoreCase("think")) {
            think();
        }
        else if (command.equalsIgnoreCase("inventory")) {
            getPlayer().showInventory();
        }
        else if (command.equalsIgnoreCase("journal")) {
            getPlayer().showJournal();
        }
        else if (command.startsWith("write ")) {
            getPlayer().writeNote(command.substring(6));
        }
        else if (command.equalsIgnoreCase("go") || command.equalsIgnoreCase("continue") || command.equalsIgnoreCase("escape")) {
            go();
        }
        else {
            System.out.println("Unknown command.");
        }
    }
}