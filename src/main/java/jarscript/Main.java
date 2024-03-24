package jarscript;

public class Main {

    public static void main(String[] args) { 
        Organizer organizer = new Organizer(); // create folder that everything goes inside
        String folderPath = organizer.getPath(); // get the path of the folder
        String folderName = organizer.getName(); // get name of project
        new Jar(folderPath, folderName); // create jar file
        new Runtime(folderName + ".jar", folderName, folderPath); // create runtime for jar file
        new Batch(folderName, folderPath); // create batch file to run jar file with runtime
        new Shell(folderName, folderPath); // create shell file for other operating systems
    }

}