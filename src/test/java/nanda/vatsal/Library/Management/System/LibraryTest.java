package nanda.vatsal.Library.Management.System;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import nanda.vatsal.books.BookRepository;
import nanda.vatsal.email.EmailSenderService;
import nanda.vatsal.lib.Library;
import nanda.vatsal.lib.LibraryRepository;
import nanda.vatsal.lib.LibraryService;
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
public class LibraryTest {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private EmailSenderService senderService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private LibraryRepository libraryRepository;

    @MockBean
    private ReserveRepository resreveRepository;

    @AfterEach
    public void afterSetup()
    {
        bookRepository.deleteAll();
        userRepository.deleteAll();
        libraryRepository.deleteAll();
        resreveRepository.deleteAll();
    }
    Library lib=new Library();
    @Test
    public void lendBooksToUsersTest()
    {


        lib.setBook_id(10);
        lib.setBook_issued("TestBook");
        lib.setBorrower_id(20);
        lib.setEmail("Test_Email");
        lib.setActual_return_date(LocalDate.now());
        lib.setUser_name("Test_Username");
        assertEquals(20,lib.getBorrower_id());
        assertEquals(10,lib.getBook_id());
        assertNotNull(lib);


    }

    @Test
    public void returnToLibraryTest()
    {

        lib.setReturn_date(LocalDate.now());
        lib.setFine(10);
        assertEquals(10,lib.getFine());
        assertNotNull(lib);

    }

}
