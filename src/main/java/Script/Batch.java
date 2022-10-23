package main.java.Script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Batch {
    
    Batch(String projName, String path) {
        createBatch(projName, path);
    }

    private void createBatch(String projName, String path) {
        File batchFile = new File(path + "\\" + projName + ".bat");
        String batchPath = "\"%~dp0"+projName+"-runtime\\bin\\java.exe\"";
        try {
            if (!batchFile.exists()) {
                batchFile.createNewFile();
                FileWriter writer = new FileWriter(batchFile);
                writer.write("ECHO OFF" + "\r\n");
                writer.write("start cmd /c " + batchPath + " -jar " + projName +".jar");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
