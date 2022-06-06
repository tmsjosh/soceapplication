package com.minimart.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.minimart.bean.Item;
import com.minimart.dao.ItemDao;

/**
 * Servlet implementation class ItemServlet
 */
@WebServlet("/")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ItemDao itemDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		itemDao = new ItemDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
	switch (action) {
	case "/new": 
		showNewForm(request, response);
		break;
	case "/insert":
		try {
			insertItem(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/delete":
		try {
			deleteItem(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/edit":
		try {
			showEditForm(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	case "/update":
		try {
			updateItem (request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
		
		default:
		try {
			listItem (request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;
	}
	}	

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void insertItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException  {
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		Item newItem = new Item (name, price);
		
		itemDao.insertItem(newItem);
		response.sendRedirect("list");
		
	}
	
	private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			itemDao.deleteItem(id);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		
        Item existingItem = itemDao.selectItem(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
        request.setAttribute("item", existingItem);
        dispatcher.forward(request, response);
	}
	
	private void updateItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Item item = new Item (id, name, price);
		itemDao.updateItem(item);
		response.sendRedirect("list");
	}
	
	private void listItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		try {
			List<Item> listItem = itemDao.selectAllItems();
			request.setAttribute("listItem", listItem);
			RequestDispatcher dispatcher = request.getRequestDispatcher("item-list.jsp");
			dispatcher.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
