package pappthomas.pack;

import pappthomas.archive.Archive;
import pappthomas.util.BufferUtil;
import pappthomas.util.FileLoader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * @author Thomas
 */
public class ArchivePacker {

    public static void pack(File directory, String extension, String label) {
        Archive archive = new Archive();
        try {
            Files.walk(directory.toPath()).forEach(path -> {
                File file = new File(path.toString());
                if (file.isDirectory())
                    return;

                // check if we need to do extension specific and if it matches said extension
                if (!file.getName().endsWith(extension))
                    return;

                try {
                    ByteBuffer buffer = FileLoader.load(file);
                    String filename = file.getName();

                    int size = buffer.remaining();
                    int labelSize = filename.length() + 1;
                    int totalSize = size + labelSize + 4;
                    ByteBuffer archiveFileBuffer = ByteBuffer.allocate(totalSize);
                    BufferUtil.putString(archiveFileBuffer, filename);
                    archiveFileBuffer.putInt(size);
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    archiveFileBuffer.put(data);
                    archiveFileBuffer.flip();

                    archive.createSubArchive(filename, archiveFileBuffer.array());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (archive.getSubArchives().isEmpty()) {
            System.out.println("Nothing to archive");
            return;
        }

        File file = new File("./"+ label +".dat");
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            FileChannel channel = raf.getChannel();
            channel.write(archive.pack());
            System.out.println("Files archived!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
