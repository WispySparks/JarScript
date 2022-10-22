package main.java.Script;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class Jar {

    Jar(String jarPath, String jarName) {
        createJar(jarPath, jarName);
    }

    private void createJar(String path, String name) {
        File jarFile = new File(path + "\\" + name + ".jar"); // make a jar file to test if it already exists
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

}
