package pl.lipinski.writer;

import java.util.Map;

public interface SopraSteriaWriter {
    void write(String path, Map<String, Long> data);
}
