package main.java.Script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Jar {

    Jar(String jarPath, String jarName) {
        createJar(jarPath, jarName);
    }

    private void createJar(String path, String name) {
        File jarFile = new File(path + "\\" + name + ".jar"); // make a jar file reference to test if it already exists
        if (!jarFile.exists()) {
            File manifest = createManifest(path);
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c jar -c -v -f " +  
            "\"" + jarFile.getAbsolutePath() + "\" -m \"" + manifest.getAbsolutePath() + "\" -C bin .");
            builder.inheritIO();
            try {
                builder.start();    // create the jar file
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!jarFile.exists());
        } 
    }

    private File createManifest(String path) {
        Scanner s = new Scanner(System.in);
        File file = new File("");
        file.deleteOnExit();
        try {
            file = File.createTempFile("mf-", ".txt");
            FileWriter writer = new FileWriter(file); // write everything to it
            System.out.println("Input main class path or leave blank for no main class. e.g. com.example.package.MainClass");
            String mc = s.nextLine();
            if (!mc.isEmpty()) {
                writer.write("Manifest-Version: 1.0" + "\r\n" + "Main-Class: " + mc + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.close();
        return file;
    }

}
