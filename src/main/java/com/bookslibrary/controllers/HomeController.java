package com.bookslibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookslibrary.db.BookDAO;
import com.bookslibrary.model.Book;

@Controller
public class HomeController {
	
	@Autowired
	private BookDAO bookDAO;

	@GetMapping("/")
	public String homecontroller(Model model)
	{
		List<Book> bookL = bookDAO.findAll();
		int nrOfBooks = bookL.size();
		double sum = 0;
		int pagesSum = 0;
		for(Book b : bookL) {
			sum+=b.getPrice();
			pagesSum+=b.getPages();
		}
		double averagePrice = sum / nrOfBooks;
		
		model.addAttribute("nrOfBooks", nrOfBooks);
		model.addAttribute("sum",sum );
		model.addAttribute("pagesSum",pagesSum );
		model.addAttribute("averagePrice",averagePrice );
		
		
		return "home";		
	}
}
