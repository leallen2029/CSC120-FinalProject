import java.util.Scanner;

public class pencil extends grabableobject {
    public pencil(String name) {
        this.name = name;
        this.description = "This is a " + name + ".";
    }

    public void use(){
        System.out.println("You have picked up the pencil . What would you like to write?");
        Scanner input = new Scanner(System.in);
        String writing = input.nextLine();
        System.out.println(writing);
        input.close();
    }
    @Override
    public void describe() {
        System.out.println(this.description);
        
    }

    @Override
    void use() {
        System.out.println("You use the " + this.name + ".");
    }
}
