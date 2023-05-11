import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordsCounter {
    private final File file;

    public WordsCounter(File file) {
        this.file = file;
    }


    Map<String, Integer> wordsMap = new TreeMap<>();

    public List counter() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);
        String[] words;

        while (scanner.hasNextLine()) {
            words = scanner.nextLine().split(" ");
            for (String word : words) {
                word = word.trim();

                if (wordsMap.containsKey(word)) {
                    wordsMap.replace(word, wordsMap.get(word) + 1);
                } else {
                    wordsMap.put(word, 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordsMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        return sortedList;
    }

    public static void main(String[] args) throws Exception {
        WordsCounter wordsCounter = new WordsCounter(new File("src/main/resources/words.txt"));
        System.out.println(wordsCounter.counter());
    }
}
