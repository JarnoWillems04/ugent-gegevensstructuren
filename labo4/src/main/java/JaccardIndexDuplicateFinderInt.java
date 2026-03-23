import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sleroux
 */
public class JaccardIndexDuplicateFinderInt extends DuplicateFinder{

    public JaccardIndexDuplicateFinderInt(List<Set<String>> tokens) {
        super(tokens);
    }

    @Override
    public List<Pair> findDuplicates(double threshold) { // alleen returnen wat boven de threshold komt

        //omzetten naar ints
        List<Set<Integer>> tokensInts = new ArrayList<>();

        for(int i = 0; i < tokens.size(); i++){
            tokensInts.add(new HashSet<>());
            for (String s : tokens.get(i)){
                tokensInts.get(i).add(s.hashCode());
            }
        }

        ArrayList<Pair> pairs = new ArrayList<>();

        for (int i = 0; i < tokensInts.size(); i++){
            for (int j = i + 1 ; j < tokensInts.size(); j++){
                double jac = jaccard(tokensInts.get(i), tokensInts.get(j));
                if ( jac > threshold){
                    pairs.add(new Pair(i, j, jac));
                }
            }
        }

        return pairs;
    }

    private double jaccard(Set<Integer> A, Set<Integer> B){
        int AnB = 0;

        for(int s : A){
            if (B.contains(s)){
                AnB++;
            }
        }

        return (double) (AnB) / (A.size() + B.size() - AnB);
    }
}
