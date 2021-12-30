package telran.b7a.book.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.b7a.book.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

	Stream<Book> findByAuthors_Name(String author);

	Stream<Book> findByPublisher_PublisherName(String publisherName);
	
	
	
}

