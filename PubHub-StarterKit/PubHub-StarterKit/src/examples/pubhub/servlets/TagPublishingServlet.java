package examples.pubhub.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.BookDAO;
import examples.pubhub.dao.BookTagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

/**
 * Servlet implementation class TagPublishingServlet
 */
@WebServlet("/TagPublishing")
public class TagPublishingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookDAO dao = DAOUtilities.getBookDAO();
		List<Book> bookList = dao.getAllBooks();
		
		BookTagDAO dao2 = DAOUtilities.getBookTagDAO();
		List<Tag> tagList = new ArrayList<Tag>();
		
		for(Book b : bookList) {
			List<Tag> tempTagList = dao2.getTags(b);
			for(Tag t : tempTagList) {
				tagList.add(t);
			}
		}
		
		request.getSession().setAttribute("tags", tagList);
		request.getRequestDispatcher("tagPublishingHome.jsp").forward(request, response);
	}

}
