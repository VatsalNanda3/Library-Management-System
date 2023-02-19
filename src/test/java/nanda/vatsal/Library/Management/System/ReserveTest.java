package nanda.vatsal.Library.Management.System;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;


import nanda.vatsal.books.BookRepository;
import nanda.vatsal.reserve.Reserve;
import nanda.vatsal.reserve.ReserveRepository;
import nanda.vatsal.reserve.ReserveService;
import nanda.vatsal.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReserveTest {

    @Autowired
    private ReserveService resreveService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private ReserveRepository reserveRepository;

    @AfterEach
    public void afterSetup(){
        reserveRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void reserveBookTest()
    {
        Reserve res=new Reserve(10,20,"Test_user","Test_user_email","test_user_phone",40,"Test_book_name");
        reserveRepository.save(res);
        assertNotNull(res);
        assertEquals(res.getBookId(),40);
        assertEquals(res.getUserId(),20);
        assertEquals(res.getReserveId(),10);
    }
}
