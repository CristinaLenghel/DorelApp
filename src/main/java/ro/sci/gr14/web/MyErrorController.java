package ro.sci.gr14.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {
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
        //errorPage.addObject("errorMsg", errorMsg);
        model.addAttribute("errorMsg", errorMsg);
        return "/error/errorPage";
    }

    @Override
    public String getErrorPath() {
        return "/error/errorPage";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}