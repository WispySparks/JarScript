package main.java.Script;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class Jar {

    File jarFile;
    
    Jar(String path, String name) {
        createJar(path, name);
    }

    private void createJar(String path, String name) {
        jarFile = new File(path + "\\" + name + ".jar"); // make a jar file to test if it already exists
        System.out.println();
        if (!jarFile.exists()) {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c jar cvf " +  "\"" + jarFile.getAbsolutePath()
             + "\"" + " -C bin . resources");
            builder.redirectError(Redirect.INHERIT);
            try {
                builder.start();    // create the jar file
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    public String getPath() {
        return jarFile.getAbsolutePath();
    }

}
