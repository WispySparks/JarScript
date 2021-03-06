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
                if (line.contains(jar + " -> ")) {
                    deps.add(line.substring(jar.length()+4));
                }
            }
            String libs = "";
            for (String string : deps) {
                if (string != "not found") {
                    if (deps.get(deps.size()-1) != string) {
                        libs += string + ",";
                    }
                    else {
                        libs += string;
                    }
                }
                System.out.println("Some Dependencies weren't Found");
            }
            System.out.println("Dependencies: " + libs);
            File runtime = new File(name+"-runtime");
            if (!runtime.exists()) {
                builder2 = new ProcessBuilder("cmd.exe", "/c jlink --output " + pname + " --add-modules " + libs);
                proc = builder2.start();
                System.out.println("Runtime Created Successfully");
            }
        } catch (IOException e) {
            System.out.println("Runtime Exception: " + e);
        }
    }
}
