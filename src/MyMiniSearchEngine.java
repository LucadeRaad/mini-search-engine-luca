import java.util.ArrayList;

import java.util.HashMap;
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
        // Where all info is stored
        indexes = new HashMap<>();

        // this splits up and sets up the keys. now need to have the values
        for (int i = 0; i < texts.size(); i++) {
            // splits up the list of strings into an array
            String sentence = texts.get(i);
            String[] words = sentence.split("\\s+"); // Took from stackoverflow
            // Can go through each individual element of the array
            for (int j = 0; j < words.length; j++) { // Took from class
                // Creating the lists here to stop overwritting of data
                List<List<Integer>> document = new ArrayList<>();
                for (int a = 0; a < texts.size(); a++)
                    document.add(new ArrayList<>());

                // This is to stop overwriting, If the word we're checking already exists, we need to make sure document has all the old values
                if (indexes.containsKey(words[j]))
                    document = indexes.get(words[j]);
                // .get into .add allows for multiple inputs into one arraylist
                document.get(i).add(j);

                indexes.put(words[j].toLowerCase(), document);
            }
        }
    }

    // search(key) return all the document ids where the given key phrase appears.

    // key phrase can have one or two words in English alphabetic characters.

    // return an empty list if search() finds no match in all documents.

    public List<Integer> search(String keyPhrase) {
        List<Integer> output = new ArrayList<>();
        String[] words = keyPhrase.split("\\s+"); // Took from stackoverflow
        List<List<List<Integer>>> fullDocument = new ArrayList<>();
        List<List<Integer>> keyDocument = new ArrayList<>(); // For cases when searching for just one word

        // Adds all the key word indexes to a larger list in order of input
        for (int i = 0; i < words.length; i++) {
            keyDocument = indexes.get(words[i].toLowerCase());
            fullDocument.add(keyDocument);
        }

        // Checking for words that dont exist
        if(keyDocument == null) return new ArrayList<>();

        // If only one word is found this if statement is triggered
        if(fullDocument.size() == 1) {
            for(int i = 0; i < keyDocument.size(); i++) {
                if(!keyDocument.get(i).isEmpty()) output.add(i);
            }
        }

        // The indexes in full document now if in order are the same number
        for (int i = 0; i < fullDocument.size(); i++) {
            for (int j = 0; j < fullDocument.get(i).size(); j++) {
                for (int a = 0; a < fullDocument.get(i).get(j).size(); a++) {
                    // Inspired by slide 65, on class 8
                    int replace = fullDocument.get(i).get(j).get(a);
                    replace -= i;
                    fullDocument.get(i).get(j).set(a, replace);
                }
            }
        }

        // Compares the first list of fulldocuments against the rest of the documents
        List<List<Integer>> firstDocument = fullDocument.get(0);
        for (int i = 1; i < fullDocument.size(); i++) {
            for (int j = 0; j < fullDocument.get(i).size(); j++) {
                List<List<Integer>> compareTo = fullDocument.get(i);
                if(firstDocument.get(j).equals(compareTo.get(j)) && !firstDocument.get(j).isEmpty() && !compareTo.get(j).isEmpty()) {
                    output.add(j);
                }
            }
        }
        return output;
    }

}