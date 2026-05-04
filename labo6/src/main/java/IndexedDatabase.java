import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author sleroux
 */
public class IndexedDatabase extends Database{
    private BTree index;
    
    public IndexedDatabase(String filename) throws FileNotFoundException, IOException {
        super(filename);
        // ToDo: Go through the entire file, build the B-tree that stores record ID's together with the corresponding line numbers
    }

    @Override
    public Record findById(int value) throws IOException {
        // Todo: replace this with a lookup in the B-tree to find the offset in the file where the corresponding line starts
        // Todo: read this line and create a Record object out of it
       return super.findById(value);
    }
    
    
    
}
