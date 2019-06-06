package online.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class ExampleServlet
 */
@WebServlet("/ExampleServlet")
public class ExampleServlet extends HttpServlet {
	Logger logger = LogManager.getLogger(ExampleServlet.class);
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ExampleServlet() {
    	logger.info("Exmaple is initial");

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("<html></html>");
		logger.info("I get a get request");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		logger.info("I get a post request");
	}
	
	//socket連線
	public static void main(String[] args) throws Exception {		
		String getReq = "HEAD / HTTP/1.1 \n";
		getReq = getReq + "Host: www.google.com\n\n";
		Socket socket = new Socket();
        InetSocketAddress isa = new InetSocketAddress("www.google.com", 80);
        socket.connect(isa);
        OutputStream output = socket.getOutputStream();
        output.write(getReq.getBytes());
        output.flush();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = "";
        while((s=reader.readLine()) != null) {
        	System.out.println(s);
        }
        socket.close();
//		Queue<String> a = new LinkedList<String>();
//		a.add("a");
//		a.add("b");
//		System.out.println(a);
//		System.out.println(a.poll());
//		System.out.println(a.peek());
//		System.out.println(a);
//		Map<String,Object> mmm = new HashMap<String,Object>();
//		mmm.put("aa", "I am a");
//		mmm.put("bb", 123);
//		mmm.put("queue", a);
//		System.out.println(mmm);
	}
	public static int aaa(int i) {
		i++;
		System.out.println(i);
		if(i > 10000) {
			return i;
		} else {
			return aaa(i);
		}
	}

}
