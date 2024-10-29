package com.example.PdfGeneration.service;

import com.example.PdfGeneration.model.ItemInfo;
import com.example.PdfGeneration.model.PdfInfo;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfService {

    public String generatePdf(PdfInfo pdfInfo){
        String path = "Sample-file"+".pdf";

        try {
            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            Document document = new Document(pdfDocument);

            float colWidth = 250f;
            Style style = new Style();
            style.setMarginLeft(50);
            style.setMarginBottom(50);
            style.setMarginTop(50);

            style.setTextAlignment(TextAlignment.CENTER);
            //style.setBold();
            float [] collArray = {colWidth,colWidth};
            Table table = new Table(collArray);
            Cell sellerCell = new Cell();
            sellerCell.addStyle(style);
            sellerCell.add("Seller");
            sellerCell.add(pdfInfo.getSeller());
            sellerCell.add("GSTIN : "+pdfInfo.getSellerGstin());
            sellerCell.add(pdfInfo.getSellerAddress());
            table.addCell(sellerCell);

            Cell buyerCell = new Cell();
            buyerCell.addStyle(style);
            buyerCell.add("Buyer");
            buyerCell.add(pdfInfo.getBuyer());
            buyerCell.add("GSTIN : "+pdfInfo.getBuyerGstin());
            buyerCell.add(pdfInfo.getBuyerAddress());
            table.addCell(buyerCell);

            document.add(table);

            float [] itemsArray = {125f,125f,125f,125f};
            int noOfItems = pdfInfo.getItems().size();
            Table iteamTable = new Table(itemsArray);

            iteamTable.addCell(new Cell().add("Items").addStyle(style));
            iteamTable.addCell(new Cell().add("Quantity").addStyle(style));
            iteamTable.addCell(new Cell().add("Rate").addStyle(style));
            iteamTable.addCell(new Cell().add("Amount").addStyle(style));

            PdfInfo pdfInfo1 = convertPdfInfo(pdfInfo);

            for(int i=0;i<noOfItems;i++){
                iteamTable.addCell(new Cell().add(pdfInfo1.getItems().get(i).getName()).addStyle(style));
                iteamTable.addCell(new Cell().add(String.valueOf(pdfInfo1.getItems().get(i).getQuantity())).addStyle(style));
                iteamTable.addCell(new Cell().add(String.valueOf(pdfInfo1.getItems().get(i).getRate())).addStyle(style));
                double amount = pdfInfo.getItems().get(i).getQuantity() * pdfInfo.getItems().get(i).getRate();
                BigDecimal roundedAmount = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
                double finalAmount = roundedAmount.doubleValue();
                iteamTable.addCell(new Cell().add(String.valueOf(finalAmount)).addStyle(style));

            }
            document.add(iteamTable);

            document.close();

        }catch (FileNotFoundException exception){
            exception.getMessage();
        }
        return "Pdf Generated...";
    }

    public static PdfInfo convertPdfInfo(PdfInfo pdfInfo){
        List<ItemInfo> itemInfoList = new ArrayList<>();
        for(int i=0;i<pdfInfo.getItems().size();i++){
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setQuantity(pdfInfo.getItems().get(i).getQuantity());
            itemInfo.setRate(pdfInfo.getItems().get(i).getRate());
            itemInfo.setName(pdfInfo.getItems().get(i).getName());
            itemInfo.setAmount(pdfInfo.getItems().get(i).getQuantity() * pdfInfo.getItems().get(i).getAmount());
            itemInfoList.add(itemInfo);
        }
        pdfInfo.setItems(itemInfoList);

        return  pdfInfo;
    }


}
