package Script;

import java.io.File;
import java.io.IOException;

public class Jar {

    File jarFile;
    
    Jar(String path, String name) {
        createJar(path, name);
    }

    private void createJar(String path, String name) {
        jarFile = new File(path + "\\" + name + ".jar"); // make a jar file to test if it already exists
        if (!jarFile.exists()) {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c jar cvfm " +  "\"" + jarFile.getAbsolutePath() + "\"" + " resources\\manifest.txt -C bin . resources");
            try {
                builder.start();    // create the jar file
                System.out.println("Jar Created Successfully");
            } catch (IOException e) {
                System.out.println("Jar Exception: " + e);
            }
        }
    }

    public String getPath() {
        return jarFile.getAbsolutePath();
    }

}
