package examples.pubhub.servlets;

import java.io.IOException;
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

@WebServlet("/PublishTag")
public class PublishTagServlet extends HttpServlet {
private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("tagDetails.jsp").forward(request, response);
	}
   
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String isbn13 = req.getParameter("isbn13");
		String nameTag = req.getParameter("nameTag");
		
		boolean doesExist = false;
		
		BookDAO database1 = DAOUtilities.getBookDAO();
		BookTagDAO database2 = DAOUtilities.getBookTagDAO();
		
		Book tempBook = database1.getBookByISBN(isbn13);
		
		if (tempBook != null) {
			//ASSERT: the book exist to add the tag
			
			List<Tag> tempTagList = database2.getTags(tempBook);
			
			if (tempTagList != null) {
				
				for(Tag t : tempTagList) {
					if (t.getNameTag().contentEquals(nameTag)) {
						// ASSERT: book with tag already exists
						doesExist = true;
						break;
					}
				}
			} 
			
			if (!doesExist) {
				//ASSERT: no tag list
				Tag tag = new Tag();
				tag.setIsbn13(isbn13);
				tag.setNameTag(nameTag);
				req.setAttribute("tag", tag);
				
				boolean isSuccess = database2.addTag(tag);

				if(isSuccess){
					req.getSession().setAttribute("message", "Tag successfully published");
					req.getSession().setAttribute("messageClass", "alert-success");

					// We use a redirect here instead of a forward, because we don't
					// want request data to be saved. Otherwise, when
					// a user clicks "refresh", their browser would send the data
					// again!
					// This would be bad data management, and it
					// could result in duplicate rows in a database.
					resp.sendRedirect(req.getContextPath() + "/TagPublishing");
				}else {
					req.getSession().setAttribute("message", "There was a problem publishing the tag");
					req.getSession().setAttribute("messageClass", "alert-danger");
					req.getRequestDispatcher("tagDetails.jsp").forward(req, resp);
					
				}
			} else {
				doesExist = false;
				req.getSession().setAttribute("message", "Tag of " + nameTag + " is already in use for given isbn");
				req.getSession().setAttribute("messageClass", "alert-danger");
				req.getRequestDispatcher("tagDetails.jsp").forward(req, resp);
			}
		} else {
			req.getSession().setAttribute("message", "ISBN of " + isbn13 + " does not exist");
			req.getSession().setAttribute("messageClass", "alert-danger");
			req.getRequestDispatcher("tagDetails.jsp").forward(req, resp);
		}
		
	}

}
