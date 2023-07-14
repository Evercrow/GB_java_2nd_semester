package Exceptions.HW3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DataSave implements AutoCloseable{
    @Override
    public void close() throws Exception {

    }
    private void saveToFile(String[] data) throws IOException {
        File f = new File(data[0]+".txt");
        try(FileWriter fw = new FileWriter( f ,true)){
            for (String field : data){
                fw.write(field + " ");
            }
        }

    }
}
