package com.tommy.web.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tommy.web.utils.DateUtils;
import com.tommy.web.version.Version;

public class Mail {

	private static final Logger logger = LoggerFactory.getLogger(Mail.class);
	
	private static Properties props = new Properties();
	private static Session mailSession;
    private static String environment_main = System.getenv("ENVIRONMENT_ADMIN");

	// 初始化配置信息 TODO
	static{
		props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
        props.put("mail.user", "");
        props.put("mail.password", "");
	}
		
	// 使用环境属性和授权信息，创建邮件会话
	static{
		Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        mailSession = Session.getInstance(props,authenticator);
	}
	
	/**
	 * 邮件发送
	 * @param toUser 收件人
	 * @param content 内容
	 * @throws MessagingException
	 */
	private static void mail(String toUser,String content) throws MessagingException {
		if("product".equals(environment_main)){
			mail(toUser, null, null, Version.p + "-线上环境邮件预警", content);
		}else{
			mail(toUser, null, null, Version.p + "-测试环境邮件预警", content);
		}
	}
	
	/**
	 * 邮件发送
	 * @param toUser 收件人
	 * @param ccUser 抄送者
	 * @param bccUser 密送者
	 * @param subject 标题
	 * @param content 内容
	 * @throws MessagingException 
	 */
	private static void mail(String toUser,String ccUser,String bccUser,String subject,String content) throws MessagingException {
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        if(StringUtils.isNotBlank(toUser)){
        	String[] users = toUser.split(",");
        	InternetAddress[] addresses = new InternetAddress[users.length];
			for (int i = 0; i < users.length; i++) {
        		String user = users[i];
        		InternetAddress to = new InternetAddress(user);
        		addresses[i] = to;
        	}
        	message.setRecipients(RecipientType.TO, addresses);
        }
        // 设置抄送
        if(StringUtils.isNotBlank(ccUser)){
        	String[] users = ccUser.split(",");
        	InternetAddress[] addresses = new InternetAddress[users.length];
        	for (int i = 0; i < users.length; i++) {
        		String user = users[i];
        		InternetAddress cc = new InternetAddress(user);
        		addresses[i] = cc;
        	}
        	message.setRecipients(RecipientType.CC, addresses);
        }
        // 设置密送，其他的收件人不能看到密送的邮件地址
        if(StringUtils.isNotBlank(bccUser)){
        	String[] users = bccUser.split(",");
        	InternetAddress[] addresses = new InternetAddress[users.length];
        	for (int i = 0; i < users.length; i++) {
        		String user = users[i];
        		InternetAddress bcc = new InternetAddress(user);
        		addresses[i] = bcc;
        	}
        	message.setRecipients(RecipientType.BCC, addresses);
        }
        // 设置邮件标题
        message.setSubject(subject);
        // 设置邮件的内容体
        message.setContent(htmlMail(content), "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
	}
	
	/**
	 * 封装HTML代码
	 * @param content
	 * @return
	 */
	private static String htmlMail(String content){
		StringBuilder html = new StringBuilder();
		html.append(" <!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		html.append(" <html xmlns=\"http://www.w3.org/1999/xhtml\">");
		html.append(" <head>");
		html.append(" <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		html.append(" <title>Top 10 Express Table Designs - Smashing Magazine Source</title>");
		html.append(" <style type=\"text/css\">");
		html.append(" body");
		html.append(" {");
		html.append(" 	line-height: 1.6em;");
		html.append(" }");
		html.append(" #box-table-a");
		html.append(" {");
		html.append(" 	font-family: \"courier\", \"monospace\";");
		html.append(" 	margin: 45px;");
		html.append(" 	width: 80%;");
		html.append(" 	text-align: left;");
		html.append(" 	border-collapse: collapse;");
		html.append(" 	border-top: 5px solid #9baff1;");
		html.append(" 	border-bottom: 5px solid #9baff1;");
		html.append(" }");
		html.append(" #box-table-a th");
		html.append(" {");
		html.append(" 	font-size: larger;");
		html.append(" 	font-weight: bold;");
		html.append(" 	padding: 8px;");
		html.append(" 	background: #b9c9fe;");
		html.append(" 	color: #039;");
		html.append(" 	border-right: 1px solid #aabcfe;");
		html.append(" 	border-left: 1px solid #aabcfe;");
		html.append(" }");
		html.append(" #box-table-a td");
		html.append(" {");
		html.append(" 	font-size: smaller;");
		html.append(" 	padding: 8px;");
		html.append(" 	background: #e8edff; ");
		html.append(" 	border-bottom: 1px solid #fff;");
		html.append(" 	border-top: 1px solid transparent;");
		html.append(" 	border-right: 1px solid #aabcfe;");
		html.append(" 	border-left: 1px solid #aabcfe;");
		html.append(" 	color: #669;");
		html.append(" }");
		html.append(" </style>");
		html.append(" </head>");
		html.append(" <body>");
		html.append(" <center>");
		html.append(" <table id=\"box-table-a\">");
		html.append("     <thead>");
		html.append("     	<tr>");
		html.append("         	<th scope=\"col\" width=\"20%\">Date</th>");
		html.append("             <th scope=\"col\" width=\"80%\">Message</th>");
		html.append("         </tr>");
		html.append("     </thead>");
		html.append("     <tbody>");
		html.append("     	<tr>");
		html.append("         	 <td>" + DateUtils.dateToStr(new Date(), "YYYY-MM-dd HH:mm:ss") +"</td>");
		html.append("             <td>" + content +"</td>");
		html.append("         </tr>");
		html.append("     </tbody>");
		html.append(" </table>");
		html.append(" </center>");
		html.append(" </body>");
		html.append(" </html>");
		return html.toString();
	}
	
}
