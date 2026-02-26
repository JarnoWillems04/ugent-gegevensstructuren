import java.util.Random;

public class SkipList implements SortedList {

    private static class Node {
        int key;
        Node[] next;
    }

    private int size = 0;
    private final int height;
    private final Node start;
    private final Random rand = new Random();

    public SkipList(int height) {
        this.height = height;
        start = new Node();
        start.next = new Node[height];
    }

    @Override
    public void insert(int key) {
        Node[] nodes = new Node[height];
        Node n = start;
        for (int i = 0; i < height; i++) {
            while (n.next[i] != null && n.next[i].key <= key) {
                if (n.next[i].key == key){ // check voor dubbels -> hierdoor <= in while
                    return;
                }
                n = n.next[i];
            }
            nodes[i] = n;
        }

        // 3 7 9

        Node nw = new Node();
        nw.key = key;
        nw.next = new Node[height];
        nw.next[height - 1] = n.next[height - 1];
        n.next[height - 1] = nw;

        int i = height - 2;
        while (i >= 0 && coinToss()) {
            nw.next[i] = nodes[i].next[i];
            nodes[i].next[i] = nw;
            i--;
        }

        size++;
    }

    @Override
    public boolean contains(int key) {
        Node n = start;
        for (int i = 0; i < height; i++) {
            while (n.next[i] != null && n.next[i].key <= key) {
                if (n.next[i].key == key){ // check voor dubbels -> hierdoor <= in while
                    return true;
                }
                n = n.next[i];
            }
        }

        return false;
    }

    @Override
    public void remove(int key) {
        if (!this.contains(key)){
            return;
        }

        Node n = start;
        Node[] nodes = new Node[height];
        for (int i = 0; i < height; i++) {
            while (n.next[i] != null && n.next[i].key < key) {
                n = n.next[i];
            }
            if (n.next[i] != null && n.next[i].key == key){
                nodes[i] = n;
            }
        }

        for (int i = 0; i < nodes.length;i++){
            if (nodes[i] != null){
                System.out.println(nodes[i].key);
                nodes[i].next[i] = nodes[i].next[i].next[i];
            }
        }
        size--;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int[] toArray() {
        int[] array = new int[size];
        Node n = start;
        int i = 0;
        while (n.next[height - 1] != null){
            array[i] = n.next[height - 1].key;
            n = n.next[height -1];
            i++;
        }
        return array;
    }

    @Override
    public void clear() {
        size = 0;
        start.next = new Node[height];
    }

    private boolean coinToss() {
        return rand.nextBoolean();
    }

    @Override
    public void print() {
        for (int i = 0; i < height; i++) {
            System.out.print("Niveau " + (i + 1) + ": ");

            Node n = start;
            while (n.next[i] != null) {
                System.out.print(n.next[i].key + " ");
                n = n.next[i];
            }

            System.out.println();
        }
    }
}
