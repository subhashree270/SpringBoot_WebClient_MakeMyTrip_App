package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.bindings.Passenger;
import in.ashokit.bindings.Ticket;
import in.ashokit.service.MakeMyTripService;
import reactor.core.publisher.Mono;

@Controller
public class MakeMyTripCotroller {
	
	@Autowired
	private MakeMyTripService service;
	
	@PostMapping("/ticket")
	public String bookingTicket (@ModelAttribute("p") Passenger p,Model model) {
		Mono<Ticket> bookTicket = service.bookTicket(p);
		model.addAttribute("ticket",bookTicket);
		model.addAttribute("msg", "Ticket is booked");
		model.addAttribute("ticketNum", "Find Your ticket number below :");
		return "bookTicket";
	}
	@GetMapping("/book-ticket")
	public String bookTicket(Model model) {
		model.addAttribute("p",new Passenger());
		model.addAttribute("ticket", new Ticket());
		return "bookTicket";
	}
	@GetMapping("/")
	public String getAllTickets(Model model) {
		Mono<Ticket[]> allTickets = service.getAllTickets();
		model.addAttribute("tickets",allTickets);
		return "index";
	}

}
