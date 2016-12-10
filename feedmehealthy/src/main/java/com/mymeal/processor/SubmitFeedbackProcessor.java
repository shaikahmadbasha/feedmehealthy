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
import org.springframework.stereotype.Component;

import com.mymeal.requestvo.SubmitFeedbackRequestVO;
import com.mymeal.responsevo.BaseResponseVO;
import com.mymeal.util.MealConstants;

/**
 * @author basha
 *
 */
@Component("submitFeedbackProcessor")
public class SubmitFeedbackProcessor extends BaseProcessor {

	private static Logger logger = Logger.getLogger(SubmitFeedbackProcessor.class);

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

			SubmitFeedbackRequestVO submitFeedbackReqVO = (SubmitFeedbackRequestVO) jsonReq;

			logger.info("Sending feedback mail to admin");
			sendFeedbackMailToAdmin(submitFeedbackReqVO);

			logger.info("Submit Feedback Completed");
		} catch (Exception excep) {
			logger.error("Exception while submiting feedback", excep);
		} finally {
			responseVO.setStatusCode(statusCode);
			responseVO.setBody(responseMap);

			logger.info(MealConstants.EXIT);
		}

		return responseVO;

	}

	/**
	 * sendFeedbackMailToAdmin.
	 * 
	 * @param SubmitFeedbackRequestVO
	 *            SubmitFeedbackRequestVO
	 */
	private void sendFeedbackMailToAdmin(final SubmitFeedbackRequestVO submitFeedbackReqVO) {

		new Thread(new Runnable() {
			public void run() {
				Properties props = new Properties();
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

					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress("food@freshybites.com"));
					message.setRecipients(
							Message.RecipientType.TO,
							InternetAddress
									.parse("basha_99964@yahoo.co.in abhinandan.malhotra28@gmail.com sampy.saurav1@gmail.com vishalsuchak21@gmail.com",
											false));
					message.setSubject("Feedback received from " + submitFeedbackReqVO.getName());

					StringBuilder sb = new StringBuilder();

					sb.append("Dear Admin,").append("\n\n We have received following feedback ")
							.append("\n Name: 	" + submitFeedbackReqVO.getName())
							.append("\n Mobile:     	" + submitFeedbackReqVO.getMobile())
							.append("\n Email:	  	" + submitFeedbackReqVO.getEmail())
							.append("\n\n Feedback: 	" + submitFeedbackReqVO.getFeedback());

					sb.append("\n\nThank you,\nFreshy Bites Order Management System.");
					message.setText(sb.toString());

					Transport.send(message);

					System.out.println("sendFeedbackMailToAdmin is success");

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}).start();

	}

}