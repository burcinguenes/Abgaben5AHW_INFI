import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*Bycrpt https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCrypt.html*/

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@WebServlet("/LoginRegister")
public class LoginRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginRegister() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("login.jsp").forward(request, response);
   }


    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		DBManager cd = new DBManager();
		
		
		String userName = request.getParameter("username");
		String name = request.getParameter("name");
		String password1 = request.getParameter("pwd1");
		String password2 = request.getParameter("pwd2");

		
		String submitType = request.getParameter("submit");
		Benutzer c = null;
		try {
			c = cd.getCostumer(userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(submitType.equals("login")) {
			
			if(c.equals(null)) {
				request.setAttribute("message", "Benutzer doesn't exist. Please register!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}	
			
			if(c.getPassword().equals(password1)) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				encoder.encode(password1); 
				encoder.matches(password1, c.getPassword());
				request.setAttribute("message", "Login succeeded.");
				request.getRequestDispatcher("welcome_home.jsp").forward(request, response);
		
			}
			
			if(!c.getPassword().equals(password1)) {
				request.setAttribute("message", "Wrong password!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		
		}
					
		
		 if(submitType.equals("register")) {
				
				 if(password1.equals(password2) && !password1.isEmpty() && !userName.isEmpty()) {
						c.setUsername(userName);
						c.setPassword(password1);
			
						boolean exists = false;
						try {
							exists = cd.existsUsername(userName);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if(exists==false)
							{try {
								cd.insertBenutzer(c);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							request.setAttribute("successMessage", "Registration complete, please login!");
							request.getRequestDispatcher("login.jsp").forward(request, response);
							}else {
								request.setAttribute("message", "Benutzername bereits verwendet");
								request.getRequestDispatcher("register.jsp").forward(request, response);
							}
						
					}
		 
				 if(password1.isEmpty() || password2.isEmpty() || !password1.equals(password2)) {
					request.setAttribute("message", "Wrong password entry");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				 }
		
		 
				 if(userName.isEmpty() || name.isEmpty()) {
				 	request.setAttribute("message", "Empty field.");
				 	request.getRequestDispatcher("register.jsp").forward(request, response);
			 	} 
			 
		 }
	}
}