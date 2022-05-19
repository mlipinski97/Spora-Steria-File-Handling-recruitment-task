package pl.lipinski.reader;

import java.util.Map;

public interface SopraSteriaReader {
    Map<String, Long> read(String path);
}
