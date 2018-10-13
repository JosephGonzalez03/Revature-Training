package examples.pubhub.dao;

import java.util.List;
import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface BookTagDAO {
	
	public List<Tag> getTags(Book book);
	public List<Book> getBooksByTag(Tag tag);

	public boolean addTag(Tag tag);
	public boolean removeTag(Tag tag);
}
