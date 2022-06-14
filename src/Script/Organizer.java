package Script;

import java.io.File;

public class Organizer {

    String path;

    Organizer(String name) {
        createFolder(name);
    }

    private void createFolder(String name) {
        File folder = new File(name);
        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("Folder Created Successfully");
        }
        path = folder.getAbsolutePath();
    }

    public String getPath() {
        return path;
    }
}
