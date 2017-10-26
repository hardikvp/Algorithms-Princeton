import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Hardik Panchal
 * Andrew ID: hvp
 */
public class Similarity {
    /**
     * lines keeps the track of the number of lines in file/ string.
     */
    private int lines = 0;
    /**
     * Map to add the words and frequency to.
     */
    private Map<String, BigInteger> m = new HashMap<String, BigInteger>();
    /**
     * Constructor to accept String as an input.
     * @param string to extract words from to be added to the map
     */
    public Similarity(String string) {
        if (string != null) {
            String[] wordsFromText = string.split("\\W");
            for (int i = 0; i < wordsFromText.length; i++) {
                if (wordsFromText[i].matches("[a-zA-Z]+") && !(wordsFromText[i].equals("")) && wordsFromText[i] != null) {
                    if (m.containsKey(wordsFromText[i])) {
                        m.put(wordsFromText[i].toLowerCase(), m.get(wordsFromText[i]).add(BigInteger.ONE));
                    } else {
                        m.put(wordsFromText[i].toLowerCase(), BigInteger.ONE);
                    }
                }
            }
        }
    }
    /**
     * Constructor to accept File as an input.
     * @param file to extract words from
     */
    public Similarity(File file) {
        if (file != null) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(file, "latin1");
                while (scanner.hasNextLine()) {
                    this.lines++;
                    String line = scanner.nextLine();
                    String[] wordsFromText = line.split("\\W");
                    for (String word : wordsFromText) {
                        if (word.matches("[a-zA-Z]+") && !(word.equals("")) && word != null) {
                            if (m.containsKey(word.toLowerCase())) {
                                m.put(word.toLowerCase(), m.get(word.toLowerCase()).add(BigInteger.ONE));
                            } else {
                                m.put(word.toLowerCase(), BigInteger.ONE);
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("Cannot find the file");
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }
    }
    /**
     * Method to find the number of lines in the file/ string.
     * @return number of lines in the file/ string
     */
    public int numOfLines() {
        return lines;
    }
    /**
     * Method to find number of words in the map.
     * @return number of words added to the map
     */
    public BigInteger numOfWords() {
        BigInteger sum = BigInteger.ZERO;
        for (String f : m.keySet()) {
            sum = sum.add(m.get(f));
        }
        return sum;
    }
    /**
     * Method to find number of distinct words in the map.
     * @return number of distinct words in map
     */
    public int numOfWordsNoDups() {
        return m.size();
    }
    /**
     * Method to calculate the Euclidean Norm of a vector.
     * @return euclidean norm of the vector
     */
    public double euclideanNorm() {
        BigInteger sqSum = BigInteger.ZERO;
        for (BigInteger f : m.values()) {
            sqSum = sqSum.add((f.multiply(f)));
        }
        return Math.sqrt(sqSum.doubleValue());
    }
    /**
     * Method to evaluate the dot product of the two vectors.
     * @param map Map to calculate the dot product with
     * @return Dot product of the two vectors
     */
    public double dotProduct(Map<String, BigInteger> map) {
        if (m == null || map == null || m.isEmpty() || map.isEmpty()) {
            return 0.0;
        }
        BigInteger dot = BigInteger.ZERO;
        Map<String, BigInteger> smaller, larger;
        BigInteger val1;
        BigInteger val2 = BigInteger.ZERO;
        if (m.size() <= map.size()) {
            larger = map;
            smaller = m;
            } else {
            larger = m;
            smaller = map;
            }
        Iterator<String> i = smaller.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            val1 = smaller.get(key);
            if (larger.containsKey(key)) {
                val2 = larger.get(key);
                dot = dot.add((val1.multiply(val2)));
                }
            }
        return dot.doubleValue();
        }
    /**
     * Method to calculate distance between the two frequency vectors.
     * @param map to calculate the distance from
     * @return distance between the two frequency vectors
     */
    public double distance(Map<String, BigInteger> map) {
        if (m == null || map == null || m.isEmpty() || map.isEmpty()) {
            return Math.PI / 2;
        }
        if (m.equals(map)) {
            return 0;
        }
        double distance = 0;
        double euclideanNorm2 = 0;
        for (BigInteger freuqency: map.values()) {
            euclideanNorm2 += Math.pow(freuqency.doubleValue(), 2);
        }
        euclideanNorm2 = Math.sqrt(euclideanNorm2);
        distance = Math.acos(dotProduct(map) / (euclideanNorm() * euclideanNorm2));
        return distance;
    }
    /**
     * Gives you the map of the instance.
     * @return Map used by constructor
     */
    public Map<String, BigInteger> getMap() {
        Map<String, BigInteger> rm = new HashMap<String, BigInteger>();
        for (String str : this.m.keySet()) {
            rm.put(str, new BigInteger(this.m.get(str).toString()));
            }
        return rm;
        }
    }
