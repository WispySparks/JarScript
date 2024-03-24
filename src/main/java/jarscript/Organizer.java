package jarscript;

import java.io.File;
import java.net.URISyntaxException;

public class Organizer {

    private File parentDirectory;
    private String path;

    Organizer() {
        createFolder();
    }

    private void createFolder() {
        try {
            parentDirectory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        File folder = new File(parentDirectory.getName()+"Bundle");
        if (!folder.exists()) {
            folder.mkdirs();
            while(!folder.exists());
        }
        path = folder.getAbsolutePath();
    }

    public String getName() {
        return parentDirectory.getName();
    }

    public String getPath() {
        return path;
    }
    
}
