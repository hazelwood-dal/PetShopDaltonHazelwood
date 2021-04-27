import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Cat extends MyPet {
    private boolean isMean;

    public Cat(String name, boolean isMean) {
        super(name);
        this.isMean = isMean;
    }

    public Cat(String[] data) throws ParseException {
        super(data[1], Integer.parseInt(data[2]));
        isMean = Boolean.parseBoolean(data[3]);
    }

    public boolean getAggression() {
        return isMean;
    }

    public void changeAggression() {
        if(isMean)
            isMean = false;
        else
            isMean = true;
    }

    @Override
    public void updateAnimal() {
        changeAggression();
    }

    public String toString() {
        return "Cat:\n" + super.toString() + "\nIs Mean: " + isMean;
    }

    @Override
    public String toText() {
        String f;
        f =  "CAT\t"  + name    +
                "\t" + petID   +
                "\t" + isMean;
        return f;
    }
}