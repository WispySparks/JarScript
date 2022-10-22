package main.java.Script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Jar {

    String mainClass;

    Jar(String jarPath, String jarName, String mainClass) {
        this.mainClass = mainClass;
        createJar(jarPath, jarName);
    }

    private void createJar(String path, String name) {
        File manifest = createManifest(path);
        File jarFile = new File(path + "\\" + name + ".jar"); // make a jar file reference to test if it already exists
        if (!jarFile.exists()) {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c jar -c -v -f " +  
            "\"" + jarFile.getAbsolutePath() + "\" -m \"" + manifest.getAbsolutePath() + "\" -C bin .");
            builder.inheritIO();
            try {
                builder.start();    // create the jar file
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 
    }

    private File createManifest(String path) {
        File file = new File("");
        try {
            file = File.createTempFile("mf-", ".txt");
            FileWriter writer = new FileWriter(file); // write everything to it
            writer.write("Manifest-Version: 1.0" + "\r\n" + "Main-Class: " + mainClass + "\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
