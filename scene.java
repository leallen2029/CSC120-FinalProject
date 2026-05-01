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
    public void help() {
}

    public void displayScene() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);
    }

    public void lookAround() {
        hasLookedAround = true;
        System.out.println("You look around, but nothing unusual stands out.");
    }

    public void inspect(String target) {
        if (!hasLookedAround) {
            System.out.println("You should look around first.");
            return;
        }

        System.out.println("There is nothing special about the " + target + ".");
    }

    public void notice(String target) {
        if (!hasLookedAround) {
            System.out.println("You should look around first.");
            return;
        }

        System.out.println("You do not notice anything new about the " + target + ".");
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

    public void drop(String target) {
        if (player.dropItem(target)) {
            System.out.println("You leave it behind.");
        }
    }

    public void place(String target) {
        if (player.hasItem(target)) {
            System.out.println("You place the " + target + " down.");
            player.dropItem(target);
        } else {
            System.out.println("You do not have " + target + ".");
        }
    }

    public void use(String target) {
        System.out.println("You cannot use " + target + " here.");
    }

    public void enter() {
        System.out.println("You cannot enter right now.");
    }

    public void go() {
        if (!confirmLeaveIfNeeded()) {
            return;
        }

        System.out.println("You continue onward.");
        completeScene();
    }
    public boolean foundImportantClues() {
        return true;
    }

    public String missingClueWarning() {
        return "You may have missed something important.";
    }

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

    public void resetLeaveWarning() {
        confirmedLeavingEarly = false;
    }

    public void move(String direction) {
        System.out.println("You can't go " + direction + " from here.");
    }

    public void setLookedAround(boolean lookedAround) {
        this.hasLookedAround = lookedAround;
    }

    public boolean hasLookedAround() {
        return hasLookedAround;
    }

    public void completeScene() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }
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
        else if (cmd.startsWith("go ")) {
            String direction = cmd.substring(3).trim();

            if (direction.equals("north") ||
                direction.equals("south") ||
                direction.equals("east") ||
                direction.equals("west")) {
                move(direction);
            } else {
                System.out.println("Go where? Try north, south, east, or west.");
            }
        }
        else if (cmd.equals("go") ) {
            go();
        }
        else {
            System.out.println("Unknown command.");
        }
    }
}