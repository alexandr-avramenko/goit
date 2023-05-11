import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JSONConverter {
    private final File file;
    private final File output = new File("src/main/resources/output.json");

    public JSONConverter(File file) {
        this.file = file;
    }

    public void convertToJSON() throws FileNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (Scanner scanner = new Scanner(this.file)) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            ArrayList<User> users = new ArrayList<>();
            String[] userData;
            while (scanner.hasNextLine()) {
                userData = scanner.nextLine().split(" ");
                users.add(new User(userData[0], Integer.parseInt(userData[1])));
            }
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(this.output, users);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        JSONConverter json = new JSONConverter(new File("src/main/resources/file.txt"));
        json.convertToJSON();
    }
}
