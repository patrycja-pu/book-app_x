package com.bookslibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookslibrary.db.BookDAO;
import com.bookslibrary.model.Book;

@Controller
public class BookController {

	@Autowired
	private BookDAO bookDAO;
	
	@GetMapping("/show")
	public String show(Model model) {
		List<Book> book_list = bookDAO.findAll();
		model.addAttribute("bookL", book_list);
		return "show";
	}
	
	@GetMapping("/fill")
	public String fill() {
		return "fill";
	}
	
	@PostMapping("addbook")
	public String addbook(Book book) {
		bookDAO.save(book);
		return "redirect:/show";
	}
	
	@GetMapping("/bookedit/{isbn}")
	public String edit(@PathVariable("isbn") int isbn, Model model) {
		Book book = bookDAO.findById(isbn).orElse(null);
		if(book == null) {
			return "redirect:/show";
		}
		
		model.addAttribute("book", book);
		return "edit";		
	}
	
	@PostMapping("/modif")
	public String modif(Book book) {
		bookDAO.save(book);
		return "redirect:/show";
	}
	
	@GetMapping("/bookremove/{isbn}")
	public String remove(@PathVariable("isbn") int isbn) {
		bookDAO.deleteById(isbn);
		return "redirect:/show";
	}
}
