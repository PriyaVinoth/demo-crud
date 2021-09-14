package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/home")
	public String viewHomePage(Model model) {
		List<Product> listProducts = productService.listAll();
		model.addAttribute("listProducts", listProducts);
		return "index";
	}

	@RequestMapping("/new")
	public String showNewProduct(Model model)
	{
		Product product = new Product();
		model.addAttribute("product",product);
		return "new_product";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product)
	{
		productService.save(product);
		
		return "redirect:/home";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProduct(@PathVariable(name = "id") Long id)
	{
		ModelAndView mav=new ModelAndView("edit_product");
		
		Product product = productService.get(id);
		mav.addObject("product",product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id)
	{
		productService.delete(id);
		
		return "redirect:/home";
	}
}
