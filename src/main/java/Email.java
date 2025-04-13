import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Appointment")
public class Email extends HttpServlet
{
MimeMessage mm;
Properties pro;
public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
String name=req.getParameter("pn");//from mail
String email=req.getParameter("email");//from mail
String phno=req.getParameter("phno");//from mail
String dname=req.getParameter("dn");//from mail
String demail=req.getParameter("demail");//from mail
String date=req.getParameter("ad");//from mail
String time=req.getParameter("at");//from mail
String pass="wsfw sxzb lrhg asob";
pro=new Properties();
pro.put("mail.smtp.auth","true");
pro.put("mail.smtp.starttls.enable","true");
pro.put("mail.smtp.host","smtp.gmail.com");
pro.put("mail.smtp.port","587");
Session ss=Session.getDefaultInstance(pro,new Authenticator()
{
protected PasswordAuthentication getPasswordAuthentication()
{
return new PasswordAuthentication("nataneshs@gmail.com",pass);
}
});
try
{
mm=new MimeMessage(ss);
mm.setFrom(new InternetAddress("nataneshs@gmail.com"));
mm.addRecipient(Message.RecipientType.TO,new InternetAddress(demail));
mm.setSubject("Appointment Mail");
mm.setText("Hello Dr."+dname+",i'm "+name+" I want to schedule an appointment with you for consulting\r\n" + //
        "-------------------Contact Details-------------------\r\n" + //
        "E-mail:"+email+"\r\n" + //
        "PhoneNo:"+phno+"\r\n" + //
        "Appointment Date:"+date+"\r\n" + //
        "Appointment Time:"+time+"\r\n");

Transport.send(mm);
RequestDispatcher rd=req.getRequestDispatcher("emailsended.html");
rd.forward(req, res);
}
catch(Exception e)
{
System.out.println(e);
}
}
public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
    doGet(req,res);
}
}