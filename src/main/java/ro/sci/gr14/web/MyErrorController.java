package ro.sci.gr14.web;

/*
 * An application that helps homeowners find handymen/craftsmen suitable for any task at hand
 */

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller implementation for error events
 *
 * @author Cristina Lenghel
 * @author Octavian Stefanescu
 * @author Marius-Laurentiu Lorincz
 * @author Cosmin Toma
 * @author Serban Moraru
 * @version 1.0
 * @since 2019-05-08
 */
@Controller
public class MyErrorController implements ErrorController {

    /**
     * The handling methods on this controller are relative to the /error path
     *
     * @param httpRequest the Javax Servlet {@link HttpServletRequest}
     * @param model       the Spring MVC {@link Model}
     * @return /error/errorPage
     */
    @RequestMapping("/error")
    public String renderErrorPage(HttpServletRequest httpRequest, Model model){

        //ModelAndView errorPage = new ModelAndView("error/errorPage");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }

        model.addAttribute("errorMsg", errorMsg);
        return "/error/errorPage";
    }

    /**
     * Method overriden from the {@link Object} class
     *
     * @return /error/errorPage
     */
    @Override
    public String getErrorPath( ){
        return "/error/errorPage";
    }

    /**
     * Returns the encountered error code
     *
     * @param httpRequest the Javax Servlet {@link HttpServletRequest}
     * @return error code as an Integer
     */
    private int getErrorCode(HttpServletRequest httpRequest){
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}