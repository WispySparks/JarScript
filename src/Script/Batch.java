package Script;

import java.io.*;

public class Batch {
    
    Batch(String projName, String path) {
        createBatch(projName, path);
    }

    private void createBatch(String projName, String path) {
        File batch = new File(path + "\\" + projName + ".bat");
        String batchPath = "\"%~dp0\\"+projName+"-runtime\\bin\\java.exe\"";
        try {
            if (!batch.exists()) {
                batch.createNewFile();
                FileWriter writer = new FileWriter(batch);
                writer.write("ECHO OFF" + "\r\n");
                writer.write("start cmd /c " + batchPath + " -jar " + projName +".jar");
                writer.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
