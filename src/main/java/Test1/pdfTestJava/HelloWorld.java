package Test1.pdfTestJava;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.DashedLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.ElementPropertyContainer;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;


import org.apache.log4j.Layout;

import java.io.*;
import java.lang.*;

import javax.swing.text.Element;
import javax.swing.text.StyleConstants;

import static com.itextpdf.layout.property.HorizontalAlignment.CENTER;

public class HelloWorld {
    String stringPdfContent = new String();
    protected void pdfCreatorZero(String fileName){

        try{
            stringPdfContent = "Let's do this now.\nThis is my next file and for every iteration," +
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

    protected void pdfCreatorList(String fileName){
        stringPdfContent = "Let's do this now.\nThis is my next file and for every iteration," +
                "a new filename is automatically generated!";

        try{
            PdfFont fontRegular = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN),
                    fontBold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            List list = new List()
                    .setSymbolIndent(12)
                    .setListSymbol("\u2022")
                    .setFont(fontBold);

            PdfWriter pdfWriter = new PdfWriter(fileName);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            document.add(new Paragraph(stringPdfContent));
            document.add(new Paragraph("Here comes the list").setFont(fontRegular));
            //adding list items
            list.add(new ListItem("So, do you like it?"))
                .add(new ListItem("iText is really super-easy"));
            document.add(list);
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

    protected void pdfCreateRealReceipt(String fileName){
        stringPdfContent = "Let's do this now.\nThis is my next file and for every iteration, " +
                "a new filename is automatically generated!";

        String title1 = "Here comes the name";
        String title2 = "Here comes the address";
        String title3 = "Reg. No.: ";
        String title4 = "more information";
        String title5 = "\n\n";
        String title6 = "Receipt";
        try{
            PdfFont fontRegular = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN),
                    fontBold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            PdfWriter pdfWriter = new PdfWriter(fileName);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            PageSize pageSize = PageSize.A5.clone();
            pdfDocument.addNewPage(pageSize);
            Document document = new Document(pdfDocument);


            document.add(new Paragraph()
            .add(new Text(title1+"\n").setFont(fontBold))
            .add(new Text(title2+"\n").setFont(fontRegular))
            .add(new Text(title3).setFont(fontBold))
            .add(new Text(title4).setFont(fontRegular))
            .setTextAlignment(TextAlignment.CENTER)
            .setBorder(new SolidBorder(1))
            );
            document.add(new LineSeparator( new DashedLine()));
            document.add(new Paragraph("RECEIPT").setFont(fontBold).setTextAlignment(TextAlignment.CENTER).setUnderline());
            document.add(new AreaBreak(pageSize));
            //pdfDocument.addNewPage(pageSize);
            document.add(new Paragraph()
                    .add(new Text(title1+"\n").setFont(fontBold))
                    .add(new Text(title2+"\n").setFont(fontRegular))
                    .add(new Text(title3).setFont(fontBold))
                    .add(new Text(title4).setFont(fontRegular))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBorder(new SolidBorder(1))
            );
            document.add(new LineSeparator( new DashedLine()));
            document.add(new Paragraph("RECEIPT").setFont(fontBold).setTextAlignment(TextAlignment.CENTER).setUnderline());

            document.close();
            System.out.println("Doc created");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Failed");
        }
    }

    public static void main(String args[]){
        HelloWorld helloWorld = new HelloWorld();
        System.out.println("Attempting to create your document");
        String thisInstanceFileName = helloWorld.autoGenerateFileName();
        //helloWorld.pdfCreatorZero(thisInstanceFileName);
        //helloWorld.pdfCreatorList(helloWorld.autoGenerateFileName());
        helloWorld.pdfCreateRealReceipt(thisInstanceFileName);

    }

}


