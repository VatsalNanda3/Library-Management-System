//Repository for books

package nanda.vatsal.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nanda.vatsal.books.Book;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{

	Book findByTitle(String title); 
	//Book is the Book.java file where the contents are stored and Integer is used as the datatype of our primary key is int

}
