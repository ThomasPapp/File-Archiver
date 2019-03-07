package pappthomas.archive;

/**
 * @author Thomas
 */
public final class ArchiveFile {

    private final String fileName;

    private byte[] data;

    public ArchiveFile(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }
}