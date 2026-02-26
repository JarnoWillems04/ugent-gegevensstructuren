import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sleroux
 */
public class BinaryTree {

    private BinaryTree left;
    private BinaryTree right;
    private String value;

    public boolean build(List<String> questions, List<String> animals, List<Map<String, Boolean>> answers) {
        if (animals.isEmpty()){
            return false;
        }
        if (questions.isEmpty() || answers.isEmpty() || animals.size() == 1) {
            if (animals.size() == 1) {
                value = animals.get(0);
                return false;
            } else {
                String out = "Can not distinghuish between ";
                for (int i = 0; i < animals.size(); i++) {
                    out += animals.get(i) + " ";
                }
                System.out.println(out);
            }
            return true;
        } else {
            List<String> next_questions = questions.subList(1, questions.size());

            value = questions.get(0);

            List<Map<String, Boolean>> answers_left = new ArrayList<>(answers.size() - 1);
            List<Map<String, Boolean>> answers_right = new ArrayList<>(answers.size() - 1);
            List<String> animals_left = new ArrayList<>();
            List<String> animals_right = new ArrayList<>();

            for (int i = 0; i < answers.size() - 1; i++) {
                answers_left.add(new HashMap<>());
                answers_right.add(new HashMap<>());
            }

            for (Map.Entry<String, Boolean> entry : answers.get(0).entrySet()) {
                if (entry.getValue()) {
                    for (int i = 1; i < answers.size(); i++) {
                        answers_left.get(i - 1).put(entry.getKey(), answers.get(i).get(entry.getKey()));
                    }
                    animals_left.add(entry.getKey());
                } else {
                    for (int i = 1; i < answers.size(); i++) {
                        answers_right.get(i - 1).put(entry.getKey(), answers.get(i).get(entry.getKey()));
                    }
                    animals_right.add(entry.getKey());
                }
            }
            boolean leftCheck = true;
            boolean rightCheck = true;

            if (!animals_left.isEmpty()){
                left = new BinaryTree();
                leftCheck = left.build(next_questions, animals_left, answers_left);
            }
            if (!animals_right.isEmpty()){
                right = new BinaryTree();
                rightCheck = right.build(next_questions, animals_right, answers_right);
            }
            return leftCheck && rightCheck;
        }
//        return false;
    }

    public int height() {
        int leftHeight = (left == null) ? 0 : left.height();
        int rightHeight = (right == null) ? 0 : right.height();

        return 1 + Math.max(leftHeight, rightHeight);
    }
    public int numberOfLeaves(){
        if (left == null && right == null) {
            return value != null ? 1 : 0;
        }
        int leaves = 0;
        if (left != null){
            leaves += left.numberOfLeaves();
        }
        if (right != null){
            leaves += right.numberOfLeaves();
        }
        return leaves;
    }

    public int numberOfSplits() {
        int leftSplits = (left == null) ? 0 : left.numberOfSplits();
        int rightSplits = (right == null) ? 0 : right.numberOfSplits();

        if (left == null && right == null) {
            return 0;
        }

        return 1 + leftSplits + rightSplits;
    }

    public double averageDepth() {
        return  ((double) (height() * numberOfLeaves())) /(double) numberOfSplits();
    }

    @Override
    public String toString() {
        return toString(0);
    }

    private String toString(int depth) {
        String tabs = "\t".repeat(depth);
        String result = "";
        if (value != null) {
            result = value;
        }
        if (left != null) {
            result += "\n" + tabs + "--Y-->" + left.toString(depth + 1);
        }
        if (right != null){
            result += "\n" + tabs + "--N-->" + right.toString(depth + 1);
        }
        return result;
    }

}
