package nanda.vatsal.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nanda.vatsal.books.Book;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	User findByName(String name);
	
	


	

}
