
package nanda.vatsal.reserve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import nanda.vatsal.books.Book;
import nanda.vatsal.books.BookRepository;
import nanda.vatsal.user.User;
import nanda.vatsal.user.UserRepository;

@EnableAutoConfiguration

@Service 
public class ReserveService {
	
	@Autowired
	private ReserveRepository reserveRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	
	public Reserve reserveBook(int userId, int bookId)
	{
		
		Reserve res=new Reserve();
		Book book=bookRepository.findById(bookId).get();
		
		User user=userRepository.findById(userId).get();
		res.setBookId(book.getBookId());
		res.setBookName(book.getTitle());
		res.setUserId(user.getId());
		res.setUserName(user.getName());
		res.setUserEmail(user.getEmail());
		res.setUserPhone(user.getPhoneNo());
		
		
		reserveRepository.save(res);
		
		return res;
		
		
		
	}
	
	public void deleteReserve(int reserveId)
	{
		reserveRepository.deleteById(reserveId);
	}

}
