package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class BookTagDAOImpl implements BookTagDAO {

	Connection connection = null;	// Our connection to the database
	PreparedStatement stmt = null;	// We use prepared statements to help protect against SQL injection
	
	
	@Override
	public List<Tag> getTags(Book book) {
		List<Tag> tags = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM book_tags WHERE isbn_13=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, book.getIsbn13());
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Tag tag = new Tag();
				
				tag.setNameTag(rs.getString("name_tag"));
				tag.setIsbn13(rs.getString("isbn_13"));
				
				tags.add(tag);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return tags;
	}

	@Override
	public List<Book> getBooksByTag(Tag tag) {
		List<Book> books = new ArrayList<>();
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT * FROM Books WHERE isbn_13 IN"
						+ "(SELECT isbn_13 FROM book_tags WHERE name_tag LIKE ?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, "%" + tag.getNameTag() + "%");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Book book = new Book();
				
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));
				
				books.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return books;
	}

	@Override
	public boolean addTag(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO book_tags (isbn_13,name_tag) VALUES (?,?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getIsbn13());
			stmt.setString(2, tag.getNameTag());
			
			if (stmt.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}

	@Override
	public boolean removeTag(Tag tag) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM book_tags WHERE (name_tag=? AND isbn_13=?)";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, tag.getNameTag());
			stmt.setString(2, tag.getIsbn13());
			
			if (stmt.executeUpdate() != 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	

	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
