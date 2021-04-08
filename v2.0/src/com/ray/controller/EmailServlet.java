package com.ray.controller;

//File Name SendEmail.java
import java.io.IOException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/email")
public class EmailServlet extends HttpServlet {
    
    private final String USERNAME = "youremail";
    private final String PASSWORD = "***";

    private static final long serialVersionUID = 1L;

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
       // Recipient's email ID needs to be mentioned.
       String to = "rayllanderson@gmail.com";
  
       // Assuming you are sending email from localhost
       String host = "smtp.gmail.com";
       
       //email do usuario
       String emailUser = request.getParameter("email"); 
  
       // properties
       Properties properties= new Properties();  
  
       // Setup mail server
       properties.put("mail.smtp.auth", "true");
       properties.put("mail.smtp.starttls", "true");
       properties.put("mail.smtp.host", host);
       properties.put("mail.smtp.port", "465");
       properties.put("mail.smtp.socketFactory.port", "465");
       properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
  
       // Get the default Session object.
       Session session = Session.getDefaultInstance(properties, new Authenticator() {
	   @Override
	protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(USERNAME, PASSWORD);
	}
    });
      
       response.setContentType("text/plain");
       response.setCharacterEncoding("UTF-8");
       try {
          // Create a default MimeMessage object.
          MimeMessage message = new MimeMessage(session);
          
          // Set From: header field of the header.
          message.setFrom(new InternetAddress(USERNAME));
          
          // Set To: header field of the header.
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
          
          // Set Subject: header field - titulo email
          message.setSubject("Gerenciador-Compras- Novo email de " + emailUser); 
          
          // Now set the actual message
          message.setText("Email do usuario: " + emailUser + ". Corpo: " + request.getParameter("message"));
          
          // Send message
          Transport.send(message);
          
          response.getWriter().write("email enviado com sucesso!");
          response.setStatus(200);
       } catch (MessagingException mex) {
          mex.printStackTrace();
          response.getWriter().write("Ocorreu um erro");
          response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
       }
    }
}
