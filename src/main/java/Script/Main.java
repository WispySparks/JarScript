package main.java.Script;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) { 
        Scanner s = new Scanner(System.in);
        Organizer organizer = new Organizer(); // create folder that everything goes inside
        String folderPath = organizer.getPath(); // get the path of the folder
        String folderName = organizer.getName(); // get name of project
        System.out.println("Input main class path. e.g. com.example.package.Main");
        String mainClass = s.nextLine();
        new Jar(folderPath, folderName, mainClass); // create jar file
        s.close();
        // sleep();
        // new Runtime(folderName + ".jar", folderName, folderPath); // create runtime for jar file //Todo runtime still isnt created sometimes
        // sleep();
        // new Batch(folderName, folderPath); // create batch file to run jar file with runtime
    }

    public static void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}