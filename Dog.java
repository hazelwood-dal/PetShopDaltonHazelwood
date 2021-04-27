import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Dog extends MyPet {
    private int age = 0;

    public Dog(String name, int cn) {
        super(name);
        age = cn;
    }

    public Dog(String[] data) throws ParseException {
        super(data[1],Integer.parseInt(data[2]));
        age = Integer.parseInt(data[3]);
    }

    public int getAge() {
        return age;
    }

    public void incrementAge() {
        ++age;
    }

    @Override
    public void updateAnimal() {
        incrementAge();
    }

    public String toString() {
        return "Dog:\n" + super.toString() + "\nAge:\t" + age;
    }

    @Override
    public String toText() {
        return "DOG\t" + name + "\t" + petID + "\t" + age;
    }
}