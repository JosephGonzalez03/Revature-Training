package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public class TestTagDAO {
	public static void main(String[] args) {
		BookDAO daoBook = new BookDAOImpl();
		BookTagDAO daoTag = new BookTagDAOImpl();
		
		byte[] mb = {(byte)0x81, 0x1c};
		
		Book book = new Book("12345656","The Fun Burrito", "Joey", mb);
		Book book2 = new Book("12345877","The Fun Burrito 2", "Joey", mb);
		
		/**********************************************************/
		
		daoBook.addBook(book);
		daoBook.addBook(book2);
		
		Tag tag1 = new Tag("tasty", book.getIsbn13());
		Tag tag2 = new Tag("fun", book2.getIsbn13());
		Tag tag3 = new Tag("tasty", book2.getIsbn13());
		
		daoTag.addTag(tag1);
		daoTag.addTag(tag2);
		daoTag.addTag(tag3);
		
		List<Tag> list = daoTag.getTags(book);
		List<Tag> list2 = daoTag.getTags(book2);
		
		System.out.println("----------------------------------------------");
		System.out.println("addTag() & getTags() test: book");
		for(Tag t : list) {
			System.out.println(t.getNameTag());
		}
		
		System.out.println("\naddTag() & getTags() test: book2");
		for(Tag t : list2) {
			System.out.println(t.getNameTag());
		}
		
		/**********************************************************/
		
		List<Book> books = daoTag.getBooksByTag(tag1);
		
		System.out.println("----------------------------------------------");
		System.out.println("getBooksByTag() test: tasty");
		for(Book b : books) {
			System.out.println(b.getTitle());
		}
		
		/**********************************************************/
		
		boolean rmvTag = false;
		
		rmvTag = daoTag.removeTag(tag2);
		List<Tag> tags = daoTag.getTags(book);
		
		System.out.println("----------------------------------------------");
		System.out.println("removeTag() test: remove fun from book2");
		System.out.println("tag removed: " + rmvTag);
		for(Tag t : tags) {
			System.out.println(t.getNameTag());
		}
		
		daoTag.removeTag(tag1);
		daoTag.removeTag(tag2);
		daoTag.removeTag(tag3);
		daoBook.deleteBookByISBN(book.getIsbn13());
		daoBook.deleteBookByISBN(book2.getIsbn13());
	}
}
