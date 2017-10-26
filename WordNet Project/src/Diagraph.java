import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Diagraph {
    private int V;
    private int E;
    @SuppressWarnings("rawtypes")
    private ArrayList [] adj;
    private ArrayList <ArrayList<Integer>> l = new ArrayList <ArrayList<Integer>>();
    ArrayList<Integer> inner = new ArrayList<Integer>();
    Map<Integer, String> m = new HashMap<Integer, String>();
    
    public Diagraph(Map<Integer, String> map) {
        m = map;
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("hypernyms.txt"), "latin1");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] wordsFromText = line.split("\\W");
                for (String word : wordsFromText) {
                    inner.add(Integer.parseInt(word));
                }
                ArrayList<Integer> newList = new ArrayList<>(inner);
                l.add(newList);
                inner.clear();
            }
                this.V = l.size();
                this.adj = new ArrayList [map.size()] ;
                for(int i = 0; i < adj.length ; i++) {
                     adj[i] = new ArrayList<Integer>();
                }
                for (int i = 0; i < l.size(); i++) {
                    int v = l.get(i).get(0);
                    for (int j = 1; j < l.get(i).size(); j++) {
                        int w = l.get(i).get(j); 
                        addEdge(v, w); 
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
    
    public int V() {
        return V;
    }
    
    public int E() {
        return E;
    }
    @SuppressWarnings("unchecked")
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @SuppressWarnings("unchecked")
    public void addEdge(int v, int w) {
        adj[v].add(w);     
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("V :" + this.V + "E :" + this.E + "\n");
        for(int i = 0; i < V; i++) {
            s.append(m.get(l.get(i).get(0))+" :" );
            for(int j = 0; j < adj[i].size(); j++) {
                s.append(m.get(adj[i].get(j))+ " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
