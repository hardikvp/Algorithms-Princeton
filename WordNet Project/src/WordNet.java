import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WordNet {
    private Diagraph d;
    private final ShortestCommonAncestor s;
    private Map <Integer,String> m = new HashMap <Integer, String>();
    private Map <Integer,String> m1 = new HashMap <Integer, String>();
    private String str;
   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) throws IOException {
       this.str = synsets;
       if (str !=null) {
           FileReader fr = new FileReader(str);
           CSVReader c = new CSVReader(fr);
           boolean eof = false;
           int count = 0;
           while (!eof) {
               if (count == 0) {
                   count += 1;
               } else {
                   String[] values = c.readCSVLine();
                   if (values == null) {
                       eof = true;
                   } else {
                       m.put(Integer.parseInt(values[0]), values[1]);
                       m1.put(Integer.parseInt(values[0]), values[2]);
                       }
                   }
               }
           c.close();
           }
       d = new Diagraph(m);
       s = new ShortestCommonAncestor(d);
       System.out.println(d.toString());
       }

   // all WordNet nouns
   public Iterable<String> nouns() {
       List<String> a = new ArrayList<String>();
       Iterator<String> itr = m.values().iterator();
       while(itr.hasNext()) {
           String s = itr.next();
           if (isNoun(s)) {
               a.add(s);   
           }   
       }
    return a;   
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
       return word.matches("[a-zA-Z]+");    
   }

   // a synset (second field of synsets.txt) that is a shortest common ancestor
   // of noun1 and noun2 (defined below)
   public String sca(String noun1, String noun2) {
       int key = 0;
       int key1 = 0;
       
       for(Map.Entry<Integer, String> entry: m.entrySet()) {
           if(noun1.equals(entry.getValue())) {
               key = entry.getKey(); 
               break;
           }
       }
       
       for(Map.Entry<Integer, String> entry1: m.entrySet()) {
           if(noun2.equals(entry1.getValue())) {
               key1 = entry1.getKey();
               break;
           }
       }
       if (s.ancestor(key, key1) == -1) {
           return " No common ancestor"; 
       } else {
           return m1.get(s.ancestor(key, key1));
       }
          
   }

   // distance between noun1 and noun2 (defined below)
   public int distance(String noun1, String noun2) {
       int key = 0;
       int key1 = 0;
       
       for(Map.Entry<Integer, String> entry: m.entrySet()) {
           if(noun1.equals(entry.getValue())) {
               key = entry.getKey(); 
               break;
           }
       }
       
       for(Map.Entry<Integer, String> entry1: m.entrySet()) {
           if(noun2.equals(entry1.getValue())) {
               key1 = entry1.getKey();
               break;
           }
       }
       
       return s.length(key, key1);
   }

   // do unit testing of this class
   public static void main(String[] args) throws IOException {
       WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
       System.out.println(w.sca("AND_circuit AND_gate","NAND_circuit NAND_gate"));
       System.out.println(w.distance("AND_circuit AND_gate","NAND_circuit NAND_gate"));
       
   }
}