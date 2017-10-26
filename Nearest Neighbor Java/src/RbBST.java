
import java.util.LinkedList;
import java.util.Queue;


public class RbBST<Key extends Comparable<Key>> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    
    private class Node {
     private Key key;
     private Node left, right;
     private int count;
     private boolean colour;
     
     public Node(Key key, boolean colour) {
         this.key = key;
         this.colour = colour;
     }
    }
    public void put(Key key) { 
        root = put(root, key); 
        root.colour=BLACK;
        }
    private Node put(Node x, Key key) {
        if (x == null) return new Node(key, RED);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key);
        else if (cmp > 0)
            x.right = put(x.right, key);
        else if (cmp == 0)
            x.key = key;
        x.count = 1 + size(x.left) + size(x.right);
        if (isRed(x.right) && !isRed(x.left))      x = rotateLeft(x);
        if (isRed(x.left)  &&  isRed(x.left.left)) x= rotateRight(x);
        if (isRed(x.left)  &&  isRed(x.right))     flipColors(x);

        return x;
    }
    private void flipColors(Node x) {
        x.colour = RED;
        x.left.colour = BLACK;
        x.right.colour = RED;
    }
    private Node rotateRight(Node x) {
        Node h = x.left;
        x.left = h.right;
        h.right = x;
        h.colour = x.colour;
        x.colour = RED;
        return h;
    }
    private Node rotateLeft(Node x) {
        Node h = x.right;
        x.right = h.left;
        h.left = x;
        h.colour = x.colour;
        x.colour = RED;
        return h;
    }
    private boolean isRed(Node x) {
        if(x==null) return false;
        return x.colour == RED;
    }
   
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) 
            return null;
        return x.key;
        }
    private Node floor(Node x, Key key) {
        if (x == null) 
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) 
            return x;
        if (cmp < 0) 
            return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) 
            return t;
        else 
            return x;
        } 
    public Key cieling (Key key)
    {
     Node x = cieling(root, key);
     if (x == null) return null;
     return x.key;
    }
    private Node cieling(Node x, Key key) {
        if (x == null) 
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) 
            return x;
        if (cmp < 0) {
            Node t = cieling(x.left, key); 
        if (t != null) return t;
        else return x; 
    } 
    return cieling(x.right, key); 
    }
    public int size() { 
        return size(root); 
        }
    private int size(Node x) {
        if (x == null) 
            return 0;
        return x.count;
    }
    public boolean contains(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) 
                x = x.left;
            else if (cmp > 0) 
                x = x.right;
            else if (cmp == 0) 
                return true;
            }
        return false;
    }
    public void deleteMin() { 
        root = deleteMin(root); 
        }
    private Node deleteMin(Node x) {
        if (x.left == null) 
            return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
    private Node min(Node x) { 
        if (x.left == null) return x; 
        else return min(x.left); 
    } 
    public void delete(Key key) { 
        root = delete(root, key); 
        }
    private Node delete(Node x, Key key) {
        if (x == null) 
            return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) 
        x.left = delete(x.left, key);
    else if (cmp > 0) 
        x.right = delete(x.right, key);
    else {
        if (x.right == null) 
            return x.left;
        if (x.left == null) 
            return x.right;
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        }
    x.count = size(x.left) + size(x.right) + 1;
    return x;
    } 
    public Iterable<Key> inorder() {
    Queue<Key> q = new LinkedList<Key>();
    inorder(root, q);
    return q;
    }
    private Queue<Key> inorder(Node x, Queue<Key> q) {
    if (x == null) return null;
    inorder(x.left, q);
    q.add(x.key);
    inorder(x.right, q);
    return q;
    }
   
}