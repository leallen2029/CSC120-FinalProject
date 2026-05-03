public class Scene {

    private String name;
    private String description;
    private boolean completed;
    private boolean hasLookedAround;
    private Player player;
    private boolean confirmedLeavingEarly;
    private boolean waitingForJournalEntry;

    public Scene(String name, String description, Player player) {
        // constructor for Scene class, initializes name, description, player, and sets completed and hasLookedAround to false
        this.name = name;
        this.description = description;
        this.player = player;
        this.completed = false;
        this.hasLookedAround = false;
        this.confirmedLeavingEarly = false;
        this.waitingForJournalEntry = false;
    }

    public Player getPlayer() {
        return player;
    }
// overriden in many scenes to provide specific help information relevant to the current environment and situation, guiding the player on what actions they can take and what they should be looking for in that scene.
    public void help() {
    }
// displays the scene's name and description, setting the stage for the player's exploration and interaction within that environment. This method is called when the player first enters a scene and can also be used to refresh their memory of where they are and what they are dealing with.
    public void displayScene() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);
    }
// marks the scene as observed and provides a default description of the surroundings, which can be overridden in specific scenes to provide more detailed or relevant information based on the environment and the player's progress.
    public void lookAround() {
        hasLookedAround = true;
        System.out.println("You look around, but nothing unusual stands out.");
    }
// ensures player inspects certain things after looking around
    public void inspect(String target) {
        if (!hasLookedAround) {
            System.out.println("You should look around first.");
            return;
        }

        System.out.println("There is nothing special about the " + target + ".");
    }
// ensures player looks around
    public void notice(String target) {
        if (!hasLookedAround) {
            System.out.println("You should look around first.");
            return;
        } System.out.println("You do not notice anything new about the " + target + ".");
    }

    public void talk(String target) {
        System.out.println("You cannot talk to " + target + " right now.");
    }

    public void open(String target) {
        System.out.println("You cannot open " + target + ".");
    }

    public void take(String target) {
        System.out.println("You cannot take " + target + ".");
    }
// allows the player to discard an item from their inventory
    public void drop(String target) {
        if (player.dropItem(target)) {
            System.out.println("You leave it behind.");
        }
    }
/// allows the player to intentionally set an item down in the current scene
    public void place(String target) {
        if (player.hasItem(target)) {
            System.out.println("You place the " + target + " down.");
            player.dropItem(target);
        } else {
            System.out.println("You do not have " + target + ".");
        }
    }
// allows player to use objects
    public void use(String target) {
        System.out.println("You cannot use " + target + " here.");
    }
// defaults to player not being able to enter or go anywhere, but can be overridden in scenes where they can move on to the next one.
    public void enter() {
        System.out.println("You cannot enter right now.");
    }
// allows the player to continue to the next environment. 
    public void go() {
        if (!confirmLeaveIfNeeded()) {
            return;
        }

        System.out.println("You continue onward.");
        completeScene();
    }
// determines whether the player has found enough important clues to leave the scene
    public boolean foundImportantClues() {
        return true;
    }
// tells player they have missed something important.
    public String missingClueWarning() {
        return "You may have missed something important.";
    }
// checks if the player can leave the scene, requiring confirmation if important clues may be missing
    public boolean confirmLeaveIfNeeded() {
        if (!foundImportantClues() && !confirmedLeavingEarly) {
            System.out.println(missingClueWarning());
            System.out.println("Are you sure you want to move on?");
            System.out.println("Type the same command again to confirm.");
            confirmedLeavingEarly = true;
            return false;
        }

        return true;
    }
// resets the confirmation state used to prevent premature scene exit
    public void resetLeaveWarning() {
        confirmedLeavingEarly = false;
    }
// provides a fallback response for movement commands not supported in this scene
    public void move(String direction) {
        System.out.println("You can't go " + direction + " from here.");
    }
// says the player has looked around, allowing other code to be executed
    public void setLookedAround(boolean lookedAround) {
        this.hasLookedAround = lookedAround;
    }
// asks if the player has looked around, used in some scenes to determine whether they can inspect or notice things, and to provide hints if they haven't looked around yet
    public boolean hasLookedAround() {
        return hasLookedAround;
    }
// indicates when scenes are completed, moving onto the next in main.
    public void completeScene() {
        completed = true;
    }
// asks whether scenes are completed, used in main to determine when to move on to the next scene
    public boolean isCompleted() {
        return completed;
    }
// handles common commands (inventory, help, journal, drop, place) shared across many scenes
    public boolean handleBasicCommand(String command) {
        String cmd = command.toLowerCase().trim();

        if (waitingForJournalEntry) {
            player.writeNote(command.trim());
            waitingForJournalEntry = false;
            return true;
        }

        if (cmd.equals("help")) {
            help();
            return true;
        }
        else if (cmd.equals("inventory")) {
            player.showInventory();
            return true;
        }
        else if (cmd.equals("journal")) {
        System.out.println("What do you want to do?");
        System.out.println("- type 'read journal' to read your notes");
        System.out.println("- type 'write journal' to add a note");
        return true;
        }
        else if (cmd.equals("read journal")) {
        player.showJournal();
        return true;
        }

        else if (cmd.equals("write journal")) {
            System.out.println("What do you want to write?");
            waitingForJournalEntry = true;
            return true;
        }

            else if (cmd.startsWith("drop ")) {
                drop(command.substring(5).trim());
                return true;
            }
            
            else if (cmd.startsWith("place ")) {
                place(command.substring(6).trim());
                return true;
            }
            return false;
        }
// handles user input and determines which action to execute based on the command
    public void handleCommand(String command) {
        if (handleBasicCommand(command)) {
            return;
        }
        String cmd = command.toLowerCase().trim();

        if (cmd.equals("look") || cmd.equals("look around")) {
            lookAround();
        }
        else if (cmd.startsWith("inspect ")) {
            String target = command.substring(8).trim();
            inspect(target);
        }
        else if (cmd.startsWith("notice ")) {
            String target = command.substring(7).trim();
            notice(target);
        }
        else if (cmd.startsWith("talk ")) {
            String target = command.substring(5).trim();
            talk(target);
        }
        else if (cmd.startsWith("open ")) {
            String target = command.substring(5).trim();
            open(target);
        }
        else if (cmd.startsWith("take ")) {
            String target = command.substring(5).trim();
            take(target);
        }
        else if (cmd.startsWith("drop ")) {
            String target = command.substring(5).trim();
            drop(target);
        }
        else if (cmd.startsWith("place ")) {
            String target = command.substring(6).trim();
            place(target);
        }
        else if (cmd.startsWith("use ")) {
            String target = command.substring(4).trim();
            use(target);
        }
        else if (cmd.equals("enter")) {
            enter();
        }
        else if (cmd.equals("go") ) {
            go();
        }
        else {
            System.out.println("Hmm. That won't help here.");
            help();
        }
    }
}