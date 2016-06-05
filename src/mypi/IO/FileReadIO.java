package mypi.IO;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author trav 
 * Class that is used to read from the designated file
 */
    public abstract class FileReadIO implements IFileInfo {
    
    
    public File getFile(){
        return FILENAME;
    }
    
    public ObjectInputStream getObjectInputStream() throws IOException,EOFException {
        if(!getFile().exists()){
                getFile().createNewFile();
            }
        return new ObjectInputStream(new BufferedInputStream(new FileInputStream(FILENAME)));
    }

}
