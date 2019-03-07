package pappthomas;

import pappthomas.pack.ArchivePacker;

import java.io.File;

/**
 * @author Thomas
 */
public class Main {

    public static void main(String[] args) {
        // settings: path etx:.scaperune | filename
        if (args.length < 1) {
            System.err.println("Missing args: [path] [pack/unpack] [etx] [label]");
            return;
        }

        System.out.println("running...");

        String path = args[0];
        boolean pack = args[1].equalsIgnoreCase("pack");
        String extension = args[2].split("etx:")[1];
        String label = args[3];

        if (pack) {
            File file = new File(path);
            ArchivePacker.pack(file, extension, label);
        }
    }

}
