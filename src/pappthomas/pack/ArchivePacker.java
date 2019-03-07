package pappthomas.pack;

import pappthomas.archive.Archive;
import pappthomas.util.BufferUtil;

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

    private static int size;

    public static void pack(File directory, String extension, String label) {
        Archive archive = new Archive(label, new byte[] {});
        try {
            Files.walk(directory.toPath()).forEach(path -> {
                File file = new File(path.toString());
                if (file.isDirectory())
                    return;

                // check if we need to do extension specific and if it matches said extension
                if (!file.getName().endsWith(extension))
                    return;

                try (RandomAccessFile raf = new RandomAccessFile(file, "r"); FileChannel channel = raf.getChannel()) {
                    ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,channel.size());

                    String filename = file.getName().replace(extension, "");

                    int size = buffer.remaining() + 4;
                    int labelSize = filename.length() + 1;
                    int totalSize = size + labelSize;
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
            System.out.println("No archives");
            return;
        }

        archive.getSubArchives().forEach((l, file) -> size += file.getData().length);
        ByteBuffer buffer = ByteBuffer.allocate(size);
        archive.getSubArchives().forEach((l, file) -> buffer.put(file.getData()));
        buffer.flip();
        archive.put(buffer.array());

        File file = new File("./"+ label +".dat");
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel channel = raf.getChannel()) {
            channel.write(buffer);
            System.out.println("Files archived!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void compile(ByteBuffer buffer, byte[] payload) {

    }

}
