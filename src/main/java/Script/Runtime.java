package main.java.Script;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

public class Runtime {

    Runtime (String jar, String projName, String path) {
        createRuntime(jar, projName, path);
    }

    private void createRuntime(String jar, String projName, String path) {
        ProcessBuilder depsBuilder = new ProcessBuilder("cmd.exe", "/c jdeps -s " + path + "\\" + jar);
        List<String> deps = new ArrayList<>();
        depsBuilder.redirectError(Redirect.INHERIT);
        try {
            Process proc = depsBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                deps.add(line.substring(jar.length()+4));
            }
            String libs = "";
            for (String string : deps) {
                libs += string + ",";
            }
            libs = libs.substring(0, libs.length()-1);
            System.out.println(jar + " Dependencies: " + libs);
            File runtime = new File(projName+"-Runtime");
            if (!runtime.exists()) {
                String runtimePath = "\"" + path + "\\" + projName + "-Runtime\"";
                ProcessBuilder runtimeBuilder = new ProcessBuilder("cmd.exe", "/c jlink --output " + runtimePath + " --add-modules " + libs);
                runtimeBuilder.redirectError(Redirect.INHERIT);
                runtimeBuilder.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
