import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class PetShopPOS {

    private static PetShop petShop = new PetShop(4);
    // this method prints out the menu to a user
    // we put it here to keep the main method clean.
    public static void printMenu() {
        System.out.print("\nBank Teller Options\n" + "-----------------------------------\n"
                + "a: add an animal to the Pet Shop\n" + "b: remove an animal from the Pet Shop\n"
                + "c: display the animals in the Pet Shop\n" + "d: count the animals in the Pet Shop\n"
                + "e: sort the animals in the Pet Shop\n" + "f: update an animal in the Pet Shop\n"
                + "g: Import animals\n" + "h: Export animals\n"
                + "?: display the menu again\n" + "q: quit this program\n\n");

    } // end of the printMenu method

    public static void main(String[] args) {
        char command;
        int petsID;
        Scanner input = new Scanner(System.in);
        printMenu();
        do {
            // ask a user to choose a command
            System.out.println("\nPlease enter a command or type ?");
            command = input.nextLine().toLowerCase().charAt(0);

            switch (command) {
                case 'a': // add an pet
                    int animalType = -1;
                    while (animalType < 1 || animalType > 2) {
                        System.out.print("\nEnter 1 for Cat or 2 for Dog: ");
                        animalType = Integer.parseInt(input.nextLine());
                    }
                    System.out.print("Enter Animals name: ");
                    String name = input.nextLine();

                    MyPet a;
                    if (animalType == 1) {
                        System.out.print("is the Cat mean? true/false");
                        Boolean r = Boolean.parseBoolean(input.nextLine());
                        a = new Cat(name, r);
                    } else { //Cat Has 9 lives do not need age
                        System.out.print("Enter Dogs Age: ");
                        int c = Integer.parseInt(input.nextLine());
                        a = new Dog(name, c);
                    }
                    if (petShop.add(a)) System.out.print("\nPet successfully added.\n");
                    else System.out.print("Pet not added. No duplicates please.\n");
                    break;
                case 'b': // remove a pet
                    System.out.print("\nEnter The Pet ID: ");
                    petsID = Integer.parseInt(input.nextLine());
                    if (petShop.remove(petShop.find(petsID)))
                        System.out.print("\nPet successfully removed.\n");
                    else System.out.print("Pet not found. Cannot remove.\n");
                    break;
                case 'c': // display the pets
                    System.out.println(petShop.toString());
                    break;
                case 'd': // count the pets
                    System.out.println("\nThere are " + petShop.getCount() + " Pets in the shop");
                    break;
                case 'e': // sort the pets
                    petShop.sort();
                    System.out.println("Animals sorted.");
                    break;
                case 'f': // update an pet
                    System.out.print("\nEnter the pets ID: ");
                    petsID = Integer.parseInt(input.nextLine());
                    MyPet anim = petShop.find(petsID);
                    if (anim == null) {
                        System.out.print("Pet not found. Cannot update.\n");
                        break;
                    }
                    Cat kitten = new Cat("",true);
                    Dog doggo = new Dog("",0);
                    if (anim.getClass() == kitten.getClass()) {
                        kitten = (Cat) anim;
                        kitten.changeAggression();
                        System.out.println("Cat aggression changed:\n" + kitten);
                    } else if (anim.getClass() == doggo.getClass()) {
                        doggo = (Dog) anim;
                        doggo.incrementAge(); /**Right Here*/
                        System.out.println("Dog age increased:\n" + doggo);
                    }
                    break;
                case 'h':
                    System.out.println("Enter a File Name: ");
                    String fileNameExp = input.nextLine();
                    try {
                        System.out.println("Successful Pets exported: " + petShop.writeAnimals(fileNameExp));
                    } catch (FileNotFoundException e) {
                        System.err.println("Invalid File Name");
                        //e.printStackTrace();
                    }
                    break;
                case 'g':
                    System.out.println("Enter a File Name: ");
                    String fileNameImp = input.nextLine();
                    try {
                        System.out.println("Pets Imported: " + readAnimals(fileNameImp));
                    } catch (FileNotFoundException e) {
                        System.err.println("Invalid File Name");
                        //e.printStackTrace();
                    }
                    break;
                case '?':
                    printMenu();
                    break;
                case 'q':
                    System.out.println("GOOD BYE!");
                    break;
                default:
                    System.out.println("Invalid Input");

            }

        } while (command != 'q');

        input.close();
    }

    public static int readAnimals(String filename) throws FileNotFoundException {
        int count = 0;
        File file = new File(filename);
        //try (Scanner scnTxt = new Scanner(file)) {
        Scanner scnTxt = new Scanner(file);
        while (scnTxt.hasNextLine()) {
            String data = scnTxt.nextLine();
            try {
                petShop.add(parseAnimalFile(data));
                count++;
                //} catch (ParseException e) {
                // e.printStackTrace();
            } catch (AnimalFileFormatException | ParseException e) {
                System.err.println("Bad Line Skipped");
            }

            //System.out.println(count);
        }
        //} catch (FileNotFoundException e) {
        // throw new FileNotFoundException();
        scnTxt.close();
        return count;
    }


    public static MyPet parseAnimalFile(String data) throws AnimalFileFormatException, ParseException {
        String[] dataArray = data.split("\t");
        MyPet a = null;
        if (dataArray.length != 4) {
            throw new AnimalFileFormatException("Data Not Right");
        }
        if (dataArray[0].equals("DOG")) {
            a = new Dog(dataArray);
            //System.out.println(a.toString());
        } else if (dataArray[0].equals("CAT")) {
            a = new Cat(dataArray);
            //System.out.println(a.toString());
        }
        return a;
    }

}





