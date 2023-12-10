package Users;
import java.io.IOException;
import java.util.InputMismatchException;
public abstract class User {
    private static int nextId = 1;
    private int id;
    private String name;
    private int age;

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) throws InputMismatchException {
        if(!name.matches("[a-zA-Z]+"))
            throw new InputMismatchException("Invalid name");
        this.name = name;
    }

    public void setAge(int age) throws InputMismatchException {
        if(age <= 0 || age >100)
            throw new InputMismatchException("Invalid age");
        this.age = age;
    }


    public abstract void add() throws Exception;

    public abstract void update() throws Exception;

    public abstract void remove() throws Exception;

    public abstract void select() throws Exception;

    protected abstract void writeFile() throws IOException;

    protected abstract void readFile() throws IOException;

    protected int generateUniqueID() {
        return nextId++;
    }
}
