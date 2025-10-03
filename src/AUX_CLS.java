import java.io.*;

public class AUX_CLS {

    public static Pet readFromBin() {
        Pet ch = null;
        String filePath = "bin/logs.bin";

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            ch = (Pet) ois.readObject(); // sad kastamo u tvoju klasu
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("No character found: " + e.getMessage());
        }
        return ch;
    }

    public static void writeToBin(Pet ch){
        String filePath = "bin/logs.bin";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(ch);
//            System.out.println("Pet updated.");
        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
        }
    }
}
