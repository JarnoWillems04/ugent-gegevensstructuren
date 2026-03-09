import java.util.Arrays;

/**
 * This class builds a HuffmanTree in the constructor based on the provided text.
 * It can then use this tree to encode or decode arbitrary text.
 * @author sleroux
 */
public class HuffmanCoder {

    private final BinaryTree tree;

    public HuffmanCoder(String text) {

        tree = null;
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
        // ToDo use the Huffman tree to encode the text
        return null;
    }

    /**
     * Decode a text by replacing each Huffman code with its corresponding
     * character
     *
     * @param code A String containing the binary representations of each
     * character separated by spaces
     * @return The decoded text
     */
    public String decode(String code) {
        // ToDo use the Huffmantree to decode the code
        return null;
    }

    public BinaryTree getTree() {
        return tree;
    }

}
