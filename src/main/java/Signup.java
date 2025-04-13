import java.sql.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet("/Signup")
public class Signup extends HttpServlet{
    PrintWriter out;
    Connection con;
    Statement st;
    ResultSet rs;
    // public void init()throws IOException,ServletException
    // {

    // }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        out=res.getWriter();
        String user=""+req.getParameter("username");
        String phno=""+req.getParameter("phone");
        String email=""+req.getParameter("email");
        String npass=""+req.getParameter("new_password");
        // String cpass=""+req.getParameter("confirm_password");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/details","YOUR_USERNAME","YOUR_PASSWORD");
            st=con.createStatement();
            st.executeUpdate("insert into login values('"+user+"','"+phno+"','"+email+"','"+npass+"');" );
            RequestDispatcher rd=req.getRequestDispatcher("accountcreated.html");
            rd.forward(req, res);
        } catch (Exception e) {
            out.println(""+e);
        }
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        doGet(req,res);
    }
}
