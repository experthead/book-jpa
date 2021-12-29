package telran.b7a.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.b7a.book.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
