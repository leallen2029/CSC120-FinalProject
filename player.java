import java.util.ArrayList;

public class Player {

    private ArrayList<String> inventory;
    private ArrayList<String> journal;
    private final int MAX_ITEMS = 5;
    private boolean tookCab;

    public Player() {
        inventory = new ArrayList<>();
        journal = new ArrayList<>();
        tookCab = false;
    }

    public void setTookCab(boolean tookCab) {
        this.tookCab = tookCab;
}

    public boolean hasTookCab() {
        return tookCab;
    }

    public boolean takeItem(String item) {
        if (inventory.size() >= MAX_ITEMS) {
            System.out.println("Your inventory is full.");
            return false;
        }

        if (inventory.contains(item)) {
            System.out.println("You already have " + item + ".");
            return false;
        }

        inventory.add(item);
        System.out.println("You take the " + item + ".");
        return true;
    }

    public boolean dropItem(String item) {
        if (inventory.remove(item)) {
            System.out.println("You drop the " + item + ".");
            return true;
        } else {
            System.out.println("You do not have " + item + ".");
            return false;
        }
    }

    public boolean hasItem(String item) {
        return inventory.contains(item);
    }

    public void showInventory() {
        System.out.println("\nInventory:");
        if (inventory.isEmpty()) {
            System.out.println("Nothing.");
        } else {
            for (String item : inventory) {
                System.out.println("- " + item);
            }
        }
    }

    public void writeNote(String note) {
        journal.add(note);
        System.out.println("You write that down in your journal.");
    }

    public void showJournal() {
        System.out.println("\nJournal:");
        if (journal.isEmpty()) {
            System.out.println("No notes yet.");
        } else {
            for (int i = 0; i < journal.size(); i++) {
                System.out.println((i + 1) + ". " + journal.get(i));
            }
        }
    }
}