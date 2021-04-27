import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PetShop {
    private MyPet[] petsAry;
    private int count;

    public PetShop(int cap) {
        petsAry = new MyPet[cap];
        count = 0;
    }

    public boolean add(MyPet a) {
        if (contains(a)) return false;
        if (full()) doubleCapacity();
        petsAry[count++] = a;
        return true;
    }

    public boolean remove(MyPet a) {
        if (!contains(a)) return false;
        petsAry[indexOf(a)] = petsAry[--count];
        return true;
    }

    public boolean contains(MyPet a) {
        return indexOf(a) != -1;
    }

    public MyPet find(int acct) {
        for (int i = 0; i < count; i++)
            if (petsAry[i].getPetID() == acct) return petsAry[i];
        return null;
    }

    private int indexOf(MyPet a) {
        if (a == null) return -1;
        for (int i = 0; i < count; i++)
            if (petsAry[i].equals(a)) return i;
        return -1;
    }

    public int getCount() {
        return count;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Animals\n");
        for (int i = 0; i < count; i++) {
            sb.append(petsAry[i].toString());
            sb.append("\n    **************    \n");
        }
        return sb.toString();
    }

    public void sort() {
        for (int i = 1; i < count; i++) {
            MyPet temp = petsAry[i];
            int j;
            for (j = i - 1; j >= 0 && less(temp, petsAry[j]); j--) {
                petsAry[j + 1] = petsAry[j];
            }
            petsAry[j + 1] = temp;
        }
    }

    private void doubleCapacity() {
        MyPet[] a = new MyPet[petsAry.length * 2];
        System.arraycopy(petsAry, 0, a, 0, count);
        petsAry = a;
    }

    private boolean less(MyPet temp, MyPet j) {
        return temp.getPetID() < j.getPetID();
    }

    private boolean full() {
        return count == petsAry.length;
    }

    //at part 3
    public int writeAnimals(String filename) throws FileNotFoundException {
        int count = 0;
        File file = new File(filename);
        PrintWriter output = new PrintWriter(file);

        for (MyPet account : petsAry) {
            output.println(account.toText());
            ++count;
        }
        output.close();
        return count;
    }
}


