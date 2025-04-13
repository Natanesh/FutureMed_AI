import java.sql.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
@WebFilter("/Home")
public class Login implements Filter{
    PrintWriter out;
    Connection con;
    Statement st;
    ResultSet rs;
    String password;
    public void init()throws IOException,ServletException
    {

    }
    public void doFilter(ServletRequest req,ServletResponse res,FilterChain fc)throws IOException,ServletException
    {
        res.setContentType("text/html");
        out=res.getWriter();
        String email=""+req.getParameter("usr");
        String pass=""+req.getParameter("pass");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/details","username","password");
            st=con.createStatement();
            rs=st.executeQuery("select * from login where email='"+email+"';" );
            int f=0;
            while(rs.next())
            {
                f=1;
                password=""+rs.getString(4);
                if(pass.equals(password))
                {
                    RequestDispatcher rd=req.getRequestDispatcher("homepage.html");
                    rd.forward(req,res);
                }
                else{
                    RequestDispatcher rd=req.getRequestDispatcher("login.html");
                    rd.include(req,res);
                    out.println("Password was wrong");
                }
            }
            if(f==0)
            {
                RequestDispatcher rd=req.getRequestDispatcher("sign_up.html");
                rd.include(req,res);
                out.println("First create your account");
            }
        } catch (Exception e) {
            out.println(""+e);
        }
    }
    public void destroy()
    {
    }
}
