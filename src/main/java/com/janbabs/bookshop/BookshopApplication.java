package com.janbabs.bookshop;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.repository.BookRepository;
import com.janbabs.bookshop.repository.UserRepository;
import com.janbabs.bookshop.service.UserServices;
import com.janbabs.bookshop.transport.UserTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookshopApplication.class, args);

	}

	@Bean
	public CommandLineRunner demoData(BookRepository bookRepository, UserServices userServices) {
		return args -> {
			bookRepository.save(new Book("Eragon", "Christian Paolini",
					40, "Wydawnictwo Sowa",
					"Eragon and the fledgling dragon must navigate the dangerous " +
					"terrain and dark enemies of an empire ruled by a king whose evil knows no bounds. Can Eragon take up the mantle of the legendary Dragon Riders?\n" +
					"\n" +
					"When Eragon finds a polished blue stone in the forest, he thinks it is the lucky discovery of a poor farm boy;" +
					" perhaps it will buy his family meat for the winter. But when the stone brings a dragon hatchling," +
					" Eragon realizes he has stumbled upon a legacy nearly as old as the Empire itself. "
					, "https://i5.walmartimages.com/asr/49c46557-d41b-4e55-963e-08ac58cae01a_1.83ed09b0c126ad3ebdbd472ff30b2a4d.jpeg?odnHeight=450&odnWidth=450&odnBg=FFFFFF"));
			bookRepository.save(new Book("Język C++ Szkoła Programowania", "Stephen Prata",
					29, "Wydawnictwo Helion",
					"Język C++. Szkoła programowania to starannie sprawdzony, sumiennie przygotowany i kompletny przewodnik po programowaniu w C++," +
							" przeznaczony dla programistów. Ten klasyczny już materiał pomocniczy dla wykładowców uczy zasad programowania, " +
							"począwszy od kodu strukturalnego i projektowania wedle metody dekompozycji i analizy, przez klasy, dziedziczenie, " +
							"szablony i wyjątki, po wyrażenia lambda, inteligentne wskaźniki i semantykę przeniesienia. " +
							"Stephen Prata jako autor i wykładowca proponuje przemyślane, przejrzyste i wnikliwe wprowadzenie do języka C++." +
							" Wraz z mechanizmami samego języka omawia fundamentalne pojęcia i techniki programowania."
					, "https://static01.helion.com.pl/global/okladki/326x466/962adc9dd7542c8e8cf0257bfeae03ab,cpprim.jpg"));
			bookRepository.save(new Book("Malowany Człowiek", "Peter V. Brett",
					79, "Wydawnictwo Fabryka Słów",
					"Zaszczuta i zdziesiątkowana ludzkość przeklina noc. " +
							"Z każdym zmierzchem, w oparach mgły, nadchodzą opętane żądzą mordu bestie. " +
							"Przerażeni ludzie chronią się za magicznymi runami. " +
							"Usiłują wymodlić dla siebie i najbliższych kolejny dzień życia. Rzeź ustaje bladym świtem, gdy światło zapędza demony z powrotem w Otchłań." +
							" Rosną odległości między pustoszejącymi osadami. Wydaje się, że nikt ani nic..."
					, "http://ecsmedia.pl/c/malowany-czlowiek-ksiega-1-w-iext39340767.jpg"));
			userServices.save(new UserTO("admin", "password", "imie", "nazwisko", "email@email", userType.ADMIN));
			userServices.save(new UserTO("user	", "password", "imie1", "nazwisko2", "email1@email", userType.USER));
		};
	}
}
