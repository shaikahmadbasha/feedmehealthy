/**
 * 
 */
package com.mymeal.processor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mymeal.beans.MenuItemBean;
import com.mymeal.beans.OrderBean;
import com.mymeal.dao.MealDAO;
import com.mymeal.requestvo.GenerateInvoiceRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealHelper;

/**
 * @author basha
 *
 */
@Component("generateInvoiceProcessor")
public class GenerateInvoiceProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(GenerateInvoiceProcessor.class);

	@Autowired
	private MealDAO mealDAO;

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonReq) {

		logger.info(MealConstants.ENTER);

		BaseResponseVO responseVO = new BaseResponseVO();

		Map<String, Object> responseMap = new HashMap<String, Object>();

		String statusCode = MealConstants.STATUS_ERROR;

		try {
			GenerateInvoiceRequestVO reqVO = (GenerateInvoiceRequestVO) jsonReq;

			String orderId = reqVO.getOrderId();

			OrderBean orderBean = null;

			if (!MealHelper.isEmpty(orderId)) {
				orderBean = mealDAO.getOrderDetails(reqVO.getOrderId());

				sendEBillToCustomer(orderBean);

				statusCode = MealConstants.STATUS_SUCCESS;
			}

		} catch (Exception excep) {
			logger.error("Exception while fetching order details", excep);
		} finally {
			responseVO.setStatusCode(statusCode);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

	private void sendEBillToCustomer(OrderBean orderBean) {

		Properties props = new Properties();
		/*
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		*/
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("food@freshybites.com", "yammyTasty@321");
			}
		});

		try {
			String custEmailId = orderBean.getCustomer().getEmail();
			String custName = orderBean.getCustomer().getName();

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("food@freshybites.com"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(custEmailId, false));
			msg.setSubject("Order Delivered - " + orderBean.getOrderId());

			StringBuilder sb = new StringBuilder();

			sb.append("Dear ").append(custName).append(",\n\n We have Delivered following order ")
					.append("\n Location: 	" + MealHelper.removeOthers(orderBean.getLocationName()))
					.append("\n Date:     	" + orderBean.getDeliveryDate())
					.append("\n Time:	  	" + orderBean.getDeliveryTime()).append("\n Items:	\n");

			for (MenuItemBean menuItemBean : orderBean.getFoodItems()) {
				sb.append("    " + menuItemBean.getItemName()).append("  -  qty: ").append(menuItemBean.getQty())
						.append("  - price: " + (menuItemBean.getQty() * menuItemBean.getPrice() ))
						.append("\n");
			}
			sb.append("\n Discount: -" +  orderBean.getDiscountAmount() );
			sb.append("\n Delivery Charges: " + orderBean.getSurcharge());
			sb.append("\n\nTotal amount:   " + (orderBean.getTotal())
					+ "\n\nPlease find attached E-Bill.\n\nThank you,\nFreshy Bites Order Management System.");

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(sb.toString());

			// Attach E-Bill
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			writeEBillToOutStream(outputStream, orderBean);
			byte[] bytes = outputStream.toByteArray();

			DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
			MimeBodyPart pdfBodyPart = new MimeBodyPart();
			pdfBodyPart.setDataHandler(new DataHandler(dataSource));
			pdfBodyPart.setFileName("invoice.pdf");

			MimeMultipart mimeMultipart = new MimeMultipart();
			mimeMultipart.addBodyPart(messageBodyPart);
			mimeMultipart.addBodyPart(pdfBodyPart);

			msg.setContent(mimeMultipart);
			Transport.send(msg);

			System.out.println("Done");

		} catch (MessagingException e) {
			// throw new RuntimeException(e);
		}
	}

	private void writeEBillToOutStream(OutputStream os, OrderBean orderBean) {
		try {
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, os);

			// Inserting Image in PDF
			// Image image = Image.getInstance(new
			// URL("http://service-freshybites.rhcloud.com/images/logo.png"));

			logger.info("logo reading started");
			InputStream is = this.getClass().getResourceAsStream("/logo_min.png");
			byte[] imgBytes = new byte[is.available()];
			is.read(imgBytes);
			logger.info("logo reading ended");

			Image image = Image.getInstance(imgBytes);
			image.scaleAbsolute(120f, 60f);// image width,height
			image.setAlignment(100);

			// Now Insert Every Thing Into PDF Document
			document.open();// PDF document opened........

			Font fontbold = FontFactory.getFont("Times-Roman", 12, Font.BOLD);
			Paragraph invoce = new Paragraph("INVOICE", fontbold);
			invoce.setAlignment(Element.ALIGN_CENTER);

			TimeZone tz = TimeZone.getTimeZone("IST");
			Calendar today = Calendar.getInstance();
			today.setTimeZone(tz);
			SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy");
			today.setTimeZone(tz);

			PdfPTable invoiceTable = new PdfPTable(new float[] { 2, 1 });
			invoiceTable.setWidthPercentage(40f);
			invoiceTable.addCell("Invoice Date");
			invoiceTable.addCell(sd.format(today.getTime()));
			invoiceTable.addCell("Invoice Number");
			invoiceTable.addCell(orderBean.getInvoiceNum() + "");
			invoiceTable.addCell("TIN");
			invoiceTable.addCell("36103008974");
			invoiceTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			document.add(invoce);
			document.add(image);
			document.add(Chunk.NEWLINE);
			document.add(invoiceTable);

			document.add(Chunk.NEWLINE); // Something like in HTML :-)
			document.add(Chunk.NEWLINE);
			Paragraph site = new Paragraph("www.freshybites.com");
			site.setAlignment(Element.ALIGN_LEFT);

			Paragraph phone = new Paragraph("Phone: +91-8886412828");
			phone.setAlignment(Element.ALIGN_LEFT);

			Paragraph to = new Paragraph("To,");
			to.setAlignment(Element.ALIGN_LEFT);

			Paragraph name = new Paragraph(orderBean.getCustomer().getName() + ",");
			name.setAlignment(Element.ALIGN_LEFT);

			Paragraph place = new Paragraph(MealHelper.removeOthers(orderBean.getLocationName()));
			place.setAlignment(Element.ALIGN_LEFT);

			document.add(site);
			document.add(phone);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			document.add(to);
			document.add(name);
			document.add(place);

			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);

			document.add(getOrderSummEBillTable(orderBean));

			document.add(Chunk.NEWLINE); // Something like in HTML :-)

			document.close();

		} catch (Exception excep) {
			logger.error("Exception while creating e-bill", excep);
		}
	}

	private Element getOrderSummEBillTable(OrderBean orderBean) {
		// Create a table with 7 columns
		PdfPTable table = new PdfPTable(new float[] { 1, 7, 2, 2, 2, 2, 3 });
		table.setWidthPercentage(100f);
		table.getDefaultCell().setUseAscender(true);
		table.getDefaultCell().setUseDescender(true);

		// Add the second header row twice
		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell("S.No");
		table.addCell("Description");
		table.addCell("Quantity");
		table.addCell("Unit Price (INR)");
		table.addCell("Total");
		table.addCell("Discount");
		table.addCell("Payable (INR)");
		table.getDefaultCell().setBackgroundColor(null);
		// There are three special rows
		// table.setHeaderRows(2);
		// One of them is a footer
		// table.setFooterRows(1);
		// Now let's loop over the screenings

		int sno = 1;
		int totPayable = 0;
		for (MenuItemBean menuItemBean : orderBean.getFoodItems()) {

			int qty = menuItemBean.getQty();
			int unitPrice = menuItemBean.getPrice();
			int totalbfdiscount = qty * unitPrice;
			int discount = menuItemBean.getDiscount();
			int totalaftrdiscount = totalbfdiscount - discount;
			totPayable = totPayable + totalaftrdiscount;

			table.addCell(String.valueOf(sno++));
			table.addCell(menuItemBean.getItemName());
			table.addCell(String.valueOf(qty));
			table.addCell(String.valueOf(unitPrice));
			table.addCell(String.valueOf(totalbfdiscount));
			table.addCell("-" + discount);
			table.addCell(String.valueOf(totalaftrdiscount));

		}

		PdfPCell cellDelvery = new PdfPCell(new Phrase("Delivery Charges"));
		cellDelvery.setColspan(6);
		cellDelvery.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellDelvery.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cellDelvery);

		PdfPCell cellDelveryAmt = new PdfPCell(new Phrase("INR " + orderBean.getSurcharge()));
		cellDelveryAmt.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cellDelveryAmt);

		
		PdfPCell cellTot = new PdfPCell(new Phrase("Total Payable"));
		cellTot.setColspan(6);
		cellTot.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellTot.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cellTot);

		PdfPCell cellTotAmt = new PdfPCell(new Phrase("INR " + (totPayable + orderBean.getSurcharge())));
		cellTotAmt.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cellTotAmt);

		return table;
	}


}
