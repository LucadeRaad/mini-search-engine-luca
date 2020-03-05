import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyMiniSearchEngine {
    // default solution. OK to change.
    // do not change the signature of index()
    private Map<String, List<List<Integer>>> indexes;

    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
        indexes = new HashMap<>();
        List<List<Integer>> document = new ArrayList<>();

        // We will be putting two arraylists, one holds the document, and the other holds every location that the word appears in the document
        //for(int i = 0; i < texts.size(); i++)


        // this splits up and sets up the keys. now need to have the values
        for(int i = 0; i < texts.size(); i++) {
            // Adding new arraylists
            ArrayList thisDocument = new ArrayList<>();
            thisDocument.add(i);
            document.add(thisDocument);
            // splits up the list of strings into an array
            String sentence = texts.get(i);
            String[] words = sentence.split("\\s+"); // Took from stackoverflow
            // Can go through each individual element of the array
            for (int j = 0; j < words.length; j++) { // Took from class
                if (indexes.containsKey(words[j])) {
                    thisDocument.add(j);
                    indexes.put(words[j], document);
                }
                indexes.put(words[j], document);
            }
        }
        System.out.println(indexes.toString());
    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
        return new ArrayList<>(); // place holder
    }
}
