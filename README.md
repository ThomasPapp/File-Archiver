# File-Archiver
A tool which archives file collections into a single .dat file.

The tool currently supports packing a collection of files based upon their extension. Support for multiple extenstion will come in the near future!

## Running the Tool
Running the tool is quite simple. The tool expects a few program arguments depending on the task.

### Packing
To pack/archive simply add `pack` as the first argument, then add the path/directory in which the files are located, 
the file extension, and lastely the archive name.

#### Example
`pack ./ .png images_archive`

In this example we're telling the tool to archive every .png inside the root folder into an archive named `images_archive`.

### Unpacking
To unpack your archive simply add `unpack` as the first argument, then add the path to the archive, and lastely the output 
directory.

#### Example
`unpack ./images_archive.dat ./images`

In this example we're telling the tool to unpack the `images_archive` located inside the root folder and to unpack the files 
into the `images` directory located inside the root folder.
