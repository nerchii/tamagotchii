import java.io.*;


/**
 * Utility class for saving and loading {@link Pet} objects
 * to and from a binary file for persistence between sessions.
 * <p>
 * The data is stored in {@code bin/logs.bin} and automatically
 * serialized using Java's built-in object streams.
 * </p>
 */
public class AUX_CLS {
    private static final String filePath = "bin/logs.bin";


    /**
     * Reads a serialized {@link Pet} object from the binary file.
     *
     * @return the loaded {@code Pet} instance, or {@code null} if no file exists
     *         or the read operation fails.
     */
    public static Pet readFromBin() {
        Pet ch = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            ch = (Pet) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("No character found: " + e.getMessage());
        }
        return ch;
    }


    /**
     * Writes the given {@link Pet} object to a binary file,
     * replacing any existing data.
     */
    public static void writeToBin(Pet ch){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(ch);
        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
        }
    }
}
