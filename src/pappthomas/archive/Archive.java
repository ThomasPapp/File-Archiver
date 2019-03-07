package pappthomas.archive;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author Thomas
 */
public final class Archive {

    private byte[] data;

    private int bufSize;

    private final Map<String, Archive> subArchives = new HashMap<>();

    public Archive() {
        this(null);
    }

    public Archive(byte[] data) {
        this.data = data;
    }

    public ByteBuffer pack() {
        ByteBuffer buffer = ByteBuffer.allocate(bufSize + 4);
        buffer.putInt(subArchives.size());
        subArchives.forEach((l, file) -> buffer.put(file.data));
        buffer.flip();
        put(buffer.array());
        return buffer;
    }

    public void forEachSub(BiConsumer<String, Archive> biConsumer) {
        for (String key : subArchives.keySet()) {
            biConsumer.accept(key, subArchives.get(key));
        }
    }

    public void createSubArchive(String label, byte[] data) {
        subArchives.computeIfAbsent(label, l -> {
            bufSize += data.length;
            return new Archive(data);
        });
    }

    private void put(byte[] data) {
        this.data = data;
    }

    public ByteBuffer toBuffer() {
        return ByteBuffer.wrap(data);
    }

    public Map<String, Archive> getSubArchives() {
        return subArchives;
    }
}