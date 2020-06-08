package com.servlet;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.entities.Note;

public class dbutil 
{

	private  DataSource dataSource;

	public dbutil(DataSource theDataSource) 
	{
		dataSource = theDataSource;
	}
	
	

	public List<Note> getnotes() throws Exception {
		
		List<Note> notes = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from notes";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String title = myRs.getString("title");
				String content = myRs.getString("content");

				
				// create new employee object
				Note tempnote = new Note(id, title, content);
				
				// add it to the list of employees
				notes.add(tempnote);				
			}
			
			return notes;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private  void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addnote(Note thenote) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			
			System.out.println("<h1>Note is added  dbutil successfully</h1>");
			
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into notes "
					   + "(title, content) "
					   + "values (?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the employee
			myStmt.setString(1, thenote.getTitle());
			myStmt.setString(2, thenote.getContent());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Note getnote(String thenoteId) throws Exception {

		Note thenote = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int noteId;
		
		try {
			// convert employee id to int
			noteId = Integer.parseInt(thenoteId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected employee
			String sql = "select * from notes where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, noteId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String title = myRs.getString("title");
				String content = myRs.getString("content");
				
				
				// use the employeeId during construction
				thenote = new Note(noteId,title,content);
			}
			else {
				throw new Exception("Could not find employee id: " + noteId);
			}				
			
			return thenote;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updatenote( Note thenotes) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			
			System.out.println("update note ");
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update notes "
						+ "set title=?, content=? "
						+ "where id=?";
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, thenotes.getTitle());
			myStmt.setString(2, thenotes.getContent());
			myStmt.setInt(3, thenotes.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deletenote(String thenoteId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert employee id to int
			int noteId = Integer.parseInt(thenoteId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete employee
			String sql = "delete from notes where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, noteId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















