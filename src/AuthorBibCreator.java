import java.io.*;
import java.util.Scanner;

/**
 * Created by jangh on 2019-03-12.
 *
 * JANGHYUK BOO 40005573
 * ASSIGNMENT 3
 */

public class AuthorBibCreator {
    public static void processBibFiles(Scanner s, PrintWriter p, String author, String type){
        int count = 0;
        while(s.hasNextLine()){
            String next = s.nextLine();
            if (next.contains(author)){
                String name = next.substring(next.indexOf("{")+1,next.indexOf("}"));
                String journal = s.nextLine();
                journal = journal.substring(journal.indexOf("{")+1,journal.indexOf("}"));
                String title = s.nextLine();
                title =title.substring(title.indexOf("{")+1,title.indexOf("}"));
                String year = s.nextLine();
                year = year.substring(year.indexOf("{")+1,year.indexOf("}"));
                String volume = s.nextLine();
                volume =  volume.substring(volume.indexOf("{")+1,volume.indexOf("}"));
                String number = s.nextLine();
                number =  number.substring(number.indexOf("{")+1,number.indexOf("}"));
                String pages = s.nextLine();
                pages =  pages.substring(pages.indexOf("{")+1,pages.indexOf("}"));
                String keywords  = s.nextLine();
                keywords = keywords.substring(keywords.indexOf("{")+1,keywords.indexOf("}"));
                String dois = s.nextLine();
                dois = dois.substring(dois.indexOf("{")+1,dois.indexOf("}"));
                String issn = s.nextLine();
                issn =  issn.substring(issn.indexOf("{")+1,issn.indexOf("}"));
                String month = s.nextLine();
                month = month.substring(month.indexOf("{")+1,month.indexOf("}"));
                if(type.equals("ieee")) {
                    p.println(name + ". " + "\"" + title + "\", " + journal + "vol." + volume + " no." + number + ", p. " + pages + " " + month + " " + year);
                    p.println();
                }
                if(type.equals("acm")) {
                    p.println("["+count+"]"+name + ". " + year +" "+ title + ". " + journal + " " + volume + ", " + number + "("+year+"), " + pages + ". DOI:https://" + dois);
                    p.println();
                    count++;
                }
                if(type.equals("nj")) {
                    p.println(name + ". " + ""+title +", " + journal + " "+ volume + pages + " " + " ("+ year +")");
                    p.println();
                }
                }
        }
        p.close();
        s.close();
    }

