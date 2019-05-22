package at.fh.swenga.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import at.fh.swenga.dao.BrandRepository;
import at.fh.swenga.dao.SmartphoneRepository;
import at.fh.swenga.model.BrandModel;
import at.fh.swenga.model.SmartphoneModel;

@Controller
public class SmartphoneController {

	@Autowired
	SmartphoneRepository smartphoneRepository;
	
	@Autowired
	BrandRepository brandRepository;

	@RequestMapping(value = { "/", "list" })
	public String index(Model model) {
		List<SmartphoneModel> smartphones = smartphoneRepository.findAll();
		model.addAttribute("smartphones", smartphones);
		model.addAttribute("count", smartphones.size());
		return "index";
	}
	
	@RequestMapping(value = { "/getPage" })
	public String getPage(Pageable page,Model model) {
		Page<SmartphoneModel> smartphonesPage = smartphoneRepository.findAll(page);
		model.addAttribute("smartphonesPage", smartphonesPage);
		model.addAttribute("smartphones", smartphonesPage.getContent());
		model.addAttribute("count", smartphonesPage.getTotalElements());
		return "index";
	}

	@RequestMapping(value = { "/find" })
	public String find(Model model, @RequestParam String searchString, @RequestParam String searchType) {
		List<SmartphoneModel> smartphones = null;
		int count=0;

		switch (searchType) {
		case "query1":
			smartphones = smartphoneRepository.findAll();
			break;
		case "query2":
			smartphones = smartphoneRepository.findByName(searchString);
			break;
		case "query3":
			smartphones = smartphoneRepository.findByBrand(searchString);
			break;
		case "query4":
			smartphones = smartphoneRepository.findByBrandOrderByPriceAsc(searchString);
			break;
		case "query5":
			smartphones = smartphoneRepository.findByNameOrBrand(searchString);
			break;
		case "query6":
			if(searchString.isEmpty())
			{
				count = 0;
			}
			else {
			count = smartphoneRepository.countByBrand(searchString);
			}
			break;
		case "query7":
			smartphones = smartphoneRepository.findTop1ByNameOrderByPriceAsc(searchString);
			break;
		case "query8":
			double searchPrice = Double.parseDouble(searchString);
			smartphones = smartphoneRepository.findByPrice(searchPrice);
			break;
		case "query9":
			smartphones = smartphoneRepository.deleteFirstByNameOrderByPriceDesc(searchString);
			break;
		case "query10":
			Calendar oneYearAgo = Calendar.getInstance();
			oneYearAgo.add(Calendar.YEAR, -1);
			smartphones = smartphoneRepository.findTop10ByReleaseDateAfterOrderByPriceAsc(oneYearAgo);
			break;
		default:
			smartphones = smartphoneRepository.findAll();
		}
		
		model.addAttribute("smartphones", smartphones);

		if (smartphones!=null) {
			model.addAttribute("count", smartphones.size());			
		}
		else {
			model.addAttribute("count", count);				
		}
		return "index";
	}

	@RequestMapping(value = { "/findById" })
	public String findById(@RequestParam("id") SmartphoneModel e, Model model) {
		if (e!=null) {
			List<SmartphoneModel> smartphones = new ArrayList<SmartphoneModel>();
			smartphones.add(e);
			model.addAttribute("smartphones", smartphones);
		}
		return "index";
	}



	
	@RequestMapping("/fillSmartphoneList")
	@Transactional
	public String fillData(Model model) {

		DataFactory df = new DataFactory();
		
		BrandModel brand = null;
		
		for(int i=0;i<100;i++) {
			
				String[] brands = {"Samsung", "Apple", "Motorola", "Nokia", "Huawei", "Google", "LG", "ZTE", "OnePlus", "Xiaomi"};
				String brandName=df.getItem(brands);
				brand=brandRepository.findFirstByName(brandName);
				if (brand==null) {
					brand = new BrandModel(brandName);
				}
			
			Calendar releaseDate = Calendar.getInstance();
			Date minDate = df.getDate(2010,1,1);
			Date maxDate = new Date();
			releaseDate.setTime(df.getDateBetween(minDate, maxDate));
			
			double price = df.getNumberBetween(100, 1000);
			
			SmartphoneModel smartphoneModel = new SmartphoneModel(df.getRandomWord() + "Phone", releaseDate, price);
			smartphoneModel.setBrand(brand);
			smartphoneRepository.save(smartphoneModel);
		}
	
		return "forward:list";
	}

	@RequestMapping("/delete")
	public String deleteData(Model model, @RequestParam int id) {
		smartphoneRepository.deleteById(id);

		return "forward:list";
	}

	@ExceptionHandler(Exception.class)
	public String handleAllException(Exception ex) {

		return "error";

	}
}
