package com.chainsys.jdbc.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import com.chainsys.jdbc.BookDAO;
import com.chainsys.jdbc.Validator;

public class BookDAOTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out
				.println("Enter option \n1.add book\n2.delete book\n3.select by id\n4.select all");
		int option = scanner.nextInt();
		switch (option) {
		case 1:
			addBooks(scanner);
			break;
		case 2:
			deleteBook(scanner);
			break;
		case 3:
			selectBookById(scanner);
			break;
		case 4:
			selectAllBook();
			break;

		}

		scanner.close();
	}

	public static void addBooks(Scanner scanner) {
		Book book = new Book();
		System.out.println("Enter book name: ");
		scanner.nextLine();
		book.name = scanner.nextLine();
		System.out.println("Enter book price: ");
		book.price = scanner.nextInt();
		System.out.println("Enter publish date: ");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter
				.ofPattern("dd/MM/yyyy");
		book.publishDate = LocalDate.parse(scanner.next(), dateTimeFormatter);
		BookDAO bookDAO = new BookDAO();

		int addResult;
		try {
			Validator.bookInsertValidator(book);
			addResult = bookDAO.addBook(book);
			if (addResult > 0)
				System.out.println("Record inserted.");
			else
				System.out.println("Record not inserted.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public static void deleteBook(Scanner scanner) {
		System.out.println("Enter book id to select: ");
		int bookId = scanner.nextInt();
		BookDAO bookDAO = new BookDAO();
		try {
			Validator.bookDeleteValidator(bookId);
			int deleteResult = bookDAO.deleteBook(bookId);
			if (deleteResult > 0)
				System.out.println("record Deleted.");
			else
				System.out.println("record not deleted.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public static void selectBookById(Scanner scanner) {
		System.out.println("Enter book id to select: ");
		int bookId = scanner.nextInt();
		BookDAO bookDAO = new BookDAO();
		try {
			Book tempBookList = bookDAO.findBookById(bookId);
			System.out.println("Book details: ");
			System.out.println("BookId BookName BookPrice  PublishDate");

			System.out.println(tempBookList.id + " " + tempBookList.name + " "
					+ tempBookList.price + " " + tempBookList.publishDate);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void selectAllBook() {
		BookDAO bookDAO = new BookDAO();

		ArrayList<Book> bookList;
		try {
			bookList = bookDAO.findAllBook();
			displayBookDetails(bookList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void displayBookDetails(ArrayList<Book> bookList) {
		System.out.println("Book details: ");
		System.out.println("BookId BookName BookPrice  PublishDate");

		for (Book tempBookList : bookList) {
			System.out.println(tempBookList.id + " " + tempBookList.name + " "
					+ tempBookList.price + " " + tempBookList.publishDate);
		}

	}
}
