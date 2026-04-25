import java.util.ArrayList;

public class Player {
    private boolean toldWatson;
    private boolean toldPolice;
    private boolean solvedSuitcase;
    private boolean tookCab;
    private boolean ranOut;
    private boolean returnedToSuitcase;
    private ArrayList<String> inventory;
    private ArrayList<String> journal;
    private final int MAX_ITEMS = 5;

    public Player() {
        inventory = new ArrayList<>();
        journal = new ArrayList<>();
        tookCab = false;
        ranOut = false;
        returnedToSuitcase = false;
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
    public void setRanOut(boolean ranOut) {
    this.ranOut = ranOut;
    }

    public boolean hasRanOut() {
        return ranOut;
    }

    public void setReturnedToSuitcase(boolean returnedToSuitcase) {
        this.returnedToSuitcase = returnedToSuitcase;
    }

    public boolean hasReturnedToSuitcase() {
        return returnedToSuitcase;
    }
    public void setToldWatson(boolean toldWatson) {
    this.toldWatson = toldWatson;
    }

    public boolean hasToldWatson() {
        return toldWatson;
    }

    public void setToldPolice(boolean toldPolice) {
        this.toldPolice = toldPolice;
    }

    public boolean hasToldPolice() {
        return toldPolice;
    }

    public void setSolvedSuitcase(boolean solvedSuitcase) {
        this.solvedSuitcase = solvedSuitcase;
    }

    public boolean hasSolvedSuitcase() {
        return solvedSuitcase;
    }
    public void addItem(String item) {
        item = item.toLowerCase();

        if (!inventory.contains(item)) {
            inventory.add(item);
        }
    }
}