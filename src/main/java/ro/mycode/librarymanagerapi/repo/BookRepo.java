package ro.mycode.librarymanagerapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.mycode.librarymanagerapi.model.Book;


@Repository
public interface BookRepo extends JpaRepository<Book, Long> {





}
