package Script;

public class Main {

    public static boolean verbose = false;
    public static void main(String[] args) {
        Setup setup = new Setup(); // create manifest.txt
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
        Organizer organizer = new Organizer(setup.topName()); // create folder that everything goes inside
        String path = organizer.getPath(); // get the path of the folder
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
        new Jar(path, setup.topName()); // create jar file
        String jarName = setup.topName() + ".jar"; // get the jar file name with extension
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
        new Runtime(jarName, setup.topName(), path); // create runtime for jar file //Todo runtime still isnt created sometimes
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }
        new Batch(setup.topName(), path); // create batch file to run jar file with runtime
    }
}