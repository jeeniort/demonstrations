package gestiondesrepas.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/jspAjoutRepas.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Initialisation des erreurs possibles
		List<String> erreurs = new ArrayList<String>();
		
		// Récupération des paramètres
		String dateAsString = request.getParameter("date");
		String timeAsString = request.getParameter("time");
		String ingredients = request.getParameter("repas");
		
		// Transformations des paramètres
		LocalDate date = null;
		LocalTime time = null;
		try {
			date = LocalDate.parse(dateAsString);
		} catch (DateTimeParseException dtpe) {
			erreurs.add("Erreur de parsing : le format de la date est incorrect (dd/MM/yyyy)");
		}
		try {
			time = LocalTime.parse(timeAsString);
		} catch (DateTimeParseException dtpe) {
			erreurs.add("Erreur de parsing : le format de l'heure est incorrect (hh:mm)");
		}
		
		List<Aliment> aliments =
				Arrays.asList(ingredients.split(","))
				.stream()
				.map(s -> new Aliment(s.trim()))
				.collect(Collectors.toList());
		
		if (aliments == null || aliments.size() == 0) {
			erreurs.add("Erreur dans les aliments : la liste ne peux pas être vide");
		}
//		String[] alimentsAsString = ingredients.split(",");
//		List<Aliment> aliments = new ArrayList<Aliment>();
//		for(String alim : alimentsAsString) {
//			Aliment tmp = new Aliment(alim.trim());
//			aliments.add(tmp);
//		}
		
		if (erreurs.size() > 0) {
			request.setAttribute("erreurs", erreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/jspAjoutRepas.jsp");
			rd.forward(request, response);
		} else {
			RepasManager rm = new RepasManager();
			Repas repasInsere = rm.ajouter(date, time);
			repasInsere.setAliments(aliments);
			
			AlimentManager am = new AlimentManager();
			am.ajouterAvecAssociation(repasInsere);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ServletVisualisationRepas");
			rd.forward(request, response);
		}
	}

}
