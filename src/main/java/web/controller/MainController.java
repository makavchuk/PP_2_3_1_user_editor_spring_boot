package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

@Controller
public class MainController {

	private UserService userService;

	public MainController(@Autowired UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public ModelAndView mainPage() {
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.getModel().put("users", userService.getUsers());
		return modelAndView;
	}

	@GetMapping(value = "/add")
	public ModelAndView addPage() {
		return addEditPage(-1, "/add");
	}

	@GetMapping(value = "/edit/{id}")
	public ModelAndView editPage(@PathVariable int id) {
		return addEditPage(id, "/edit/" + id);
	}

	public ModelAndView addEditPage(int id, String action) {
		ModelAndView modelAndView = new ModelAndView("form");
		User user = id < 0 ? new User() : userService.getUser(id);
		modelAndView.getModel().put("user", user);
		modelAndView.getModel().put("action", action);
		return modelAndView;
	}


	@RequestMapping(value = "/add", method = {RequestMethod.PUT, RequestMethod.POST})
	public ModelAndView createUser(@ModelAttribute("user") User user) {
		System.out.println("inserting " + user);
		userService.addUser(user);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/edit/{id}", method = {RequestMethod.PUT, RequestMethod.POST})
	public ModelAndView updateUser(@ModelAttribute("user") User user, @PathVariable int id) {
		System.out.println("updating id = " + id);
		user.setId(id);
		userService.updateUser(user);
		return new ModelAndView("redirect:/");
	}

	@GetMapping (value = "/delete/{id}")
	public ModelAndView deleteUser(@PathVariable int id) {
		System.out.println("deleting id = " + id);
		userService.deleteUser(id);
		return new ModelAndView("redirect:/");
	}


}
