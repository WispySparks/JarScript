package jarscript;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Shell {
    
    Shell(String projName, String path) {
        createShell(projName, path);
    }

    private void createShell(String projName, String path) {
        File shellFile = new File(path + "/" + projName + ".sh");
        String shellPath = "\"/"+projName+"-runtime/bin/java.exe\"";
        try {
            if (!shellFile.exists()) {
                shellFile.createNewFile();
                FileWriter writer = new FileWriter(shellFile);
                writer.write("SCRIPT=$(readlink -f \"$0\")" + "\n");
                writer.write("SCRIPTPATH=$(dirname \"$SCRIPT\")" + "\n");
                writer.write("SCRIPTPATH+=" + shellPath + "\n");
                writer.write("$SCRIPTPATH" + " -jar " + projName +".jar");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
