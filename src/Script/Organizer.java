package Script;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Organizer {

    String path;

    Organizer(String name, String jarName, String jarPath) {
        createFolder(name, jarName, jarPath);
    }

    private void createFolder(String name, String jarName, String jarPath) {
        File folder = new File(name);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        path = folder.getAbsolutePath();
        try {
            Files.move(Paths.get(jarPath), Paths.get(path + "\\" + jarName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public String getPath() {
        return path;
    }
}
