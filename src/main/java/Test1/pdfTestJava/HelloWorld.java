package Test1.pdfTestJava;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;


import java.io.*;
import java.lang.*;

public class HelloWorld {
    String stringPdfContent = new String();
    protected void pdfCreator(String fileName){


        try{
            stringPdfContent = "Let's do this now.\nThis is my second file and for every iteration," +
                    "a new filename is automatically generated!";
            PdfWriter pdfWriter = new PdfWriter(fileName);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            document.add(new Paragraph(stringPdfContent));
            document.close();
            System.out.println("Doc created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed");
        }

    }

    protected String autoGenerateFileName(){
        //start auto-naming files to avoid conflicts
        //get a list of all files
        File folder = new File("/home/dev-k/Documents/allPdfTests");
        File[] listOfFiles = folder.listFiles();

        //print all files:
        /*for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }*/
        String lastFile = listOfFiles[listOfFiles.length - 1] + "";
        char[] charLastFile = lastFile.toCharArray();
        int currIndex = Integer.valueOf(Character.toString(charLastFile[charLastFile.length - 5]));
        charLastFile[charLastFile.length - 5] = Character.forDigit((currIndex + 1), 10);
        String fileNameString = new String(charLastFile);
        System.out.println("New file name: " + fileNameString);
        return fileNameString;
    }

    public static void main(String args[]){
        System.out.println("Hello world");
        System.out.println("Flashing back to New York City\nI was dumb but you undid me");
        HelloWorld helloWorld = new HelloWorld();
        System.out.println("Attempting to create your document");
        //print all files:
        //helloWorld.autoGenerateFileName();
        helloWorld.pdfCreator(helloWorld.autoGenerateFileName());
    }

}


