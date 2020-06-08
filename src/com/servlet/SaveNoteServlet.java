package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.entities.Note;



@WebServlet("/SaveNoteServlet")
public class SaveNoteServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private dbutil notedbutil;
	
	@Resource(name="jdbc/note_tracker")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try 
		{
			notedbutil = new dbutil(dataSource);
		}
		catch (Exception exc) 
		{
			throw new ServletException(exc);
		}
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			try {
				// read the "command" parameter
				String theCommand = request.getParameter("command");
				
				// if the command is missing, then default to listing employees
				if (theCommand == null) {
					theCommand = "LIST";
				}
				
				// route to the appropriate method
				switch (theCommand) {
				
				case "LIST":
					listNotes(request, response);
					break;
					
				case "ADD":
					addNotes(request, response);
					break;
					
				case "LOAD":
					loadNotes(request, response);
					break;	
					
				case "UPDATE":
					updateNotes(request, response);
					break;
				
				case "DELETE":
					deleteNotes(request, response);
					break;
					
				default:
					listNotes(request, response);
				}
					
			}
			catch (Exception exc) {
				throw new ServletException(exc);
			}
			
			
			
		
	}
	

	
	private void addNotes(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		Note thenotes = new Note(title,content);
		
		// add the employee to the database
		notedbutil.addnote(thenotes);
		
		listNotes(request, response);
	
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		out.println("<h1>Note is added successfully</h1>");
		}
	
	private void loadNotes(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read employee id from form data
			String theNoteId = request.getParameter("noteId");
			
			
			Note thenote=notedbutil.getnote(theNoteId);
			
			// place employee in the request attribute
			request.setAttribute("THE_NOTE", thenote);
			
			// send to jsp page: update-employee-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("update_note.jsp");
			dispatcher.forward(request, response);		
		}

	
	
	private void deleteNotes(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read employee id from form data
			String theNoteId = request.getParameter("noteId");
			
			// delete employee from database
			notedbutil.deletenote(theNoteId);
			
			// send them back to "list employees" page
			listNotes(request, response);
		}

		private void updateNotes(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read employee info from form data
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			// create a new employee object
			Note thenotes = new Note(id,title,content);
			
			// perform update on database
			notedbutil.updatenote(thenotes);
			
			// send them back to the "list employees" page
			listNotes(request, response);
			
		}
		
		private void listNotes(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {

			
				List<Note> notes = notedbutil.getnotes();
				
				// add employees to the request
				request.setAttribute("NOTE_LIST", notes);
						
				// send to JSP page (view)
				RequestDispatcher dispatcher = request.getRequestDispatcher("all_notes.jsp");
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employees-with-scriptlets.jsp");
				dispatcher.forward(request, response);
			}


}
