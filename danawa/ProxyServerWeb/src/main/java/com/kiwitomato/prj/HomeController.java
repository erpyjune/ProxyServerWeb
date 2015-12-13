package com.kiwitomato.prj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/proxy", method = RequestMethod.GET)
	public @ResponseBody String proxy(Locale locale, Model model, HttpServletRequest request, ServletContext ctx) {
		logger.info("proxy read");
	
		try {
		      ////////////////////////////////////////////////////////////////
		      BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/main/java/resources/proxy.dat")));
		      String s;

		      while ((s = in.readLine()) != null) {
		        System.out.println(s);
		      }
		      in.close();
		      ////////////////////////////////////////////////////////////////
		    } catch (Exception e) {
		        System.err.println(Arrays.toString(e.getStackTrace()));
		    }
		
		
		return "home";
	}
	
}
