package online.store.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Product;

@WebServlet("/CartController")
public class CartController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		Product pro = new Product();
		HttpSession session = request.getSession();
		if (action.equals("cartnow")) {
			if (session.getAttribute("cart") == null) {
				List<Product> cart = new ArrayList<Product>();
				
				
			}
		}
	}

}
