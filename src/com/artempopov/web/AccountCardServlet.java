package com.artempopov.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.artempopov.web.beans.GuitarBean;
import com.artempopov.web.data.Item;
import com.artempopov.web.data.Order;
import com.artempopov.web.data.OrderHistory;
import com.artempopov.web.data.OrderHistoryDetails;

import javassist.bytecode.Descriptor.Iterator;

/**
 * Servlet implementation class AccountCardServlet
 */
@WebServlet("/account")
public class AccountCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SessionFactory sessionFactory;

       
    public AccountCardServlet() {
        super();
        
        sessionFactory = MainServlet.getSessionFactory();
        
        if (sessionFactory == null) {
        	sessionFactory = new Configuration().buildSessionFactory();
        }

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Translator tr = MainServlet.getCurrentTranslator(request, response);
		
		forwardAccountHistory(request, response);

		request.setAttribute("tr", tr);
		request.getServletContext().getRequestDispatcher("/accountPage.jsp")
		.forward(request, response);
	}

	private void forwardAccountHistory(HttpServletRequest request, HttpServletResponse response) {
		
		Session session = null;
		Transaction tx = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			List list = session.createQuery("FROM Order WHERE buyer='artem'").list();
			ArrayList<Order> orders = new ArrayList<>();
			ArrayList<OrderHistory> orderHistory = new ArrayList<>();
			ArrayList<Item> orderGuitars = new ArrayList<>();
			ArrayList<OrderHistoryDetails> orderInfo = new ArrayList<>();
			
			for (Object order : list) {
				
				Order next = (Order) order;

				orders.add(next);
				
				List<OrderHistory> items = session.createQuery("FROM OrderHistory WHERE orderId="+next.getOrderId()).list();
				
				orderGuitars.clear();

				for (OrderHistory history : items) {
					
					orderHistory.add(history);
					
					List<Item> orderItems = session.createQuery("FROM Item WHERE itemId="+history.getItemId()).list();
					
					
					for (Item item : orderItems) {
						orderGuitars.add(item);
					}
					
				}
				
				OrderHistoryDetails newOrderDetails = new OrderHistoryDetails(orderGuitars, next);
				
				orderInfo.add(newOrderDetails);
				
			}
			
			request.setAttribute("orders", orderInfo);
			
			tx.commit();
			
		} catch (HibernateException e) {
			if (tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
