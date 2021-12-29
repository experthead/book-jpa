package telran.b7a.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.b7a.book.dao.AuthorRepository;
import telran.b7a.book.dao.BookRepository;
import telran.b7a.book.dao.PublisherRepository;
import telran.b7a.book.dto.BookDto;
import telran.b7a.book.dto.exceptions.EntityNotFoundException;
import telran.b7a.book.model.Author;
import telran.b7a.book.model.Book;
import telran.b7a.book.model.Publisher;

@Service
public class BookServiceImpl implements BookService {

	ModelMapper modelMapper;
	BookRepository bookRepository;
	AuthorRepository authorRepository;
	PublisherRepository publisherRepository;

	@Autowired
	public BookServiceImpl(ModelMapper modelMapper, BookRepository bookRepository, AuthorRepository authorRepository,
			PublisherRepository publisherRepository) {
		this.modelMapper = modelMapper;
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	@Transactional
	public boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		// Publisher
		Publisher publisher = publisherRepository.findById(bookDto.getPublisher())
				.orElse(publisherRepository.save(new Publisher(bookDto.getPublisher())));
		// Author
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName())
						.orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(book, BookDto.class);
	}

}
