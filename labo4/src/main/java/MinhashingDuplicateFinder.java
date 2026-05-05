import java.util.*;

public class MinhashingDuplicateFinder extends DuplicateFinder {

    private final int c = 186581;
    protected int[][] minhashes;// A large prime number
    protected int k;

    public MinhashingDuplicateFinder(List<Set<String>> tokens, int k) {
        super(tokens);
        this.k = k;

        //omzetten naar ints
        List<Set<Integer>> codes = new ArrayList<>(tokens.size());
        HashMap<String, Integer> mapping = new HashMap<>(); // De Integer is gewoon een simpel getal, 1, 2, 3 ...

        for (Set<String> doc : tokens) {
            Set<Integer> code = new HashSet<>();
            for (String s : doc) {
                if (!mapping.containsKey(s)) {
                    mapping.put(s, mapping.size());
                }
                code.add(mapping.get(s));
            }
            codes.add(code);
        }

        Random rnd = new Random();
        minhashes = new int[codes.size()][k];

        for(int i = 0; i < k; i++){

            int a = rnd.nextInt(mapping.size());
            int b = rnd.nextInt(mapping.size());

            for (int doc = 0; doc < codes.size(); doc++){
                Set<Integer> document = codes.get(doc);

                int minHash = Integer.MAX_VALUE;
                int minIndex = 0;

                for(int code : document){
                    int h = (a * code + b) % c; // hashing
                    if (h < minHash){
                        minHash = h;
                        minIndex = code;
                    }
                }

                minhashes[doc][i] = minIndex; //per doc kleinste index
            }
        }

    }

    @Override
    public List<Pair> findDuplicates(double threshold) {
        List<Pair> output = new ArrayList<>();

        // Compare all documents with all other documents
        for (int i = 0; i < minhashes.length; i++) {
            int[] h1 = minhashes[i];
            for (int j = i + 1; j < minhashes.length; j++) {
                int equal = 0;
                int[] h2 = minhashes[j];

                // Check for each hash function if they resulted in the same element
                // for both documents
                for (int l = 0; l < k; l++) {
                    if (h1[l] == h2[l]) {
                        equal++;
                    }
                }

                // Estimate the jaccard similarity
                double score = ((double) equal) / k;
                if (score > threshold) {
                    output.add(new Pair(i, j, score));
                }

            }
        }
        return output;

    }
}
