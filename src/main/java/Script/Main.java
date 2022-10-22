package main.java.Script;

public class Main {

    public static void main(String[] args) { 
        Organizer organizer = new Organizer(); // create folder that everything goes inside
        String path = organizer.getPath(); // get the path of the folder
        String name = organizer.getName(); // get name of project
        new Jar(path, name); // create jar file
        // sleep();
        // new Runtime(name + ".jar", name, path); // create runtime for jar file //Todo runtime still isnt created sometimes
        // sleep();
        // new Batch(name, path); // create batch file to run jar file with runtime
    }

    public static void sleep() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}