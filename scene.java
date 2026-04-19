public class Scene {

    private String name;
    private String description;
    private boolean completed;
    private boolean hasLookedAround;

    // Constructor
    public Scene(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
        this.hasLookedAround = false;
    }

    public void displayScene() {
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);
    }

    // General look
    public void lookAround() {
        System.out.println("\nYou take a careful look around...");
        hasLookedAround = true;
        System.out.println("Nothing unusual stands out.");
    }

    // targeted look 
    public void lookAround(String target) {
        if (!hasLookedAround) {
            System.out.println("You should look around first before focusing on something specific.");
            return;
        }

        System.out.println("You do not notice anything special about the " + target + ".");
    }

    // General inspect
    public void inspect(String target) {
        if (!hasLookedAround) {
            System.out.println("You should look around first before inspecting anything.");
            return;
        }

        System.out.println("There is nothing special about the " + target + ".");
    }

    // Allow subclasses to update look state safely
    public void setLookedAround(boolean lookedAround) {
        this.hasLookedAround = lookedAround;
    }

    public void completeScene() {
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean hasLookedAround() {
        return hasLookedAround;
    }
  
}