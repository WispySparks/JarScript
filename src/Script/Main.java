package Script;

public class Main {
    public static void main(String[] args) {
        new Setup(); // compile files, java docs and create manifest.txt
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            System.out.println(e);
        }
        Jar jar = new Jar(); // create jar file
        String jarName = jar.getName();  // get the jar file name with extension
        String projName = jarName.substring(0, jarName.length()-4); // get the name of the project based on the jar
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            System.out.println(e);
        }
        Organizer organizer = new Organizer(projName, jarName, jar.getPath()); // create folder that everything goes inside
        String path = organizer.getPath(); // get the path of the folder
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            System.out.println(e);
        }
        new Runtime(jarName, projName, path); // create runtime for jar file
        new Batch(projName, path); // create batch file to run jar file with runtime
    }
}