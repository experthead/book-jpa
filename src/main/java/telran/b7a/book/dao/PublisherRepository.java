package telran.b7a.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.b7a.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

}
