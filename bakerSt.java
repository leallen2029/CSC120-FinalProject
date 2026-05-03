public class BakerSt extends Scene {

// attributes
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
    private boolean waitingForCodeAnswer;
    private boolean codeFiguredOut;

// initializes the scene and sets the initial state of all events and items
    public BakerSt(Player player) {
        super("Baker Street", "You arrive back at 221B Baker Street.", player);
// constructor
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
        waitingForCodeAnswer = false;
        codeFiguredOut = false;
    }

// shows the arrival text and sets the stage for the player's investigation in Baker Street, including the importance of the suitcase and the phone, as well as the presence of Watson and the police. The player's interactions with these elements will affect how the scene progresses and what information they uncover.
    public void showArrival() {
        if (arrivalShown) {
            return;
        }

        System.out.println("You return to Baker Street.");

        if (getPlayer().hasItem("suitcase")) {
            if (getPlayer().hasReturnedToSuitcase()) {
                System.out.println("The pink suitcase is with you now.");
                System.out.println("You returned for it after leaving the house, but you still need to examine it properly.");
            } else {
                System.out.println("The pink suitcase is with you now, sitting in the room like a challenge.");
                System.out.println("You cannot shake the feeling that it matters.");
            }
        } else {
            System.out.println("Without the suitcase, you feel that something important has slipped from your grasp.");
            System.out.println("If the police take possession of it, you may lose your best chance to solve the crime properly.");
            lostSuitcaseToPolice = true;
        }

        System.out.println("The sitting room is quiet for the moment.");
        System.out.println("You should probably look around and think.");
        arrivalShown = true;
    }

// handles the player's interactions in Baker Street, including looking around, inspecting specific details, opening the suitcase and phone, talking to Watson, hiding the suitcase from Watson, surrendering the suitcase to the police, thinking through the clues, and moving on to the next scene. The player's choices and interactions will affect how the scene progresses and what information they uncover.
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

// ensures the player knows to look around before inspecting and provides different descriptions based on what they choose to inspect, as well as the consequences of inspecting certain items like the suitcase and phone.
    @Override
    public void inspect(String target) {
        if (!hasLookedAround()) {
            System.out.println("You should look around first before inspecting anything.");
            return;
        }

        if (target.equalsIgnoreCase("suitcase")) {
            if (getPlayer().hasItem("suitcase")) {
                if (!suitcaseOpened) {
                    System.out.println("You study the lock.");
                    System.out.println("It needs a five-letter code.");
                    System.out.println("What is the code?");
                    waitingForCodeAnswer = true;
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
// handles the player's attempt to open the suitcase and phone, ensuring they have the necessary items and have made the necessary deductions before allowing them to open these key items. The consequences of opening these items will affect how the scene progresses and what information they uncover.
    @Override
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

            if (!codeFiguredOut) {
                System.out.println("The suitcase is locked.");
                System.out.println("Inspect the suitcase if you want to try the code.");
                return;
            }

            System.out.println("You enter RACHE into the lock.");
            System.out.println("The suitcase clicks open.");
            System.out.println("Inside is a phone.");
            System.out.println("You should take the phone before you can use it.");
            suitcaseOpened = true;
            phoneFound = true;
            getPlayer().writeNote("The suitcase code was RACHE.");
        }
        else if (target.equalsIgnoreCase("phone")) {
            if (!phoneFound) {
                System.out.println("There is no phone to open.");
                return;
            }

            if (!getPlayer().hasItem("phone")) {
                System.out.println("You need to take the phone first.");
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
            getPlayer().setSolvedSuitcase(true);
            watsonArrived = true;
            getPlayer().writeNote("The phone inside the suitcase points to the cabbie.");
            System.out.println("A moment later, Watson comes into the room.");
            System.out.println("You can 'tell watson' or 'hide suitcase'.");
        }
        else {
            System.out.println("You cannot open " + target + ".");
        }
    }
// allows the player to take the phone from the suitcase, which will be a key item for solving the case and confronting the cabbie in the final scene. The player's decision to take the phone and how they use it will affect how they approach the confrontation and what information they have at their disposal.
   @Override
    public void take(String target) {
        if (target.equalsIgnoreCase("phone")) {
            if (!phoneFound) {
                System.out.println("There is no phone to take.");
            } else if (getPlayer().hasItem("phone")) {
                System.out.println("You already took the phone.");
            } else {
                System.out.println("You pick up the phone.");
                getPlayer().addItem("phone");
            }
        } else {
            super.take(target);
        }
    }
// allows the player to tell Watson about the suitcase and its contents, which will affect how Watson interacts with the player and how the police become involved in the case. The player can also choose to hide the suitcase from Watson, which will affect their relationship with him and how the investigation proceeds.
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
        getPlayer().setToldWatson(true);

        if (!policeArrived) {
            policeArrived = true;
            System.out.println("Before either of you can say more, you hear movement outside.");
            System.out.println("The police have arrived.");
        }
    }

// allows the player to hide the suitcase from Watson, which will affect their relationship with him and how the investigation proceeds. If the player chooses to hide the suitcase, they will have to deal with the consequences of keeping this information from Watson and potentially losing his trust.
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
        System.out.println("Watson watches you carefully, but says nothing.");
        System.out.println("You get the sense this may matter later.");
        hidSuitcase = true;
        getPlayer().setToldWatson(false);

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
        getPlayer().setToldPolice(true);
    }

// helps the player to think through the clues they have uncovered in Baker Street, including the significance of the suitcase and phone, the implications of telling Watson or hiding the suitcase, and how these elements connect to the cabbie and the overall case. The player's deductions will affect how they approach the final confrontation in the next scene.
    public void think() {
        System.out.println("You force yourself to connect the pieces.");

        if (getPlayer().hasItem("suitcase") && !suitcaseOpened) {
            System.out.println("The lock needs a five-letter code.");
            System.out.println("If you cannot remember it, type 'read journal' to look at your notes.");
        }

        if (phoneOpened) {
            System.out.println("The cabbie is no longer just background. He is part of this.");
        }

        if (lostSuitcaseToPolice) {
            System.out.println("Without the suitcase, the investigation feels like it is slipping away from you.");
  
        }
    }

// deals with go() for this situation
    @Override
    public void go() {
        if (lostSuitcaseToPolice) {
            System.out.println("Without the suitcase lead, you lose your best chance to solve the crime properly.");
            completeScene();
            return;
        }

        if (phoneOpened && !toldWatson && !hidSuitcase) {
            System.out.println("Watson is waiting for an answer.");
            System.out.println("You should either 'tell watson' or 'hide suitcase' before moving on.");
            return;
        }

        if (phoneOpened && policeArrived && !noticedPhoneLight) {
            System.out.println("You feel like something in the room has changed.");
            System.out.println("Look around before you leave.");
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
// processes player input for the Baker Street scene, including special interactions like the suitcase code and watson choices
    @Override
    public void handleCommand(String command) {
        if (handleBasicCommand(command)) {
            return;
        }

        String cmd = command.toLowerCase().trim();

        if (waitingForCodeAnswer) {
            if (cmd.equals("rache")) {
                System.out.println("Correct. RACHE.");
                System.out.println("The code falls into place.");
                codeFiguredOut = true;
                waitingForCodeAnswer = false;
            } else {
                System.out.println("That is not the code.");
                System.out.println("You may need to return to your crime scene notes.");
                waitingForCodeAnswer = false;
            }
            return;
        }

        if (!arrivalShown) {
            showArrival();
        }

        if (cmd.equals("look") || cmd.equals("look around")) {
            lookAround();
        }
        else if (cmd.startsWith("inspect ")) {
            String target = cmd.substring(8).trim();
            inspect(target);
        }
        else if (cmd.startsWith("open ")) {
            String target = cmd.substring(5).trim();
            open(target);
        }
        else if (cmd.startsWith("take ")) {
            take(cmd.substring(5).trim());
        }
        else if (cmd.equals("tell watson")) {
            tellWatson();
        }
        else if (cmd.equals("hide suitcase")) {
            hideSuitcase();
        }
        else if (cmd.equals("give to police") || cmd.equals("hand over suitcase")) {
            surrenderSuitcase();
        }
        else if (cmd.equals("think")) {
            think();
        }
        else if (cmd.equals("go") || cmd.equals("escape")) {
            go();
        }
        else {
            System.out.println("Unknown command.");
        }
    }
    @Override
    public void help() {
        System.out.println("\nYou pause and consider your options...");

        if (lostSuitcaseToPolice) {
            System.out.println("- go");
        }
        else if (!arrivalShown) {
            System.out.println("- look around");
        }
        else if (getPlayer().hasItem("suitcase") && !suitcaseOpened && !codeFiguredOut) {
            System.out.println("- look around");
            System.out.println("- inspect suitcase");
            System.out.println("- read journal");
            System.out.println("- type the code if you know it");
        }
        else if (getPlayer().hasItem("suitcase") && !suitcaseOpened && codeFiguredOut) {
            System.out.println("- open suitcase");
        }
        else if (suitcaseOpened && phoneFound && !getPlayer().hasItem("phone")) {
            System.out.println("- take phone");
        }
        else if (getPlayer().hasItem("phone") && !phoneOpened) {
            System.out.println("- open phone");
        }
        else if (phoneOpened && watsonArrived && !toldWatson && !hidSuitcase) {
            System.out.println("- tell watson");
            System.out.println("- hide suitcase");
        }
        else if (phoneOpened && policeArrived && !noticedPhoneLight) {
            System.out.println("- look around");
        }
        else if (phoneOpened && noticedPhoneLight) {
            System.out.println("- go");
        }
        else {
            System.out.println("- think");
            System.out.println("- look around");
        }
    }
}