package pappthomas;

import pappthomas.pack.ArchivePacker;
import pappthomas.unpack.ArchiveUnpacker;

import java.io.File;

/**
 * @author Thomas
 */
public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Missing args: [pack/unpack] [path] [etx] [label] | [archive] [output]");
            return;
        }

        System.out.println(args[0].equalsIgnoreCase("pack") ? "Packing..." : "Unpacking...");

        if (args[0].equalsIgnoreCase("pack")) {
            String path = args[1];
            String extension = args[2];
            String label = args[3];

            File file = new File(path);
            ArchivePacker.pack(file, extension, label);
        }
        else if (args[0].equalsIgnoreCase("unpack")) {
            String archive = args[1];
            String output = args[2];

            File file = new File(archive);
            ArchiveUnpacker.unpack(file, output);
        }
    }

}
