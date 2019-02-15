package com.chainsys.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.chainsys.jdbc.test.Book;

public class BookDAOUnitTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddBook() throws Exception {
//		fail("Not yet implemented");
		Book book=new Book();
		book.name="c++";
		book.price=100;
		book.publishDate=LocalDate.parse("2019-02-15");
		BookDAO bookDAO=new BookDAO();
		int result=bookDAO.addBook(book);	
		
		assertEquals(1, result);
		
	}

	@Test
	public void testDeleteBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindBookById() throws Exception {
		Book book=new Book();
		book.id=4;
		BookDAO bookDAO=new BookDAO();
		
		Book bookResult=bookDAO.findBookById(book.id);
//		System.out.println(bookResult.id);
		assertEquals(book.id, bookResult.id);
//		fail("Not yet implemented");
	}

	@Test
	public void testFindAllBook() {
		fail("Not yet implemented");
	}

}
