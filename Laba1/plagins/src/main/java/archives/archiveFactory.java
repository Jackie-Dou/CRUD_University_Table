package archives;

import java.io.IOException;

public interface archiveFactory {
    void archive(String fileName) throws IOException;
    void fromArchieve(String archiveName, String dirPath) throws IOException;
}

