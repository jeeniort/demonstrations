package recherchernombre;

import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Saisie
 */
@WebServlet(
		urlPatterns="/Saisie",
		initParams= {
				@WebInitParam(
						name="BORNE_INFERIEURE",
						value="21"
						),
				@WebInitParam(
						name="BORNE_SUPERIEURE",
						value="75"
						)
		}
)
public class ServletSaisie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int guess;
	private int inf;
	private int sup;

    /**
     * Default constructor. 
     */
    public ServletSaisie() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	
    	String borneInf = this.getInitParameter("BORNE_INFERIEURE");
    	String borneSup = this.getInitParameter("BORNE_SUPERIEURE");
    	if(borneInf != null && borneInf.length() > 0)
    		inf = Integer.parseInt(this.getInitParameter("BORNE_INFERIEURE"));
    	if(borneSup != null && borneSup.length() > 0)
    		sup = Integer.parseInt(this.getInitParameter("BORNE_SUPERIEURE"));
    	
//    	try {
//    		inf = Integer.parseInt(this.getInitParameter("BORNE_INFERIEURE"));
//    	} catch (Exception e) {
//    		inf = 0;
//    	}
//    	
//    	try {
//    		sup = Integer.parseInt(this.getInitParameter("BORNE_SUPERIEURE"));
//    	} catch (Exception e) {
//    		sup = 10;
//    	}
    	
    	guess = new Random().nextInt(sup - inf) + inf;

    	System.out.println(inf);
    	System.out.println(sup);
    	System.out.println(guess);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		guess = new Random().nextInt(sup - inf + 1) + inf;
		response.sendRedirect("/MyTPRechercherNombre/html/saisie.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String choice = request.getParameter("choice");
		System.out.println(choice);
		int choiceInt = Integer.parseInt(choice);
		
		if(choiceInt == guess) response.sendRedirect("/MyTPRechercherNombre/html/victoire.html");
		else response.sendRedirect("/MyTPRechercherNombre/html/echec.html");
	}

}
