package javastudy;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Modify
 */
@WebServlet("/Modify")
public class Modify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		String author=request.getParameter("author");
		Float price=Float.parseFloat(request.getParameter("price"));
		String publisher=request.getParameter("publisher");
		Book book=new Book();
		book.setAuthor(author);
		book.setId(id);
		book.setName(name);
		book.setPrice(price);
		book.setPublisher(publisher);
		
		try {
			BookDAO dao=new BookDAO();
			dao.modifyBook(id, book);
			response.sendRedirect("bookList.jsp");
		} catch (SQLException |ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
