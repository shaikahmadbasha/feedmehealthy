/**
 * 
 */
package com.mymeal.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mymeal.beans.CustomerBean;
import com.mymeal.dao.MealDAO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;
import com.mymeal.util.MealConstants.ResponseConstants;
import com.mymeal.util.MealHelper;

/**
 * @author basha
 *
 */
@Component("forgotPasswordProcessor")
public class ForgotPasswordProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(ForgotPasswordProcessor.class);

	@Autowired
	private MealDAO mealDAO;

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response, Object jsonReq) {
		logger.info(MealConstants.ENTER);

		BaseResponseVO responseVO = new BaseResponseVO();

		Map<String, Object> responseMap = new HashMap<String, Object>();

		String statusCode = MealConstants.STATUS_ERROR;

		String fgStatus = MealConstants.NOT_FOUND;

		try {

			CustomerBean customerBean = mealDAO.getCustomerDetails((String) jsonReq);

			if (null != customerBean && !MealHelper.isEmpty(customerBean.getEmail())) {
				sendFGPasswordMailToCust(customerBean);
				fgStatus = MealConstants.EMAIL_SENT;
			}
			responseMap.put(ResponseConstants.fgStatus.value(), fgStatus);

			statusCode = MealConstants.STATUS_SUCCESS;

		} catch (Exception excep) {
			logger.error("Exception while fetching customer details", excep);
		} finally {
			responseVO.setStatusCode(statusCode);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

	/**
	 * sendFGPasswordMailToCust.
	 * 
	 * @param customerBean
	 */
	private void sendFGPasswordMailToCust(final CustomerBean customerBean) {

		new Thread(new Runnable() {
			public void run() {
				Properties props = new Properties();
				/*
				 * props.put("mail.smtp.host", "smtp.gmail.com");
				 * props.put("mail.smtp.socketFactory.port", "465");
				 * props.put("mail.smtp.socketFactory.class",
				 * "javax.net.ssl.SSLSocketFactory");
				 * props.put("mail.smtp.auth", "true");
				 * props.put("mail.smtp.port", "465");
				 */

				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.office365.com");
				props.put("mail.smtp.port", "587");

				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("food@freshybites.com", "yammyTasty@321");
					}
				});

				try {
					String custEmailId = customerBean.getEmail();
					String custName = customerBean.getName();
					String password = customerBean.getPassword();

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("food@freshybites.com"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(custEmailId, false));
					message.setSubject("Your Password with Feshy Bites");

					StringBuilder sb = new StringBuilder();

					sb.append("Dear ").append(custName).append(",\n\n Your Password with Freshy Bites is ")
							.append(password);

					message.setText(sb.toString());

					Transport.send(message);

					System.out.println("Seding Password is success for customer " + custName);

				} catch (MessagingException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	@Override
	public Object processRequest(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
