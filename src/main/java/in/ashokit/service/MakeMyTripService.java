package in.ashokit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import in.ashokit.bindings.Passenger;
import in.ashokit.bindings.Ticket;
import reactor.core.publisher.Mono;

@Service
public class MakeMyTripService {
	
	public Mono<Ticket> bookTicket(Passenger p){
		String apiUrl="http://localhost:9090/ticket";
		WebClient webclient=WebClient.create();
		Mono<Ticket> bodyToMono = webclient.post()
		         .uri(apiUrl)
		         .body(BodyInserters.fromValue(p))
		         .retrieve()
		         .bodyToMono(Ticket.class);
		
		return bodyToMono;
	}
	
	public Mono<Ticket[]> getAllTickets(){
		String apiUrl="http://localhost:9090/tickets";
		WebClient webclient = WebClient.create();
		Mono<Ticket[]> bodyToMono = webclient.get()
		         .uri(apiUrl)
		         .retrieve()
		         .bodyToMono(Ticket[].class);
		return bodyToMono;
	}

}
