package com.artempopov.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.artempopov.web.beans.GuitarBean;
import com.artempopov.web.data.CartItemList;
import com.artempopov.web.data.Item;
import com.artempopov.web.data.Order;
import com.artempopov.web.data.OrderHistory;
import com.artempopov.web.data.OrderItems;
import com.fasterxml.classmate.AnnotationConfiguration;


@WebServlet(description = "Proceed order logic", urlPatterns = { "/order" })


public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private long lastOrderId;
	
	private Translator tr;
	
	private SessionFactory sessionFactory;
	
	ArrayList<OrderItems> orders;

    public OrderServlet() {
        super();
        lastOrderId = 0;
                
        //GuitarBean[] guitars = MainServlet.getItems();
        
        /*
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
        for (int i = 0; i < guitars.length; i++) {
        	
        	Item item = new Item();
        	item.setName(guitars[i].getName());
        	item.setPrice(guitars[i].getPrice());
        	item.setType(guitars[i].getType());
        	
        	session.save(item);
        	
        }
		
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
        */
        
        sessionFactory = MainServlet.getSessionFactory();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// User signed in first time 
		// Set up http session info 
		
		tr = MainServlet.getCurrentTranslator(request, response);
		
		if (request.getParameter("action") != null) {
			
			if (request.getParameter("action").equals("orderExist")) {
				orderSpec(request, response, (String) request.getParameter("address"));
			} else {
				// order from existing delivery location
				orderExisting(request, response, (String) request.getParameter("address"));
			}
			
			request.setAttribute("tr", tr);
			request.getServletContext().getRequestDispatcher("/orderComplete.jsp")
			.forward(request, response);
		}
			
		session.setAttribute("logged", "true");
		session.setAttribute("userName", request.getRemoteUser());
		session.setAttribute("cartItems", request.getAttribute("cartItems"));
		session.setAttribute("page", "order");
		
		request.setAttribute("tr", tr);
		request.getServletContext().getRequestDispatcher("/order.jsp")
		.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	private void orderSpec(HttpServletRequest request, HttpServletResponse response, String value) throws ServletException, IOException {
		
		Order newOrder = new Order();
		
		newOrder.setBuyer((String) request.getSession().getAttribute("userName"));
		newOrder.setShopLocation(value);
		newOrder.setOrderDate(new Date());
		
		saveOrder(newOrder);

		
		request.setAttribute("tr", tr);
		request.getServletContext().getRequestDispatcher("/main?page=itemList")
		.forward(request, response);
		
	}
	
	private void orderExisting(HttpServletRequest request, HttpServletResponse response, String value) {
		
		Order newOrder = new Order();

		newOrder.setBuyer((String) request.getSession().getAttribute("userName"));
		newOrder.setShopLocation(value);
		newOrder.setOrderDate(new Date());
		
		saveOrder(newOrder);
		
		
		request.setAttribute("tr", tr);
		try {
			request.getServletContext().getRequestDispatcher("/main?page=itemList")
			.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveOrder(Order order) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(order);
		
		
		CartItemList list = MainServlet.getCartItems();
		
		for (GuitarBean guitar : list) {
			OrderHistory history = new OrderHistory();
			history.setItemId(guitar.getId());
			history.setOrderId(order.getOrderId());
			history.setItemCount(list.getItemCount(guitar));
			session.save(history);
		}
		
		// clear cart
		MainServlet.emptyCart();
		
		session.getTransaction().commit();
		session.close();
		
	}
}

