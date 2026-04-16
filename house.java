public class House extends Scene {

    public House() {
        super("House", "You arrive at the scene of the death.");
    }

    @Override
    public void lookAround() {
        super.lookAround();
        System.out.println("You see the outside of the house, the inside, a staircase, a bedroom, something on the floor, and the body.");
    }

    @Override
    public void inspect(String target) {

        if (!hasLookedAround()) {
            System.out.println("You should look around first.");
            return;
        }

        if (target.equalsIgnoreCase("body")) {
            System.out.println("The body lies still. Something about it feels off.");
        }

        else if (target.equalsIgnoreCase("floor")) {
            System.out.println("There are marks on the floor. They look intentional.");
        }

        else if (target.equalsIgnoreCase("bedroom")) {
            System.out.println("The bedroom looks disturbed, but not randomly.");
        }

        else if (target.equalsIgnoreCase("stairs")) {
            System.out.println("The stairs creak. Someone was here recently.");
        }

        else if (target.equalsIgnoreCase("outside")) {
            System.out.println("Outside, everything seems normal. Too normal.");
        }

        else if (target.equalsIgnoreCase("inside")) {
            System.out.println("Inside, the air feels heavy. Something happened here.");
        }

        else {
            System.out.println("Nothing important there.");
        }
    }
}