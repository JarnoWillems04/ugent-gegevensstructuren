import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sleroux
 */
public class JaccardIndexDuplicateFinder extends DuplicateFinder{

    public JaccardIndexDuplicateFinder(List<Set<String>> tokens) {
        super(tokens);
    }

    @Override
    public List<Pair> findDuplicates(double threshold) { // alleen returnen wat boven de threshold komt
        ArrayList<Pair> pairs = new ArrayList<>();


        for (int i = 0; i < tokens.size(); i++){
            for (int j = i + 1 ; j < tokens.size(); j++){
                double jac = jaccard(tokens.get(i), tokens.get(j));
                if ( jac > threshold){
                    pairs.add(new Pair(i, j, jac));
                }
            }
        }

        return pairs;
    }

    private double jaccard(Set<String> A, Set<String> B){
        int AnB = 0;

        for(String s : A){
            if (B.contains(s)){
                AnB++;
            }
        }

        return (double) (AnB) / (A.size() + B.size() - AnB);
    }
}
