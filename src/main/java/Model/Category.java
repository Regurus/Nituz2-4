package Model;

public class Category {

    private String name;

    public Category( String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "name: " + name;
    }
}
