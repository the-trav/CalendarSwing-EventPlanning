package mypi.IO;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author trav
 * abstract class that provides further classes the ability to write to correct file.
 */
abstract class FileWriteIO implements IFileInfo{ 

    public ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream( FILENAME ) ) );
    }

}
