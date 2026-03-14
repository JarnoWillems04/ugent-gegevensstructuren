/**
 * A Simple BinaryTree class.
 *
 * @author sleroux
 */
public class BinaryTree {

    private BinaryTree left;
    private BinaryTree right;
    private String value;
    private int weight;

    // ToDo: add data attributes if needed
    // ToDo: add constructors or methods if needed
    // ToDo: implement toString

    public BinaryTree() {
        this.weight = 0;
    }

    public BinaryTree(BinaryTree other) {
        if (other != null) {
            this.value = other.value;

            if (other.left != null) {
                this.left = new BinaryTree(other.left);
            }

            if (other.right != null) {
                this.right = new BinaryTree(other.right);
            }
        }
    }

    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int depth) {
        String tabs = "\t".repeat(depth);
        String result = "";
        if (value != null) {
            result = value + " " + weight;
        }
        if (left != null) {
            result += "\n" + tabs + "--Y-->" + left.toString(depth + 1);
        }
        if (right != null) {
            result += "\n" + tabs + "--N-->" + right.toString(depth + 1);
        }
        return result;
    }

}
