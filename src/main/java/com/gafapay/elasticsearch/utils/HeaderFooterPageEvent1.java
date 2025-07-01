package com.gafapay.elasticsearch.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent1 extends PdfPageEventHelper {
   private String headerText;
   private String logoPath;
   private String companyName;
   private String startDate;
   private String endDate;
   BaseColor lightBlue = new BaseColor(135, 206, 250);
   private boolean isHeaderAdded = false;

   public HeaderFooterPageEvent1(String headerText, String logoPath, String companyName, String startDate, String endDate) {
      this.headerText = headerText;
      this.logoPath = logoPath;
      this.companyName = companyName;
      this.startDate = startDate;
      this.endDate = endDate;
   }

   public void onEndPage(PdfWriter writer, Document document) {
      try {
         Rectangle pageSize = document.getPageSize();
         Image logo = Image.getInstance(this.logoPath);
         logo.scaleAbsolute(70.0F, 70.0F);
         float logoWidth = logo.getScaledWidth();
         float logoHeight = logo.getScaledHeight();
         float logoX = pageSize.getRight() - logoWidth - 20.0F;
         float logoY = pageSize.getTop() - logoHeight - 20.0F;
         logo.setAbsolutePosition(logoX, logoY);
         document.add(logo);
         float titleX = logoX + 10.0F;
         float titleY = logoY - logoHeight + 57.0F;
         PdfContentByte canvas = writer.getDirectContent();
         ColumnText.showTextAligned(canvas, 0, new Phrase(this.companyName, FontFactory.getFont("Helvetica-Bold", 12.0F, this.lightBlue)), titleX, titleY, 0.0F);
         float startDateX = logoX + 70.0F;
         float startDateY = titleY - 10.0F;
         ColumnText.showTextAligned(canvas, 2, new Phrase("Start Date: " + this.startDate, FontFactory.getFont("Helvetica", 8.0F, this.lightBlue)), startDateX, startDateY, 0.0F);
         float endDateY = startDateY - 10.0F;
         ColumnText.showTextAligned(canvas, 2, new Phrase("End Date: " + this.endDate, FontFactory.getFont("Helvetica", 8.0F, this.lightBlue)), startDateX, endDateY, 0.0F);
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
