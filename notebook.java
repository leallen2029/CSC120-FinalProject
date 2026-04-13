public class notebook {
    private String name;
    private String description;
    private boolean isOpen;

    public notebook(String name, String description) {
        this.name = name;
        this.description = description;
        this.isOpen = false;
    }

    public void use(){
        this.isOpen = true;
    }
}
