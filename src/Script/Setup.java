package Script;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Setup {
    
    String packageName;
    File top;

    Setup() {
        createManifest();
        compileFiles();
        createDocs();
    }

    private void compileFiles() {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c javac -cp src src\\" + packageName + "\\*.java -d bin");
        try {
            builder.start();
            System.out.println("Files Compiled Successfully");
        } catch (IOException e) {
            System.out.println("Compile Exception: " + e);
        }
    }

    private void createDocs() {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c javadoc src\\"+ packageName +"\\*.java -d docs");
        try {
            builder.start();
            System.out.println("Docs Created Successfully");
        } catch (IOException e) {
            System.out.println("Docs Exception: " + e);
        }
    }

    private void createManifest() {     // creates the manifest document
        File manifest = new File("resources/manifest.txt");
        try {
            top = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            File src = new File(top.getAbsolutePath()+"\\src\\"); //gets the path to where the main file would be located
            Stream<Path> walk = Files.walk(Paths.get(src.getAbsolutePath()));
            // Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\wispy\\Programming Projects\\Java\\JarScript\\src"));
            List<String> result = walk.filter(p -> !Files.isDirectory(p)).map(p -> p.toString()).filter(f -> f.contains("Main.java"))
            .collect(Collectors.toList()); // searches for the main file
            walk.close();
            File main = new File(result.get(0)); // file representation of the main.java
            packageName = main.getParentFile().getName(); // get the name of the package from the parent of the main.java file
            manifest.createNewFile(); // create the manifest
            FileWriter writer = new FileWriter(manifest); // write everything to it
            writer.write("Manifest-Version: 1.0" + "\r\n" + "Class-Path: ." + "\r\n" + "Main-Class: " + packageName + ".Main" + "\r\n");
            writer.close();
            System.out.println("Manifest Created Successfully");
        } catch (Exception e) {
            System.out.println("Manifest Exception: " + e);
        }
    }

    public String manifestPath() {
        return null;
    }

    public String topName() {
        return top.getName();
    }
}
