public class House extends Scene {

    public House() {
        super("House", "You arrive outside the house where the death occurred.");
        System.out.println("Stepping under the crime scene tape, you find yourself standing outside the house where the death occurred. What do you do now?");
    }

    @Override
    public void lookAround() {
        setLookedAround(true);

        System.out.println("John Watson looks around and says, 'This place gives me the creeps.'");
        System.out.println("Sergeant Donovan is walking around the yard, looking for clues.");
        System.out.println("You notice the front door. It’s slightly open.");
        System.out.println("You can go inside now.");
    }

    @Override
    public void go() {
        if (!hasLookedAround()) {
            System.out.println("You should look around first.");
            return;
        }

        System.out.println("You enter the house.");
            
    }
}