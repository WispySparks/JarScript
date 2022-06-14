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

    Setup() {
        createManifest();
        compileFiles();
        createDocs();
    }

    private void compileFiles() {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c javac -cp src src\\" + packageName + "\\*.java -d bin");
        try {
            builder.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void createDocs() {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c javadoc src\\"+ packageName +"\\*.java -d docs");
        try {
            builder.start();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void createManifest() {     // creates the manifest document
        File manifest = new File("resources/manifest.txt");
        try {
            File top = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            File src = new File(top.getAbsolutePath()+"\\src\\"); //gets the path to where the main file would be located
            System.out.println(src);
            //Stream<Path> walk = Files.walk(Paths.get(src.getAbsolutePath()));
            Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\wispy\\Programming Projects\\Java\\JarScript\\src"));
            List<String> result = walk.filter(p -> !Files.isDirectory(p)).map(p -> p.toString()).filter(f -> f.contains("Main.java"))
            .collect(Collectors.toList());
            walk.close();
            File main = new File(result.get(0)); // file representation of the main.java
            packageName = main.getParentFile().getName(); // get the name of the package from the parent of the main.java file
            if (!manifest.exists()) {
                manifest.createNewFile();
                FileWriter writer = new FileWriter(manifest);
                writer.write("Manifest-Version: 1.0" + "\r\n" + "Class-Path: ." + "\r\n" + "Main-Class: src." + packageName + ".Main" + "\r\n");
                writer.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String manifestPath() {
        return null;
    }
}
