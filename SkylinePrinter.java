import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SkylinePrinter {

    public static void printASCIIArtFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
