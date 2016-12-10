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
import java.util.List;
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
import com.mymeal.beans.CustomerBean;
import com.mymeal.beans.MenuItemBean;
import com.mymeal.beans.OfferBean;
import com.mymeal.beans.OrderBean;
import com.mymeal.dao.MealDAO;
import com.mymeal.exception.MealException;
import com.mymeal.requestvo.SubmitOrderRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealConstants.ResponseConstants;
import com.mymeal.util.MealHelper;

/**
 * @author basha
 *
 */
@Component("submitOrderProcessor")
public class SubmitOrderProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(SubmitOrderProcessor.class);

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

			SubmitOrderRequestVO submitOrderReqVO = (SubmitOrderRequestVO) jsonReq;

			OrderBean orderBean = createOrderBean(submitOrderReqVO);

			logger.info("Sending mail to admin");
			sendOrderMailToAdmin(orderBean);
			
			logger.info("Sending mail to customer");
			sendConfirmationMailToCust(orderBean);

			CustomerBean custBean = orderBean.getCustomer();

			logger.info(new StringBuilder("Created order id ").append(orderBean.getOrderId())
					.append(" for the customer ").append(custBean.getPhone()));

			long invoiceNum = mealDAO.saveOrder(orderBean);
			orderBean.setInvoiceNum(invoiceNum);
			
			if (invoiceNum > 0) {
				statusCode = MealConstants.STATUS_SUCCESS;
				responseMap.put(ResponseConstants.orderId.value(), orderBean.getOrderId());
				responseMap.put(ResponseConstants.total.value(), orderBean.getTotal());
			}
			
			//logger.info("Sending E-Bill to Admin");
			//sendEBillToAdmin(orderBean);
			
			logger.info("Submit Order Completed");
		} catch (Exception excep) {
			logger.error("Exception while submiting order", excep);
		} finally {
			responseVO.setStatusCode(statusCode);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}


	/**
	 * sendOrderMailToAdmin.
	 * 
	 * @param orderBean
	 */
	private void sendOrderMailToAdmin(final OrderBean orderBean) {

		new Thread(new Runnable() {
			public void run() {
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
				
				Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("food@freshybites.com","yammyTasty@321");
						}
					});

				try {

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("food@freshybites.com"));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse("shaikahmadbasha@gmail.com abhinandan.malhotra28@gmail.com sampy.saurav1@gmail.com vishalsuchak21@gmail.com", false));
					message.setSubject("Order received " + orderBean.getOrderId());
					
					StringBuilder sb = new StringBuilder();
					
					sb.append("Dear Admin,").append(
							"\n\n We have received following order ").append(
							"\n Location: 	" + orderBean.getLocationName()).append(
							"\n Date:     	" + orderBean.getDeliveryDate()).append(
							"\n Time:	  	" + orderBean.getDeliveryTime()).append(
							"\n Customer Name: 	" + orderBean.getCustomer().getName()).append(
							"\n Customer Mobile: " + orderBean.getCustomer().getPhone()).append(
							"\n Customer Email: " + orderBean.getCustomer().getEmail()).append(
							"\n Items:	\n");
							
					for(MenuItemBean menuItemBean : orderBean.getFoodItems()) {
						sb.append(menuItemBean.getItemName()).append("  -  qty: ").append(menuItemBean.getQty())
						.append("  - price: " + (menuItemBean.getQty() * menuItemBean.getPrice() ))
						.append("\n");
					}
					
					sb.append("\n Discount: -" +  orderBean.getDiscountAmount() );
					sb.append("\n Delivery Charges: " + orderBean.getSurcharge());
					sb.append("\n\nTotal amount:   "+ (orderBean.getTotal() - orderBean.getDiscountAmount() + orderBean.getSurcharge()) +"\n\nThank you,\nFreshy Bites Order Management System.");
					message.setText(sb.toString());
					
					Transport.send(message);

					System.out.println("sendOrderMailToAdmin is success");

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}


	/**
	 * sendOrderMailToAdmin.
	 * 
	 * @param orderBean
	 */
	private void sendConfirmationMailToCust(final OrderBean orderBean) {

		new Thread(new Runnable() {
			public void run() {
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

				Session session = Session.getInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("food@freshybites.com","yammyTasty@321");
						}
					});

				try {
					String custEmailId = orderBean.getCustomer().getEmail();
					String custName = orderBean.getCustomer().getName();

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("food@freshybites.com"));
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(custEmailId, false));
					message.setSubject("Order Confirmation - " + orderBean.getOrderId());
					
					StringBuilder sb = new StringBuilder();
					
					sb.append("Dear ").append(custName).append(
							",\n\n We have received following order ").append(
							"\n Location: 	" + MealHelper.removeOthers(orderBean.getLocationName())).append(
							"\n Date:     	" + orderBean.getDeliveryDate()).append(
							"\n Time:	  	" + orderBean.getDeliveryTime()).append(
							"\n Items:	\n");
							
					for(MenuItemBean menuItemBean : orderBean.getFoodItems()) {
						sb.append("    " + menuItemBean.getItemName()).append("  -  qty: ").append(menuItemBean.getQty())
						.append("  - price: " + (menuItemBean.getQty() * menuItemBean.getPrice() ))
						.append("\n");
					}
					sb.append("\n Discount: -" +  orderBean.getDiscountAmount() );
					sb.append("\n Delivery Charges: " + orderBean.getSurcharge());
					sb.append("\n\nTotal amount:   "+ (orderBean.getTotal() - orderBean.getDiscountAmount() + orderBean.getSurcharge()) +"\n\nThank you,\nFreshy Bites Order Management System.");
					message.setText(sb.toString());
					
					/*
					// Attach E-Bill
					ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		            writeEBillToOutStream(outputStream, orderBean);
		            byte[] bytes = outputStream.toByteArray();
		            
		            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
		            MimeBodyPart pdfBodyPart = new MimeBodyPart();
		            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
		            pdfBodyPart.setFileName("invoice.pdf");
		            
		            MimeMultipart mimeMultipart = new MimeMultipart();
		            mimeMultipart.addBodyPart(pdfBodyPart);
		            
		            message.setContent(mimeMultipart);
					*/
					Transport.send(message);

					System.out.println("sendConfirmationMailToCust is success");

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			
			}
		}).start();
		
	}

	private void sendEBillToAdmin(OrderBean orderBean) {

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

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("food@freshybites.com","yammyTasty@321");
				}
			});

		try {
			String custEmailId = orderBean.getCustomer().getEmail();
			String custName = orderBean.getCustomer().getName();

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("food@freshybites.com"));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("basha_99964@yahoo.co.in abhinandan.malhotra28@gmail.com sampy.saurav1@gmail.com vishalsuchak21@gmail.com", false));
			msg.setSubject("Order Delivered - " + orderBean.getOrderId());
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("Dear ").append(custName).append(
					",\n\n We have received following order ").append(
					"\n Location: 	" + orderBean.getLocationName()).append(
					"\n Date:     	" + orderBean.getDeliveryDate()).append(
					"\n Time:	  	" + orderBean.getDeliveryTime()).append(
					"\n Items:	\n");
					
			for(MenuItemBean menuItemBean : orderBean.getFoodItems()) {
				sb.append("    " + menuItemBean.getItemName()).append("  -  qty: ").append(menuItemBean.getQty()).append("\n");
			}

			sb.append("\n\nTotal amount:   "+ orderBean.getTotal() +"\n\nPlease find attached E-Bill.\n\nThank you,\nFreshy Bites Order Management System.");
			
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
			//throw new RuntimeException(e);
		}
	}
	
	/**
	 * createOrderBean.
	 * 
	 * @param submitOrderReqVO
	 * @return
	 * @throws MealException 
	 */
	private OrderBean createOrderBean(SubmitOrderRequestVO submitOrderReqVO) throws MealException {
		OrderBean orderBean = new OrderBean();
		orderBean.setCustomer(submitOrderReqVO.getCustomer());
		orderBean.setDeliveryDate(submitOrderReqVO.getDeliveryDate());
		orderBean.setDeliveryTime(submitOrderReqVO.getDeliveryTime());
		orderBean.setFoodItems(submitOrderReqVO.getFoodItems());
		orderBean.setLocationId(submitOrderReqVO.getLocationId());
		orderBean.setLocationName(submitOrderReqVO.getLocationName());
		orderBean.setOrderId(createOrderId(submitOrderReqVO));
		orderBean.setPaymentMode(submitOrderReqVO.getPaymentMode());
		
		int total = getFoodItemsTotal(submitOrderReqVO.getFoodItems());

		String offerCode = MealHelper.isEmpty(submitOrderReqVO.getOfferCode()) ? "" : submitOrderReqVO.getOfferCode();
		OfferBean offerBean = mealDAO.getOfferCodeDetails(offerCode.toLowerCase());
		int discountAmt = 0;
		if (MealConstants.OFFER_VALID.equals(MealHelper.offerApplicable(offerBean, submitOrderReqVO.getFoodItems(), total))) {
			discountAmt = (total * Integer.parseInt(offerBean.getOfferPercentage())) / 100;
			orderBean.setOfferPercentage(MealHelper.parseInt(offerBean.getOfferPercentage()));
		} else {
			orderBean.setOfferPercentage(0);
		}
		orderBean.setDiscountAmount(discountAmt);
		
		int deliveryCharges = 0;
		if(total - discountAmt < MealConstants.MIN_PURCHASE) {
			deliveryCharges = MealConstants.DELIVERY_CHARGES;
		}
		
		orderBean.setSurcharge(deliveryCharges);
		orderBean.setTotal(total);
		
		return orderBean;
	}

	/**
	 * getFoodItemsTotal.
	 * 
	 * @param foodItems
	 * @return
	 * @throws MealException
	 */
	private int getFoodItemsTotal(List<MenuItemBean> foodItems) throws MealException {
		List<MenuItemBean> menuItemsList = mealDAO.getMenuItems();

		int total = 0;
		for(MenuItemBean orderdedMenuItem : foodItems) {
			MenuItemBean menuItemBean = getMenuItemBeanById(menuItemsList, orderdedMenuItem.getItemId(), orderdedMenuItem.getItemName());
			orderdedMenuItem.setPrice(menuItemBean.getPrice());
			
			total = total + (orderdedMenuItem.getQty() * menuItemBean.getPrice());
		}
		
		return total;
	}

	/**
	 * getMenuItemBeanById.
	 * 
	 * @param menuItemsList
	 * @param itemId
	 * @param itemName
	 * @return
	 */
	private MenuItemBean getMenuItemBeanById(List<MenuItemBean> menuItemsList, String itemId, String itemName) {
		for(MenuItemBean menuItemBean : menuItemsList) {
			if(menuItemBean.getItemId().equalsIgnoreCase(itemId) && menuItemBean.getItemName().equalsIgnoreCase(itemName)) {
				return menuItemBean;
			}
		}
		
		return null;
	}

	/**
	 * createOrderId.
	 * 
	 * @param submitOrderReqVO
	 * @return
	 */
	private String createOrderId(SubmitOrderRequestVO submitOrderReqVO) {
		logger.info(MealConstants.ENTER);

		StringBuilder orderId = new StringBuilder();

		CustomerBean custBean = submitOrderReqVO.getCustomer();

		orderId.append(submitOrderReqVO.getDeliveryDate());
		orderId.append("-");
		orderId.append(submitOrderReqVO.getDeliveryTime());
		orderId.append("-");
		orderId.append(submitOrderReqVO.getLocationId());
		orderId.append("-");
		orderId.append(MealHelper.trailingChars(custBean.getPhone(), 4));
		orderId.append("-");
		orderId.append(Integer.toString(MealHelper.randInt(1000, 9999)));

		logger.info(MealConstants.EXIT + " OrderID created " + orderId.toString());
		return orderId.toString();
	}
	
	
	private void writeEBillToOutStream(OutputStream os, OrderBean orderBean) {
		try {
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, os); 

			// Inserting Image in PDF
			//Image image = Image.getInstance(new URL("http://service-freshybites.rhcloud.com/images/logo.png"));
			
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
			invoiceTable.addCell(orderBean.getInvoiceNum()+"");
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

			Paragraph place = new Paragraph("Hyderabad");
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

			
		} catch(Exception excep) {
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
		for(MenuItemBean menuItemBean : orderBean.getFoodItems()) {
			
			int qty = menuItemBean.getQty();
			int unitPrice = menuItemBean.getPrice();
			int totalbfdiscount = qty * unitPrice;
			int offerPercentage = orderBean.getOfferPercentage();
			int discount =  (totalbfdiscount*offerPercentage) / 100  ;
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

		PdfPCell cellTot = new PdfPCell(new Phrase("Total Payable"));
		cellTot.setColspan(6);
		cellTot.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cellTot.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cellTot);

		PdfPCell cellTotAmt = new PdfPCell(new Phrase("INR " + totPayable));
		cellTotAmt.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cellTotAmt);

		return table;
	}

} 