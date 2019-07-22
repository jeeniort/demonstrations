package chifoumi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletTraitantChifoumi
 */
@WebServlet("/ServletTraitantChifoumi")
public class ServletTraitantChifoumi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String[] chifoumi = new String[]{"pierre", "feuille", "ciseaux"};
	private Map<String, Map<String, Integer>> matriceVictoireJoueur;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTraitantChifoumi() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	matriceVictoireJoueur = new HashMap<String, Map<String, Integer>>();
    	HashMap<String, Integer> mapPourFeuille = new HashMap<String, Integer>();
    	mapPourFeuille.put(chifoumi[0], 1);
    	mapPourFeuille.put(chifoumi[1], 0);
    	mapPourFeuille.put(chifoumi[2], -1);
    	matriceVictoireJoueur.put(chifoumi[1], mapPourFeuille);
    	
    	HashMap<String, Integer> mapPourCiseaux = new HashMap<String, Integer>();
    	mapPourCiseaux.put(chifoumi[1], 1);
    	mapPourCiseaux.put(chifoumi[2], 0);
    	mapPourCiseaux.put(chifoumi[0], -1);
    	matriceVictoireJoueur.put(chifoumi[2], mapPourCiseaux);
    	
    	HashMap<String, Integer> mapPourPierre = new HashMap<String, Integer>();
    	mapPourPierre.put(chifoumi[2], 1);
    	mapPourPierre.put(chifoumi[0], 0);
    	mapPourPierre.put(chifoumi[1], -1);
    	matriceVictoireJoueur.put(chifoumi[0], mapPourPierre);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recuperation du choix utilisateur
		String choixUtilisateur = request.getParameter("choix");
		
		// Generation du choix de l'IA
		Random rand = new Random();
		String choixIA = chifoumi[rand.nextInt(chifoumi.length)];
		
		// Dans tous les cas, on voudra afficher les choix effectues
		request.setAttribute("choixUtilisateur", choixUtilisateur);
		request.setAttribute("choixIA", choixIA);
		
		// On verifie ensuite le resultat du chifoumi
		switch (matriceVictoireJoueur.get(choixUtilisateur).get(choixIA)) {
		case 0:
			request.setAttribute("result", "Egalité");
			break;
		case 1 : 
			request.setAttribute("result", "Victoire !");
			break;
		case -1 :
			request.setAttribute("result", "Victoire !.. de l'ordinateur");
		}
		
		// Etape bonus : on choisit une image en fonction du choix
		request.setAttribute("pathIA", choixIA + ".png");
		request.setAttribute("pathUtilisateur", choixUtilisateur + ".png");
		
		// Dans tous les cas, on fait la redirection vers la jsp de resultat
		// RequestDispatcher rd = request.getRequestDispatcher("/MyChifoumiAmeliore/resultat");
		// RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/MyChifoumiAmeliore/resultat");
		RequestDispatcher rd = this.getServletContext().getNamedDispatcher("resultat");
		rd.forward(request, response);
	}

}
