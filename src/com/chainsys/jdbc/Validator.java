package com.chainsys.jdbc;

import com.chainsys.jdbc.test.Book;

public class Validator {

	public static void bookInsertValidator(Book book) throws Exception {
		if(book.name.isEmpty()||book.name.equals(" "))
			throw new Exception("Book name is empty");
		if(book.price==0)
			throw new Exception("Book price is 0");
		if(book.publishDate.equals(null))
			throw new Exception("date is empty");		
		
	}
	public static void bookDeleteValidator(int bookId) throws Exception {
		if(bookId<=0)
			throw new Exception("Book id is invalid");
		
	}
	
}
