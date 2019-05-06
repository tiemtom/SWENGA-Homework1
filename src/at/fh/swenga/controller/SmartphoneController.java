package at.fh.swenga.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.model.SmartphoneModel;
import at.fh.swenga.model.SmartphoneService;

@Controller
public class SmartphoneController {

	@Autowired
	private SmartphoneService smartphoneService;

	@RequestMapping(value = { "/", "listSmartphones" })
	public String showAllSmartphones(Model model) {
		model.addAttribute("smartphones", smartphoneService.getAllSmartphones());
		return "listSmartphones";
	}

	@RequestMapping("/fillSmartphoneList")
	public String fillSmartphoneList(Model model) {

		Date now = new Date();
		smartphoneService
				.addSmartphone(new SmartphoneModel(smartphoneService.getSize() + 1, "Galaxy S10", "Samsung", now, 1.0));
		smartphoneService
				.addSmartphone(new SmartphoneModel(smartphoneService.getSize() + 1, "P30 Pro", "Huawei", now, 1.0));
		smartphoneService
				.addSmartphone(new SmartphoneModel(smartphoneService.getSize() + 1, "Mi 9", "Xiaomi", now, 1.0));
		smartphoneService
				.addSmartphone(new SmartphoneModel(smartphoneService.getSize() + 1, "iPhone 10", "Apple", now, 1.0));
		smartphoneService
				.addSmartphone(new SmartphoneModel(smartphoneService.getSize() + 1, "P20 Pro", "Huawei", now, 1.0));
		smartphoneService
				.addSmartphone(new SmartphoneModel(smartphoneService.getSize() + 1, "Galaxy A7", "Samsung", now, 1.0));

		model.addAttribute("smartphones", smartphoneService.getAllSmartphones());
		return "listSmartphones";
	}

	// Spring 4: @RequestMapping(value = "/deleteSmartphone", method =
	// RequestMethod.GET)
	@GetMapping("/deleteSmartphone")
	public String delete(Model model, @RequestParam int id) {
		boolean isRemoved = smartphoneService.remove(id);

		if (isRemoved) {
			model.addAttribute("warningMessage", "Smartphone " + id + " deleted");
		} else {
			model.addAttribute("errorMessage", "There is no Smartphone " + id);
		}

		// Multiple ways to "forward"
		// return "forward:/listSmartphones";
		return showAllSmartphones(model);
	}

	// Spring 4: @RequestMapping(value = "/searchSmartphones", method =
	// RequestMethod.POST)
	@PostMapping("/searchSmartphones")
	public String search(Model model, @RequestParam String searchString) {
		model.addAttribute("smartphones", smartphoneService.getFilteredSmartphones(searchString));
		return "listSmartphones";
	}

	// Spring 4: @RequestMapping(value = "/addSmartphone", method =
	// RequestMethod.GET)
	@GetMapping("/addSmartphone")
	public String showAddSmartphoneForm(Model model) {
		return "editSmartphone";
	}

	// Spring 4: @RequestMapping(value = "/addSmartphone", method =
	// RequestMethod.POST)
	@PostMapping("/addSmartphone")
	public String addSmartphone(@Valid SmartphoneModel newSmartphoneModel, BindingResult bindingResult, Model model) {

		// Any errors? -> Create a String out of all errors and return to the page
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: " + fieldError.getCode() + "<br>";
			}
			model.addAttribute("errorMessage", errorMessage);

			// Multiple ways to "forward"
			return "forward:/listSmartphones";
		}

		// Look for smartphone in the List. One available -> Error
		SmartphoneModel smartphone = smartphoneService.getSmartphoneById(newSmartphoneModel.getId());

		if (smartphone != null) {
			model.addAttribute("errorMessage", "Smartphone already exists!<br>");
		} else {
			smartphoneService.addSmartphone(newSmartphoneModel);
			model.addAttribute("message", "New smartphone " + newSmartphoneModel.getId() + " added.");
		}

		return "forward:/listSmartphones";
	}
	
	// Spring 4: @RequestMapping(value = "/editSmartphone", method = RequestMethod.GET)
	@GetMapping("/editSmartphone")
	public String showChangeSmartphoneForm(Model model, @RequestParam int id) {
 
		SmartphoneModel smartphone = smartphoneService.getSmartphoneById(id);
 
		if (smartphone != null) {
			model.addAttribute("smartphone", smartphone);
			return "editSmartphone";
		} else {
			model.addAttribute("errorMessage", "Couldn't find smartphone " + id);
			return "forward:/listSmartphones";
		}
	}
	
	// Spring 4: @RequestMapping(value = "/editSmartphone", method = RequestMethod.POST)
	@PostMapping("/editSmartphone")
	public String editSmartphone(@Valid SmartphoneModel changedSmartphoneModel, BindingResult bindingResult,
			Model model) {

		// Any errors? -> Create a String out of all errors and return to the page
		if (bindingResult.hasErrors()) {
			String errorMessage = "";
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				errorMessage += fieldError.getField() + " is invalid: "+fieldError.getCode()+"<br>";
			}
			model.addAttribute("errorMessage", errorMessage);
			return "forward:/listSmartphones";
		}

		// Get the smartphone we want to change
		SmartphoneModel smartphone = smartphoneService.getSmartphoneById(changedSmartphoneModel.getId());

		if (smartphone == null) {
			model.addAttribute("errorMessage", "Smartphone does not exist!<br>");
		} else {
			// Change the attributes
			smartphone.setId(changedSmartphoneModel.getId());
			smartphone.setName(changedSmartphoneModel.getName());
			smartphone.setBrand(changedSmartphoneModel.getBrand());
			smartphone.setReleaseDate(changedSmartphoneModel.getReleaseDate());
			smartphone.setPrice(changedSmartphoneModel.getPrice());

			// Save a message for the web page
			model.addAttribute("message", "Changed smartphone " + changedSmartphoneModel.getId());
		}

		return "forward:/listSmartphones";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}
