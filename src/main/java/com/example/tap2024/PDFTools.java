package com.example.tap2024;

import com.itextpdf.*;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;

public class PDFTools {

    public void generarTicketDeCompra(){
        try {
            //pdfwriter
            String destino = "./ticket.pdf";
            PdfWriter writer = new PdfWriter(destino);
            //pdfdoc
            PdfDocument pdfDoc = new PdfDocument(writer);
            //add a page
            pdfDoc.addNewPage();
            // Creating a Document
            Document document = new Document(pdfDoc);
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
