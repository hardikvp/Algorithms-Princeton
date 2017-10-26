import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShortestCommonAncestor {
    private Map <Integer, Integer> m2 = new HashMap <Integer, Integer> ();
    private Map <Integer, Integer> m3 = new HashMap <Integer, Integer> ();
    private Diagraph g;
    private boolean[] marked;
    private boolean[] marked1;
    private int distance = 0;
    private int distance1 = 0;

   // constructor takes a rooted DAG as argument
   public ShortestCommonAncestor(Diagraph G) {
       this.g = G;
       marked = new boolean[g.V()];
       marked1 = new boolean[g.V()];
   }

   // length of shortest ancestral path between v and w
   public int length(int v, int w) {
       if(ancestor(v,w) == -1) {
           return 0;
       } else {
           return m2.get(dfs1(g,w)) + m3.get(dfs1(g,w));
           }
       
       }

   // a shortest common ancestor of vertices v and w
   public int ancestor(int v, int w) {
       m2.put(v, distance);
       m3.put(w, distance1);
       dfs(g,v);
       return dfs1(g,w);
       }

   private int dfs1(Diagraph g2, int y) {
       marked1[y] = true;
       if(m3.get(y) == 0) {
           distance1 = 0;
       }
       for (int x : g.adj(y)) {
           if (!marked1[x]) {
               distance1 = distance1 + 1;
               m3.put(x, distance1);
               if(m2.containsKey(x)) {
                   distance1 = 0;
                   return x;
               } else {
                   dfs1(g, x);
                   }
               }
           }
       distance1 = 0;
       return -1;
       }
   private void dfs(Diagraph g2, int v) {
       marked[v] = true;
      
       if(m2.get(v) == 0) {
           distance = 0;
       }
       for (int w : g.adj(v)) {
           if (!marked[w]) {
               distance = distance + 1;
               m2.put(w, distance);
               dfs(g, w);
           }
       }
    
}

// length of shortest ancestral path of vertex subsets A and B
   public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
       Iterator<Integer> itr = subsetA.iterator();
       Iterator<Integer> itr1 = subsetB.iterator();
       
       return 0;
       }

   // a shortest common ancestor of vertex subsets A and B
   public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
       return 0;
       }

   // do unit testing of this class
   public static void main(String[] args) {
       
   }
}