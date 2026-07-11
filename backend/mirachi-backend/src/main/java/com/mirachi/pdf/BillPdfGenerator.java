package com.mirachi.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.mirachi.entity.Bill;

@Component
public class BillPdfGenerator {

    public ByteArrayInputStream
    generateBillPdf(Bill bill) {

        try {

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            Document document =
                    new Document();

            PdfWriter.getInstance(
                    document,
                    out);

            document.open();

            document.add(
                    new Paragraph(
                            "MIRACHI KANDAP KENDRA"));

            document.add(
                    new Paragraph(
                            "--------------------------------"));

            document.add(
                    new Paragraph(
                            "Bill Number : "
                                    + bill.getBillNumber()));

            document.add(
                    new Paragraph(
                            "Customer : "
                                    + bill.getCustomer()
                                            .getCustomerName()));

            document.add(
                    new Paragraph(
                            "Date : "
                                    + bill.getBillDate()));

            document.add(
                    new Paragraph(" "));

            document.add(
                    new Paragraph(
                            "Items"));

            document.add(
                    new Paragraph(
                            "--------------------------------"));

            bill.getBillItems()
                    .forEach(item -> {

                        try {

                            document.add(
                                    new Paragraph(
                                            item.getItemName()
                                                    + " | "
                                                    + item.getWeightInKg()
                                                    + " KG | ₹"
                                                    + item.getRatePerKg()
                                                    + " | ₹"
                                                    + item.getAmount()));

                        } catch (Exception e) {

                            throw new RuntimeException(
                                    e);
                        }
                    });

            document.add(
                    new Paragraph(
                            "--------------------------------"));

            document.add(
                    new Paragraph(
                            "Total Amount : ₹"
                                    + bill.getTotalAmount()));

            document.add(
                    new Paragraph(
                            "Payment Status : "
                                    + bill.getPaymentStatus()));

            document.close();

            return new ByteArrayInputStream(
                    out.toByteArray());

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error generating PDF",
                    e);
        }
    }
}