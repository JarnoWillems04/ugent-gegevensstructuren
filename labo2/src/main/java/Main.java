import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author sleroux
 */
public class Main {

    private static void readQuestions(String filename, List<String> questions, List<String> animals, List<Map<String, Boolean>> answers) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileInputStream("src/main/resources/" + filename))) {
            int nAnimals = sc.nextInt();
            int nQuestions = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < nQuestions; i++) {
                String question = sc.nextLine();
                questions.add(question);
                answers.add(new HashMap<>());
            }

            for (int i = 0; i < nAnimals; i++) {
                String animal = sc.next();
                animals.add(animal);
                for (int j = 0; j < nQuestions; j++) {
                    boolean answer = sc.next().equals("y");
                    answers.get(j).put(animal, answer);
                }
                sc.nextLine();
            }
        }
    }
    /**
     * @param args the command line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        List<String> questions = new ArrayList<>();
        List<String>animals = new ArrayList<>();
        List<Map<String, Boolean>> answers = new ArrayList<>();
        
        readQuestions("animals_medium.txt", questions, animals, answers);
        
        System.out.println(questions);
        System.out.println(animals);

        for (Map<String, Boolean> a: answers){
            System.out.println(a);
        }

        BinaryTree bt = new BinaryTree();
        if (bt.build(questions, animals, answers)){
            System.out.println("Not enough information to build a tree");
        }
        else {
            System.out.println(bt);
            System.out.println("Hoogte: " + bt.height());
            System.out.println("Aantal bladeren: " + bt.numberOfLeaves());
            System.out.println("Aantal vertakkingen: " + bt.numberOfSplits());
            System.out.println("Gemiddelde diepte: " + bt.averageDepth());
        }
    }

}
