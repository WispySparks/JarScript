package Script;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Jar {

    String jarName;
    File jarFile;
    
    Jar() {
        createJar();
    }

    private void createJar() {
        try {
            jarName = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getName();
        } catch (URISyntaxException e1) {
            System.out.println(e1);
        }
        jarFile = new File(jarName+".jar");
        if (!jarFile.exists()) {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c jar cvfm " + jarName + ".jar resources\\manifest.txt -C bin . resources");
            try {
                builder.start();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public String getName() {
        return jarName+".jar";
    }

    public String getPath() {
        return jarFile.getAbsolutePath();
    }

}
