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
    public List<Pair> findDuplicates(double threshold) {
        return new ArrayList<>();
    }
    
}
