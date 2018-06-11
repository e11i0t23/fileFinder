import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;

public class fileFinder {

//  A simple program to search for files from terminal

    public static Integer filesSearched = 0;
    public static Integer occurances = 0;
    public final Scanner sc = new Scanner(System.in);
    public static Boolean choice_made = false;


    public fileFinder(){
        welcome();
        while (choice_made != true){
            System.out.print("[1]Search with full extension, \n[2]Search for like filename, \n[3]List with extension, \nChoice: ");
            String choice = sc.nextLine();
            System.out.print("Path (Use . for current dir and .. for containing dir): ");
            String path = sc.nextLine();
            if (choice.equals("1")){
                System.out.print("Full fileName: ");
                String fn = sc.nextLine();
                searchForFileFull(path, fn);
                choice_made = true;
            }else if (choice.equals("2")) {
                System.out.print("fileName: ");
                String fn = sc.nextLine();
                searchForFilePart(path, fn);
                choice_made = true;
            }else if (choice.equals("3")) {
                System.out.print("Estension (excluding .): ");
                String ext = sc.nextLine();
                listFilesWithExt(path, ext);
                choice_made = true;

            }else{
                System.out.print("Ivalid choice");
            }
        }


        //welcomemsg.msg();
    }

    public void listFilesWithExt(String path, String extension){
        try{

            Files.walk(Paths.get(path))
            .filter(Files::isRegularFile)
            .forEach(file -> {
                filesSearched++;
                String fn = "" + file;
                if (getFileExtension(fn).equals(extension)){
                    System.out.println(file);
                    occurances++;
                }
            });
            System.out.println(occurances + " occurances Found");
            System.out.println("In " + filesSearched + " Files searched");
        }
        catch(IOException e){
            System.out.println(e);
        };

    }

    public void searchForFileFull(String path, String fileName){
        try{
            Files.walk(Paths.get(path))
            .filter(Files::isRegularFile)
            .forEach(file -> {
                filesSearched++;
                String fn = "" + file;
                fn = fn.substring(fn.lastIndexOf("/")+1);
                if (fn.equals(fileName)){
                    System.out.println(fileName + " is located in " + file);
                    occurances++;
                }
            });
            System.out.println(occurances + " occurances Found");
            System.out.println("In " + filesSearched + " Files searched");
        }
        catch(IOException e){
            System.out.println(e);
        };
    }

    public void searchForFilePart(String path, String fileName){
        try{
            Files.walk(Paths.get(path))
            .filter(Files::isRegularFile)
            .forEach(file -> {
                String fn = "" + file;
                filesSearched++;
                if (fn.contains(fileName)){
                    System.out.println(fileName + " is located in " + file);
                    occurances++;
                }
            });
            System.out.println(occurances + " occurances Found");
            System.out.println("In " + filesSearched + " Files searched");
        }
        catch(IOException e){
            System.out.println(e);
        };
    }

    private static String getFileExtension(String fileName) {
        // String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0){
            // System.out.println(fileName.substring(fileName.lastIndexOf(".")+1));
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        else return "";
    }

    protected void welcome(){
        System.out.println();
        System.out.println("A simple program to search for files");
        System.out.println("==========Made by e11i0t23==========");
        System.out.println("======Follow the options below======");
    }


    public static void main(String[] args) {
      // pass off to main handler
      new fileFinder();
    }
}