package com.bookapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.SessionFactoryServiceInitiator;
import org.hibernate.service.spi.SessionFactoryServiceRegistryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookapp.model.Book;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/fill")
	public String fill() {
		
		Configuration con = new Configuration().configure().addAnnotatedClass(Book.class);
		//ServiceRegistry reg = new ServiceRegistry
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Book b = new Book();
		//b.setIsbn(2);
		b.setTitle("ff");
		b.setAuthor("jan");
		b.setPages(10);
		b.setPrice(34d);
		b.setDescription("fdfd");
		
		Transaction tx = session.beginTransaction();
		session.save(b);
		tx.commit();
		
		
		

		return "fill";
	}
}
