package com.example.tap2024;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PDFTools {

    public static void generarTicketDeCompra(String datos){
        try {
            String cabezeraDatos = String.format("Taqueria \"El Judio Antisemita\"\n\nRecibo de compra\n\n","","");
            //pdfwriter
            String destino = "./ticket.pdf";
            PdfWriter writer = new PdfWriter(destino);
            //pdfdoc
            PdfDocument pdfDoc = new PdfDocument(writer);
            //add a page
            pdfDoc.addNewPage();
            // Creating a Document
            Document document = new Document(pdfDoc);
            //Creating the content
            Paragraph content = new Paragraph(cabezeraDatos + datos);
            document.add(content);
            // Closing the document
            document.close();
            System.out.println("PDF Created");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void generarGraficas(){

    }

}
