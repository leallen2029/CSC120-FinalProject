import java.util.ArrayList;

public class Player {
// attributes
    private boolean toldWatson;
    private boolean toldPolice;
    private boolean solvedSuitcase;
    private boolean tookCab;
    private boolean ranOut;
    private boolean returnedToSuitcase;
    private boolean sawSuitcase;
    private ArrayList<String> inventory;
    private ArrayList<String> journal;
    private final int MAX_ITEMS = 5;
    
// constructors
    public Player() {
        inventory = new ArrayList<>();
        journal = new ArrayList<>();
        tookCab = false;
        ranOut = false;
        returnedToSuitcase = false;
        sawSuitcase = false;
    }

    public void setTookCab(boolean tookCab) {
        this.tookCab = tookCab;
    }

    public boolean hasTookCab() {
        return tookCab;
    }
// decides if the player can take an item based on a reasonable amount for inventory max
    public boolean takeItem(String item) {
        if (inventory.size() >= MAX_ITEMS) {
            System.out.println("Your inventory is full.");
            return false;
        }
// prevents duplicates in inventory
        if (inventory.contains(item)) {
            System.out.println("You already have " + item + ".");
            return false;
        }
// adds item to inventory and provides feedback
        inventory.add(item);
        System.out.println("You take the " + item + ".");
        return true;
    }
// allows player to remove items from inventory
    public boolean dropItem(String item) {
        return inventory.remove(item);
    }
    public boolean hasItem(String item) {
        return inventory.contains(item);
    }
// standardizes item names
    public void addItem(String item) {
        item = item.toLowerCase();

        if (!inventory.contains(item)) {
            inventory.add(item);
        }
    }
// will display inventory and provide feedback if empty, allowing the player to keep track of what they have collected and what they can use in different scenes to solve puzzles and progress through the game.
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
// allows player to write notes in their journal, which can be used to keep track of clues, thoughts, and deductions as they navigate the case. This feature encourages players to actively engage with the story and helps them remember important details that may be relevant in later scenes.
    public void writeNote(String note) {
        journal.add(note);
        System.out.println("You write that down in your journal.");
    }
// shows notes in journal
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
// the following are case-specific to scenes and will be used to track the player's progress and choices, which will influence how certain scenes unfold and what options are available to the player as they navigate through the story.
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

    public void setSawSuitcase(boolean sawSuitcase) {
        this.sawSuitcase = sawSuitcase;
    }

    public boolean hasSawSuitcase() {
        return sawSuitcase;
    }
}