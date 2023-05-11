import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneMatcher {
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\(?\\d{3}\\)?[- ]?\\d{3,4}-\\d{4}");

    private final File file;

    public PhoneMatcher(File file) {
        this.file = file;
    }

    public void getFormattedPhoneNumbers() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String number = scanner.nextLine();
                Matcher matcher = PHONE_PATTERN.matcher(number);
                boolean found = matcher.matches();

                if (found) {
                    System.out.println(number);
                }

                while (matcher.find()) {

                    String phn = matcher.group(3);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        PhoneMatcher file = new PhoneMatcher(new File("./files/file.txt"));
        file.getFormattedPhoneNumbers();
    }
}
