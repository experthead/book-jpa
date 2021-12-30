package telran.b7a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.book.dto.AuthorDto;
import telran.b7a.book.dto.BookDto;
import telran.b7a.book.service.BookService;

@RestController
public class BookController {

	BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/book")
	public boolean addBook(@RequestBody BookDto bookDto) {
		return bookService.addBook(bookDto);
	}

	@GetMapping("/book/{isbn}")
	public BookDto findBookByIsbn(@PathVariable String isbn) {
		return bookService.findBookByIsbn(isbn);
	}

	@DeleteMapping("/book/{isbn}")
	public BookDto deleteBookByIsbn(@PathVariable String isbn) {
		return bookService.removeBook(isbn);

	}

	@PutMapping("/book/{isbn}/title/{title}")
	public BookDto updateBook(@PathVariable String isbn, String title) {
		return bookService.updateBook(isbn, title);

	}

	@GetMapping("/books/author/{author}")
	public Iterable<BookDto> getBooksByAuthor(@PathVariable String author) {
		return bookService.findBooksByAuthor(author);

	}

	@GetMapping("/books/publisher/{publisher}")
	public Iterable<BookDto> getBooksByPublisher(@PathVariable String publisher) {
		return bookService.findBooksByPublisher(publisher);

	}
	@GetMapping("publishers/author/{author}")
	public Iterable<String> findPublishersByAuthor(String author) {
		return bookService.findPublishersAuthor(author);
		
	}
	@DeleteMapping("/author/{author}")
	public AuthorDto removeAuthor(String author) {
		return bookService.removeAuthor(author);
		
	}
}
