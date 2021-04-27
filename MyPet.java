import java.text.ParseException;
import java.util.Objects;

public abstract class MyPet {
    protected static int animalsCreated;
    protected String name;
    protected int petID;


    public MyPet(String name) {
        this.name = name;
        petID = generatePetID();
        animalsCreated++;
    }

    public MyPet(String name, int ID) throws ParseException {
        this.name = name;
        this.petID = ID;
    }

    public static int getAnimalsCreated() {
        return animalsCreated;
    }

    public abstract void updateAnimal();

    public int getPetID() {
        return petID;
    }

    public String toString() {
        return name + " [" + petID + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyPet)) return false;
        MyPet that = (MyPet) o;
        return petID == that.petID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petID);
    }

    public int compareTo(MyPet that) {
        return this.petID - that.petID;
    }

    private int generatePetID() {
        return (int) (Math.random() * 90000) + 10000;
    }

    public abstract String toText();


}