package gestiondesrepas.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gestiondesrepas.bll.AlimentManager;
import gestiondesrepas.bll.RepasManager;
import gestiondesrepas.bo.Aliment;
import gestiondesrepas.bo.Repas;

/**
 * Servlet implementation class ServletAjoutRepas
 */
@WebServlet("/ServletAjoutRepas")
public class ServletAjoutRepas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjoutRepas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ajout.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Recuperation des donnees de la jsp
		String dateAsString = request.getParameter("date");
		String timeAsString = request.getParameter("time");
		String alimentsAsString = request.getParameter("aliments");
		
		// Formatage des données
		LocalDate date = LocalDate.parse(dateAsString);
		LocalTime time = LocalTime.parse(timeAsString);
		List<String> alimentsAsStringList = Arrays.asList(alimentsAsString.split(","));
		List<Aliment> alimentsAsList = new ArrayList<Aliment>();
		for(String alim : alimentsAsStringList) {
			alimentsAsList.add(new Aliment(alim.trim()));
		}
		
		// Début de la transaction avec la base de données
		RepasManager rm = new RepasManager();
		Repas repasInsere = rm.inserer(date, time);
		
		AlimentManager am = new AlimentManager();
		am.insererMultiple(repasInsere.getId(), alimentsAsList);
		
		RequestDispatcher rd = request.getRequestDispatcher("/ServletVisualisationRepas");
		rd.forward(request, response);
	}

}
