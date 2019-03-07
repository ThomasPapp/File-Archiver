package pappthomas.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Thomas
 */
public final class FileLoader {

    public static ByteBuffer load(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        FileChannel channel = raf.getChannel();
        return channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
    }

}
