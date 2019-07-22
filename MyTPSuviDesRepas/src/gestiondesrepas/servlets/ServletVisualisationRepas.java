package gestiondesrepas.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestiondesrepas.bll.AlimentManager;
import gestiondesrepas.bll.RepasManager;
import gestiondesrepas.bo.Repas;

/**
 * Servlet implementation class ServletVisualisationRepas
 */
@WebServlet("/ServletVisualisationRepas")
public class ServletVisualisationRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVisualisationRepas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperation de l'information en base de données
		RepasManager rm = new RepasManager();
		List<Repas> repas = rm.selectAll();
		
		AlimentManager am = new AlimentManager();
		am.selectAlimentByRepas(repas);
		
		// Stockage de l'information pour la jsp
		request.setAttribute("repas", repas);
		
		// Redirection vers la jsp correspondante
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/visualisation.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
