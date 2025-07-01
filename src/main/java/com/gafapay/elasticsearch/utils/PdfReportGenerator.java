package com.gafapay.elasticsearch.utils;

import com.gafapay.elasticsearch.api.sendreport.model.request.ReportData;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class PdfReportGenerator {
   private static final BaseColor HEADER_COLOR = new BaseColor(10, 123, 137);
   private static final BaseColor TABLE_BORDER_COLOR = new BaseColor(135, 135, 135);
   private static final Font.FontFamily FONT_FAMILY;
   private static final float TITLE_FONT_SIZE = 18.0F;
   private static final float COMPANY_NAME_FONT_SIZE = 15.0F;
   private static final float DATE_FONT_SIZE = 9.0F;
   private static final float NORMAL_FONT_SIZE = 12.0F;

   public PdfReportGenerator() {
   }

   public static void generatePDF(ReportData reportData, ByteArrayOutputStream pdfOutputStream) throws DocumentException, MalformedURLException, IOException {
      int numberOfColumn = reportData.getHeaders().size();
      Rectangle pageSize = numberOfColumn > 9 ? (numberOfColumn > 13 ? PageSize.A1 : PageSize.A2) : PageSize.A3;
      Document document = new Document(pageSize);
      document.setMargins(30.0F, 30.0F, 10.0F, 10.0F);
      PdfWriter.getInstance(document, pdfOutputStream);
      document.open();
      document.add(getLogoImg(reportData.getCompanyLogoUrl()));
      Font companyFont = new Font(FONT_FAMILY, 15.0F, 0, HEADER_COLOR);
      document.add(getParagraph(reportData.getCompanyName(), companyFont, 2));
      Font titleFont = new Font(FONT_FAMILY, 18.0F, 0, HEADER_COLOR);
      document.add(getParagraph(reportData.getTitleText(), titleFont, 1));
      Font dateFont = new Font(FONT_FAMILY, 9.0F, 0, HEADER_COLOR);
      document.add(getParagraph("Start Date : " + reportData.getStartDate(), dateFont, 2));
      document.add(getParagraph("End Date : " + reportData.getEndDate(), dateFont, 2));
      document.add(getParagraph("\n", new Font(FONT_FAMILY, 8.0F), 0));
      document.add(getTable(reportData));
      document.close();
   }

   private static void addTableHeader(List<String> headers, PdfPTable table) {
      Font font = new Font(FONT_FAMILY, 12.0F);
      PdfPCell header = new PdfPCell();
      header.setPhrase(new Phrase("No ", font));
      header.setBorderColor(TABLE_BORDER_COLOR);
      table.addCell(header);
      headers.forEach((columnTitle) -> {
         header.setPhrase(new Phrase(columnTitle, font));
         table.addCell(header);
      });
   }

   private static void addRows(PdfPTable table, List<String> row, int count) {
      table.addCell(getDesignedCell(Integer.toString(count)));

      for(String cellData : row) {
         table.addCell(getDesignedCell(cellData));
      }

   }

   private static PdfPCell getDesignedCell(String data) {
      Font font = new Font(FONT_FAMILY, 12.0F);
      PdfPCell cell = new PdfPCell(new Phrase(data, font));
      cell.setBorderColor(TABLE_BORDER_COLOR);
      return cell;
   }

   private static float[] calculateColumnWidths(ReportData reportData) {
      float[] columnWidths = new float[reportData.getHeaders().size() + 1];
      columnWidths[0] = 4.0F;

      for(int i = 1; i < columnWidths.length; ++i) {
         columnWidths[i] = (float)((String)reportData.getHeaders().get(i - 1)).length();
      }

      int count = 1;

      for(List<String> row : reportData.getData()) {
         if (count > 50) {
            break;
         }

         for(int i = 1; i < columnWidths.length; ++i) {
            columnWidths[i] = Math.max(columnWidths[i], (float)((String)row.get(i - 1)).length());
         }

         ++count;
      }

      float totalWidth = 0.0F;

      for(int i = 0; i < columnWidths.length; ++i) {
         totalWidth += columnWidths[i];
      }

      for(int i = 0; i < columnWidths.length; ++i) {
         columnWidths[i] = columnWidths[i] * 100.0F / totalWidth;
      }

      return columnWidths;
   }

   private static PdfPTable getTable(ReportData reportData) throws DocumentException {
      PdfPTable table = new PdfPTable(reportData.getHeaders().size() + 1);
      table.setWidthPercentage(100.0F);
      float[] columnWidths = calculateColumnWidths(reportData);
      table.setWidths(columnWidths);
      addTableHeader(reportData.getHeaders(), table);
      int count = 1;

      for(List<String> row : reportData.getData()) {
         addRows(table, row, count);
         ++count;
      }

      return table;
   }

   private static Paragraph getParagraph(String data, Font font, int alignData) {
      Paragraph paragraph = new Paragraph(data, font);
      paragraph.setAlignment(alignData);
      return paragraph;
   }

   private static Image getLogoImg(String logoUrl) throws BadElementException, MalformedURLException, IOException {
      Image logoImage = Image.getInstance(logoUrl);
      logoImage.setAlignment(2);
      logoImage.scaleToFit(100.0F, 100.0F);
      return logoImage;
   }

   static {
      FONT_FAMILY = FontFamily.HELVETICA;
   }
}
