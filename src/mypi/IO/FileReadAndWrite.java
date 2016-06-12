package mypi.IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author trav
 */
public abstract class FileReadAndWrite {
private final File FILENAME = new File("Planned_Events_POJO.txt");
//private final File TEST_FILE = new File("Testing_Events_POJO.txt");
    /**
     *
     * @return file that is being worked with
     */
    public File getFile() {
        return FILENAME;
        //return TEST_FILE;//un comment for tests
    }

    /**
     * 
     * @return an object that will write to file
     * @throws IOException 
     */
    public ObjectOutputStream getObjectOutputStream() throws IOException {
        return new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream( getFile() )));
    }

    /**
     *
     * @return an object that will read from file
     * @throws IOException
     * @throws EOFException
     */
    public ObjectInputStream getObjectInputStream() throws IOException, EOFException {
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream( getFile() )));
    }
}
