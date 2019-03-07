package pappthomas.archive;

import pappthomas.util.BufferUtil;
import pappthomas.util.FileLoader;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author Thomas
 */
public final class ArchiveLoader {

    public static Archive load(File file) throws IOException {
        Archive archive = new Archive(file.getName().replace(".dat", ""), null);
        ByteBuffer buffer = FileLoader.load(file);

        int length = buffer.getInt();
        for (int i = 0; i < length; i++) {
            String filename = BufferUtil.getString(buffer);
            int size = buffer.getInt();
            byte[] data = new byte[size];
            buffer.get(data);
            archive.createSubArchive(filename, data);
        }

        archive.pack();
        return archive;
    }

}
