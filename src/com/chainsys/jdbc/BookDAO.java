package com.chainsys.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.jdbc.test.Book;

public class BookDAO {

	public int addBook(Book book) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "insert into books(id,name,price,publishdate) values (BOOKS_ID_SEQ.NEXTVAL,?,?,?)";
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, book.name);
			preparedStatement.setInt(2, book.price);
			preparedStatement.setDate(3, Date.valueOf(book.publishDate));
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("unable to inserted");
		}

		return result;

	}

	public int deleteBook(int id) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "delete books where id=?";
		int deleteResult = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			deleteResult = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new Exception("Unable to delete");
		}

		return deleteResult;

	}

	public Book findBookById(int id) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Book book = null;
		String query = "select id,name,price,publishdate from books where id=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet != null) {
				while (resultSet.next()) {
					book = new Book();
					book.id = resultSet.getInt("id");
//					System.out.println(book.id);
					book.name = resultSet.getString("name");
					book.price = resultSet.getInt("price");
					book.publishDate = resultSet.getDate("publishdate")
							.toLocalDate();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			 throw new Exception("unable to find record");
		}

		return book;
	}

	public ArrayList<Book> findAllBook() throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Book> bookList = null;
		String query = "select id,name,price,publishdate from books";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				bookList = new ArrayList<Book>();
				while (resultSet.next()) {
					Book book = new Book();
					book.id = resultSet.getInt("id");
					book.name = resultSet.getString("name");
					book.price = resultSet.getInt("price");
					book.publishDate = resultSet.getDate("publishdate")
							.toLocalDate();
					bookList.add(book);
				}
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new Exception("Unable to find records");
		}

		return bookList;
	}

}
