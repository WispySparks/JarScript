package Script;

public class Main {
    public static void main(String[] args) {
        //Setup setup = new Setup();
        Jar jar = new Jar();
        String jarName = jar.getName(); 
        String projName = jarName.substring(0, jarName.length()-4);
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            System.out.println(e);
        }
        Organizer organizer = new Organizer(projName, jarName, jar.getPath());
        String path = organizer.getPath();
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            System.out.println(e);
        }
        new Runtime(jarName, projName, path);
        new Batch(projName, path);
    }
}