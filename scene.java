public class Scene {

    private String name;
    private String description;
    private boolean completed;
    private boolean hasLookedAround;
    private Player player;

    public Scene(String name, String description, Player player) {
        this.name = name;
        this.description = description;
        this.player = player;
        this.completed = false;
        this.hasLookedAround = false;
    }
    public Player getPlayer() {
        return player;
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

    public void take(String target, Player player) {
        System.out.println("You cannot take " + target + ".");
    }

    public void drop(String target, Player player) {
        if (player.dropItem(target)) {
            System.out.println("You leave it behind.");
        }
    }

    public void place(String target, Player player) {
        if (player.hasItem(target)) {
            System.out.println("You place the " + target + " down.");
            player.dropItem(target);
        } else {
            System.out.println("You do not have " + target + ".");
        }
    }

    public void handleCommand(String command) {
    if (command.equalsIgnoreCase("look") || command.equalsIgnoreCase("look around")) {
        lookAround();
    } 
    else if (command.startsWith("inspect ")) {
        String target = command.substring(8);
        inspect(target);
    } 
    else if (command.equalsIgnoreCase("go") || command.equalsIgnoreCase("continue")) {
        go();
    } 
    else {
        System.out.println("Unknown command.");
    }
}

    public void use(String target, Player player) {
        System.out.println("You cannot use " + target + " here.");
    }

    public void enter() {
        System.out.println("You cannot enter right now.");
    }

    public void go() {
        System.out.println("You continue onward.");
        completeScene();
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

    public void handleCommand(String command, Player player) {
        if (command.equalsIgnoreCase("look") || command.equalsIgnoreCase("look around")) {
            lookAround();
        }
        else if (command.startsWith("inspect ")) {
            String target = command.substring(8);
            inspect(target);
        }
        else if (command.startsWith("notice ")) {
            String target = command.substring(7);
            notice(target);
        }
        else if (command.startsWith("talk ")) {
            String target = command.substring(5);
            talk(target);
        }
        else if (command.startsWith("open ")) {
            String target = command.substring(5);
            open(target);
        }
        else if (command.startsWith("take ")) {
            String target = command.substring(5);
            take(target, player);
        }
        else if (command.startsWith("drop ")) {
            String target = command.substring(5);
            drop(target, player);
        }
        else if (command.startsWith("place ")) {
            String target = command.substring(6);
            place(target, player);
        }
        else if (command.startsWith("use ")) {
            String target = command.substring(4);
            use(target, player);
        }
        else if (command.equalsIgnoreCase("enter")) {
            enter();
        }
        else if (command.equalsIgnoreCase("go") || command.equalsIgnoreCase("continue")) {
            go();
        }
        else if (command.equalsIgnoreCase("inventory")) {
            player.showInventory();
        }
        else if (command.equalsIgnoreCase("journal")) {
            player.showJournal();
        }
        else if (command.startsWith("write ")) {
            String note = command.substring(6);
            player.writeNote(note);
        }
        else {
            System.out.println("Unknown command.");
        }
    }
}