    public static void fileExistcheck() {
        for (int i = 1; i <= 10; i++) {
            String a = "C:\\Users\\JA_BOO\\IdeaProjects\\article\\Toread"+"\\"+ "Latex" + i + ".bib";
            File f = new File(a);
            if (!f.exists()) {
                System.out.println("could not open input file " + a + "for reading. please check if file exists. program will terminate after closing any opned file");
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) {

        String author;
        PrintWriter pw = null;
        Scanner kb = new Scanner(System.in);
        Scanner sc = null;
        BufferedReader br = null;
        System.out.println("Welcome to bibCreator," +
                "Please enter the author name you are targeting: ");

        author = kb.next();
        String nameieee = author + "-IEEE.JSON";
        String nameacm = author + "-ACM.JSON";
        String namenj = author + "-NJ.JSON";
        String nameieeeBU = author + "-IEEE-BU.JSON";
        String nameacmBU = author + "-ACM-BU.JSON";
        String nameNjBU = author + "-NJ-BU.JSON";

        try
        {
            File folder = new File("C:\\Users\\JA_BOO\\IdeaProjects\\article\\Toread");
            File[] listOfFiles = folder.listFiles();


            pw = new PrintWriter(new FileOutputStream("save"));
           fileExistcheck();

            for(int i = 1; i<listOfFiles.length; i++) {
                String a =  listOfFiles[i].getPath(); //"C:\\Users\\jangh\\IdeaProjects\\article\\Toread"+"\\"+"Latex" + i + ".bib";
                File f = new File(a);
                sc = new Scanner(new FileInputStream(listOfFiles[i]));
                while(sc.hasNextLine()){
                    pw.println(sc.nextLine());
                }
            }
            File folder2 = new File("C:\\Users\\JA_BOO\\IdeaProjects\\article");
            File[] listOfFiles2 = folder2.listFiles();
            System.out.println("The program will make the file with " + author + " if it is not found it will be deleted");
            System.out.println("Hope you have enjoyed using this program");
               for (File file : listOfFiles2) {

                   if (file.getName().equals(nameieeeBU)||file.getName().equals(nameacmBU)||file.getName().equals(nameNjBU)){
                       throw new Exception();
                   }
                   if (file.getName().equals(nameieee)||file.getName().equals(nameacm)||file.getName().equals(namenj)) {
                       throw new FileExistException();
                  }
                  else{
                       sc = new Scanner(new FileInputStream("save"));
                       pw = new PrintWriter(new FileOutputStream(nameieee));
                       processBibFiles(sc, pw, author, "ieee");
                       sc = new Scanner(new FileInputStream("save"));
                       pw = new PrintWriter(new FileOutputStream(nameacm));
                       processBibFiles(sc, pw, author, "acm");
                       sc = new Scanner(new FileInputStream("save"));
                       pw = new PrintWriter(new FileOutputStream(namenj));
                       processBibFiles(sc, pw, author,"nj");

  }
               }

            File emptyfile = new File(nameieee);
            File emptyfile2 = new File(nameacm);
            File emptyfile3 = new File(namenj);
            if(emptyfile.length() == 0){
                emptyfile.delete();
                emptyfile2.delete();
                emptyfile3.delete();
                System.out.println("No records were found for author with name :" + author);
                System.out.println("No file have been created ");
            }
               }

        catch(FileNotFoundException e)
        {
            System.out.println("Problem opening files. Cannot proceed to copy.");
            System.out.println("Program will terminate.");
            System.exit(0);
        }
        catch(FileExistException e)
        {   File folder = new File("C:\\Users\\JA_BOO\\IdeaProjects\\article");
            File[] listOfFiles = folder.listFiles();
            sc.close();
            pw.close();
            try {

                sc = new Scanner(new FileInputStream("save"));
                pw = new PrintWriter(new FileOutputStream(nameieeeBU));
                processBibFiles(sc, pw, author, "ieee");
                sc = new Scanner(new FileInputStream("save"));
                pw = new PrintWriter(new FileOutputStream(nameacmBU));
                processBibFiles(sc, pw, author, "acm");
                sc = new Scanner(new FileInputStream("save"));
                pw = new PrintWriter(new FileOutputStream(nameNjBU));
                processBibFiles(sc, pw, author, "nj");
            }
            catch(FileNotFoundException ee)
            {
                System.out.println("Problem opening files. Cannot proceed to copy.");
                System.out.println("Program will terminate.");
                System.exit(0);
            }


        }
        catch(Exception e){
            File folder = new File("C:\\Users\\JA_BOO\\IdeaProjects\\article");
            File[] listOfFiles = folder.listFiles();


            for (File file : listOfFiles) {
                File deleted = new File(nameieeeBU);
                deleted.delete();
                deleted = new File(nameNjBU);
                deleted.delete();
                deleted = new File(nameacmBU);
                deleted.delete();


            }
            for (File file : listOfFiles) {

                if (file.getName().equals(nameieee)) {
                    File newIee = new File(nameieeeBU);
                    file.renameTo(newIee);}
                if (file.getName().equals(nameacm)) {
                    File newAcm = new File(nameacmBU);
                    file.renameTo(newAcm);
                }if(file.getName().equals(namenj)) {
                    File newNj = new File(nameNjBU);
                    file.renameTo(newNj);
                }
        }



        }
    }
}
