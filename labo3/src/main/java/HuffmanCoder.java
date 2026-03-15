import java.util.ArrayList;

public class HuffmanCoder {

    private final BinaryTree tree;

    public HuffmanCoder(String text) {
        StringBuilder reducedText = new StringBuilder(); // tekst zonder dubbele charachters
        ArrayList<Integer> freq = new ArrayList<>(); // spiegel van reducedText

        for (char c : text.toCharArray()) {
            if (!reducedText.toString().contains(c + "")) {
                reducedText.append(c);
                freq.add(1);
            } else {
                freq.set(reducedText.indexOf(c + ""), freq.get(reducedText.indexOf(c + "")) + 1);
            }
        }

        ArrayList<BinaryTree> trees = new ArrayList<>();
        for (int i = 0; i < freq.size(); i++){
            BinaryTree bt = new BinaryTree();
            bt.setWeight(freq.get(i));
            bt.setValue(""+reducedText.charAt(i));
            trees.add(bt);
        }

        /*

        1. Begin met alle nodes los (een bos van bomen).
        2. Neem de twee met het kleinste gewicht.
        3. Maak een nieuwe parent-node.
        4. Stop die terug in de lijst.
        5. Herhaal tot er één boom overblijft.

         */

        while( trees.size()!= 1){
            BinaryTree bt1 = trees.get(0);
            BinaryTree bt2 = trees.get(1);

            for(int i = 2; i < trees.size(); i++){
                if (bt1.getWeight() > trees.get(i).getWeight()){
                    bt1 = trees.get(i);
                }
                else if (bt2.getWeight() > trees.get(i).getWeight()){
                    bt2 = trees.get(i);
                }
            }

            BinaryTree bt = new BinaryTree();
            bt.setLeft(bt1);
            bt.setRight(bt2);
            bt.setValue(bt1.getValue() + bt2.getValue());
            bt.setWeight(bt1.getWeight() + bt2.getWeight());

            trees.remove(bt1);
            trees.remove(bt2);
            trees.add(bt);
        }

        tree = trees.getFirst();
    }

    // ToDo: add additional methods if needed

    /**
     * Encode a text by replacing each character with its Huffman code
     *
     * @param text The text to encode
     * @return A String containing the binary representations of each character
     * separated by spaces
     */
    public String encode(String text) {
        StringBuilder rc = new StringBuilder();

        BinaryTree traversalTree = new BinaryTree(tree);

        for(char c : text.toCharArray()){

            while (!traversalTree.isLeaf()){
                if (traversalTree.getLeft().getValue().contains(c+"")){
                    rc.append("1");
                    traversalTree = traversalTree.getLeft();
                }
                else{
                    rc.append("0");
                    traversalTree = traversalTree.getRight();
                }
            }
            traversalTree = new BinaryTree(tree);
            rc.append(" ");
        }

        return rc.toString();
    }

    /**
     * Decode a text by replacing each Huffman code with its corresponding
     * character
     *
     * @param code A String containing the binary representations of each
     *             character separated by spaces
     * @return The decoded text
     */
    public String decode(String code) {
        StringBuilder text = new StringBuilder();

        BinaryTree traversalTree = new BinaryTree(tree);

        for (char c : code.toCharArray()){
            if (c == ' '){
                text.append(traversalTree.getValue());
                traversalTree = new BinaryTree(tree);
            }
            else if(c == '1') {
                traversalTree = traversalTree.getLeft();
            }
            else {
                traversalTree = traversalTree.getRight();
            }
        }

        return text.toString();
    }

    public BinaryTree getTree() {
        return tree;
    }

}
