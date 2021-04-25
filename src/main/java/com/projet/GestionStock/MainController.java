/**
 * 
 */
package com.projet.GestionStock;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author ibrahima
 *
 */
@Controller
public class MainController {
	
	@RequestMapping("/gesstock")
	public String index(Model model)
		{
		return "index";
		}

	@RequestMapping("/")
	public String login(Model model)
		{
		return "login";
		}
}
