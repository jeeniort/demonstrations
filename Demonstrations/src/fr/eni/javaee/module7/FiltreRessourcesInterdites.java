package fr.eni.javaee.module7;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltreRessourcesInterdites
 */
@WebFilter(
	urlPatterns="/modules/module7/*",
	dispatcherTypes= {
				DispatcherType.REQUEST,
				DispatcherType.INCLUDE,
				DispatcherType.FORWARD,
				DispatcherType.ERROR
				}
)
public class FiltreRessourcesInterdites implements Filter {

    /**
     * Default constructor. 
     */
    public FiltreRessourcesInterdites() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if(!httpRequest.getServletPath().toLowerCase().contains("interdit"))
		{
			//Laissons passer la requête vers la ressource qui est autorisée
			chain.doFilter(request, response);
		}
		else
		{
			//renvoyons une 403 à l'utilisateur
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
