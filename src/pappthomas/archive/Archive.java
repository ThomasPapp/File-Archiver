package pappthomas.archive;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Thomas
 */
public final class Archive {

    private final String label;

    private byte[] data;

    private final Map<String, Archive> subArchives = new HashMap<>();

    public Archive(String label, byte[] data) {
        this.label = label;
        this.data = data;
    }

    public void createSubArchive(String label, byte[] data) {
        subArchives.computeIfAbsent(label, l -> new Archive(label, data));
    }

    public Archive getSubArchive(String label) {
        return subArchives.get(label);
    }

    public void put(byte[] data) {
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public byte[] getData() {
        return data;
    }

    public Map<String, Archive> getSubArchives() {
        return subArchives;
    }
}