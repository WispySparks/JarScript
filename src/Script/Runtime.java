package Script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.util.ArrayList;

public class Runtime {

    private ProcessBuilder builder;

    Runtime (String jar, String projName, String path) {
        builder = new ProcessBuilder("cmd.exe", "/c jdeps " +  "\"" + path + "\\" + jar);
        createRuntime(jar, projName, path);
    }

    private void createRuntime(String jar, String name, String path) {
        ProcessBuilder builder2;
        ArrayList<String> deps = new ArrayList<String>();
        String pname = "\"" + path + "\\" + name + "-runtime\"";
        try {
            Process proc = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains(jar + " -> ")) {
                    deps.add(line.substring(jar.length()+4));
                }
            }
            String libs = "";
            for (String string : deps) {
                if (deps.get(deps.size()-1) != string) {
                    libs += string + ",";
                }
                else {
                    libs += string;
                }
            }
            File runtime = new File(name+"-runtime");
            if (!runtime.exists()) {
                builder2 = new ProcessBuilder("cmd.exe", "/c jlink --output " + pname + " --add-modules " + libs);
                proc = builder2.start();
            }
        } catch (IOException e) {
            System.out.println("Exception:  " + e);
        }
    }
}
