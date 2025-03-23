package com.wondersri.wondersri.service.Impl;

import com.wondersri.wondersri.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendBookingConfirmationEmail(String toEmail, String bookingCode, String userName,
                                             String boatName, String boatLocation, String bookingDate,
                                             String timeSlot) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject("Booking Confirmation - WonderSri");
        helper.setFrom("wordersri.services@gmail.com");

        String htmlContent = getBookingConfirmationHtml(bookingCode, userName, boatName, boatLocation, bookingDate, timeSlot);
        helper.setText(htmlContent, true);

        ClassPathResource logoImage = new ClassPathResource("static/wondersri-logo.png");
        helper.addInline("logoImage", logoImage);

        mailSender.send(mimeMessage);
    }

    private String getBookingConfirmationHtml(String bookingCode, String userName, String boatName,
                                              String boatLocation, String bookingDate, String timeSlot) {
        String htmlTemplate =
                "<!DOCTYPE html>" +
                        "<html lang=\"en\">" +
                        "<head>" +
                        "    <meta charset=\"UTF-8\">" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                        "    <title>WonderSri - Your Virtual Tour Guide</title>" +
                        "    <style>" +
                        "        body {" +
                        "            font-family: Arial, sans-serif;" +
                        "            line-height: 1.6;" +
                        "            margin: 0;" +
                        "            padding: 0;" +
                        "            background-color: #f5f5f5;" +
                        "        }" +
                        "        .email-container {" +
                        "            max-width: 600px;" +
                        "            margin: 20px auto;" +
                        "            background: #ffffff;" +
                        "            padding: 20px;" +
                        "            border-radius: 8px;" +
                        "            box-shadow: 0 0 10px rgba(0,0,0,0.1);" +
                        "        }" +
                        "        .header {" +
                        "            background: #007BFF;" +
                        "            color: white;" +
                        "            padding: 20px;" +
                        "            text-align: center;" +
                        "            border-radius: 8px 8px 0 0;" +
                        "        }" +
                        "        .logo {" +
                        "            max-width: 150px;" +
                        "            height: auto;" +
                        "            margin-bottom: 10px;" +
                        "        }" +
                        "        .content {" +
                        "            padding: 20px;" +
                        "        }" +
                        "        .footer {" +
                        "            background: #f1f1f1;" +
                        "            padding: 10px;" +
                        "            text-align: center;" +
                        "            border-radius: 0 0 8px 8px;" +
                        "            font-size: 0.9em;" +
                        "        }" +
                        "        .button {" +
                        "            display: inline-block;" +
                        "            padding: 10px 20px;" +
                        "            margin: 20px 0;" +
                        "            background: #007BFF;" +
                        "            color: white;" +
                        "            text-decoration: none;" +
                        "            border-radius: 5px;" +
                        "        }" +
                        "        .details-table {" +
                        "            width: 100%;" +
                        "            margin: 20px 0;" +
                        "            border-collapse: collapse;" +
                        "        }" +
                        "        .details-table td {" +
                        "            padding: 10px;" +
                        "            border-bottom: 1px solid #ddd;" +
                        "        }" +
                        "        .highlight {" +
                        "            background: #e7f3ff;" +
                        "            font-weight: bold;" +
                        "        }" +
                        "    </style>" +
                        "</head>" +
                        "<body>" +
                        "    <div class=\"email-container\">" +
                        "        <div class=\"header\">" +
                        "            <img src=\"cid:logoImage\" alt=\"WonderSri Logo\" class=\"logo\">" +
                        "            <h1>Booking Confirmation</h1>" +
                        "        </div>" +
                        "        <div class=\"content\">" +
                        "            <p>Dear <strong>{{userName}}</strong>,</p>" +
                        "            <p>Your boat booking with <strong>WonderSri</strong> has been successfully confirmed. Here are your booking details:</p>" +
                        "            <table class=\"details-table\">" +
                        "                <tr>" +
                        "                    <td>Booking Confirmation Code</td>" +
                        "                    <td class=\"highlight\"><strong>{{bookingCode}}</strong></td>" +
                        "                </tr>" +
                        "                <tr>" +
                        "                    <td>Boat Name</td>" +
                        "                    <td>{{boatName}}</td>" +
                        "                </tr>" +
                        "                <tr>" +
                        "                    <td>Boat Location</td>" +
                        "                    <td>{{boatLocation}}</td>" +
                        "                </tr>" +
                        "                <tr>" +
                        "                    <td>Booking Date</td>" +
                        "                    <td>{{bookingDate}}</td>" +
                        "                </tr>" +
                        "                <tr>" +
                        "                    <td>Time Slot</td>" +
                        "                    <td>{{timeSlot}}</td>" +
                        "                </tr>" +
                        "            </table>" +
                        "            <a href=\"https://wondersri.example.com/booking/{{bookingCode}}\" class=\"button\">View My Booking</a>" +
                        "            <p>If you have any questions, feel free to reach out to us:</p>" +
                        "            <ul style=\"list-style: none; padding-left: 0;\">" +
                        "                <li>Email: <a href=\"mailto:wordersri.services@gmail.com\">wordersri.services@gmail.com</a></li>" +
                        "                <li>Phone: <a href=\"tel:+44754690501\">07546 90501</a> (LK)</li>" +
                        "            </ul>" +
                        "            <p>Thank you for choosing <strong>WonderSri</strong> - Your Virtual Tour Guide!</p>" +
                        "        </div>" +
                        "        <div class=\"footer\">" +
                        "            <p>This is an automated email. Please do not reply directly.</p>" +
                        "            <p>" +
                        "                Contact Us: " +
                        "                <a href=\"tel:+44754690501\">07546 90501</a> | " +
                        "                <a href=\"mailto:wordersri.services@gmail.com\">wordersri.services@gmail.com</a>" +
                        "            </p>" +
                        "            <p>© 2025 WonderSri. All rights reserved.</p>" +
                        "        </div>" +
                        "    </div>" +
                        "</body>" +
                        "</html>";

        return htmlTemplate
                .replace("{{userName}}", userName)
                .replace("{{bookingCode}}", bookingCode)
                .replace("{{boatName}}", boatName)
                .replace("{{boatLocation}}", boatLocation)
                .replace("{{bookingDate}}", bookingDate)
                .replace("{{timeSlot}}", timeSlot);
    }
}