package pappthomas.unpack;

import pappthomas.archive.Archive;
import pappthomas.archive.ArchiveLoader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Thomas
 */
public class ArchiveUnpacker {

    public static void unpack(File file, String outputDirectory)  {
        try {
            Archive archive = ArchiveLoader.load(file);
            archive.forEachSub((filename, _archive) -> writeFile(_archive.toBuffer(), new File(outputDirectory +"/"+ filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(ByteBuffer buffer, File file) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
