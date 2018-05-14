package com.janbabs.bookshop;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.repository.BookRepository;
import com.janbabs.bookshop.service.BookService;
import com.janbabs.bookshop.service.CartService;
import com.janbabs.bookshop.service.UserService;
import com.janbabs.bookshop.transport.UserDTO;
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
    public CommandLineRunner demoData(BookRepository bookRepository, UserService userService, BookService bookService, CartService cartService) {
        return args -> {

            //Dodawanie przykładowych książek
            bookRepository.save(new Book("Musimy porozmawiać o Kevinie", "Lionel Shriver", 22,"Wydawnictwo Videograf",
                    "Nowe wydanie głośnej powieści, nagrodzonej Orange Prize i przetłumaczonej na ponad 20 języków. " +
                    "Oparty na książce film został nominowany do Złotej Palmy Cannes. " +
                            "Tilda Swinton w roli matki znakomicie oddaje dramat kobiety, która wychowuje syna o psychopatycznej osobowości." +
                            " Tytułowy Kevin – 14-letni chłopak z zamożnej amerykańskiej rodziny – bez szczególnego powodu zabił kilkanaście osób. " +
                            "Książka jest napisana w formie listów matki Kevina do ojca chłopca, w których próbuje ona odpowiedzieć sobie na pytanie: dlaczego?",
                    "https://emp-scs-uat.img-osdw.pl/img-p/1/kipwn/d576082e/std/2bc-452/106014632o.jpg"));
            bookRepository.save(new Book("Mdłości", "Jean – Paul Sartre", 29, "Wydawnictwo Zielona Sowa",
                    "\"Mdłości to pierwsza i kto wie, czy nie najciekawsza powieść Jeana Paula Sartre'a," +
                            " na pewno jedna z klasycznych powieści XX wieku (ukazała się u Gallimaida w 1938 r,)." +
                            " Z kilku poziomów tekstu, najważniejszy dotyczy odkrycia straszności istnienia jako podstawowej cechy człowieka. " +
                            "Straszny jest sam fakt istnienia, sama świadomość bytu, jeśli dojrzy to i odkryje jednostka. Zaś nieodłącznym, odczuciem," +
                            " jakie temu towarzyszy, są Mdłości. Antoine Roquentin po to właśnie postanawia pisać swój dziennik," +
                            " by utrwalić i ujrzeć wyraźniej tę nową świadomość bytu\", (z posłowia J. Trznadla)",
                    "https://media.merlin.pl/media/original/000/004/222/56baad3865735.jpg" ));
            bookRepository.save(new Book("Najgorszy człowiek na świecie", "Małgorzata Halber", 34, "Wydawnictwo Znak",
                    "Książka nagrodzona tytułem Książki Roku 2015 lubimyczytać.pl w kat. Literatura piękna. Krystyna czuje się wśród ludzi niepewnie." +
                            " Od zawsze tak było. Nie wie, jak i o czym z nimi rozmawiać, choć od lat pracuje w telewizji. Jednak umie się kamuflować." +
                            " Dla znajomych jest przebojową imprezowiczką, wygadaną i wesołą – bo wie, jak można dodać sobie odwagi. Wystarczy mały drink, może dwa." +
                            " Przed szkołą, po pracy, przed randką, na imprezie. Żeby dobrze się bawić. Żeby wreszcie się nie bać. Żeby cokolwiek poczuć. Przecież wszyscy tak robią." +
                            " Ale to lekarstwo działa tylko do czasu. Przed trzydziestką Krystyna decyduje się przestać. Na dobre. Wyleczyć ciało, ale przede wszystkim umysł." +
                            " Brawurowy debiut Małgorzaty Halber to szczera i bezkompromisowa relacja z krainy nałogu. " +
                            "To opowieść o szukaniu nowej drogi i o znajdowaniu odwagi, by wreszcie być sobą.",
                    "http://2.bp.blogspot.com/-f2AKlScism8/VM54RarjZII/AAAAAAAAANo/LgS7UKLU4T8/s1600/z17335920O%2C-Najgorszy-czlowiek-swiata--Malgorzata-Halber--fot-.jpg"));
            bookRepository.save(new Book("Proces", "Franz Kafka", 18, "Wydawnictwo GREG",
                    "Proces jest powieścią surrealistyczną Franza Kafki należącą do ścisłego kanonu literatury światowej. " +
                            "Opowiada o prokurencie bankowym Józefie K., który zostaje pewnego dnia aresztowany, pomimo że nie popełnił żadnego przestępstwa." +
                            " Pozornie areszt ma łagodny charakter; skazany może prowadzić normalne życie, będąc jedynie zobowiązanym do pozostania do dyspozycji sądu." +
                            " Jednak w rzeczywistości aresztowanie diametralnie przemienia egzystencję Józefa K. Odwracają się od niego przyjaciele i znajomi." +
                            " Mnożą się niezrozumiałe zdarzenia. Sąd nie odstępuje od wyroku, a liczne przesłuchania nie pozwalają bohaterowi udowodnić swojej niewinności." +
                            " Szuka on pomocy w rodzinie i poza nią: u żony sądowego, prawnika, malarza sądowego, innego oskarżonego, kapelana więziennego, ale wszystkie te próby okazują się bezskuteczne." +
                            " Proces zapisał się w historii literatury jako genialna metafora totalitaryzmu i jako opowieść o samotności człowieka we współczesnym świecie," +
                            " w którym wszechwładne państwo i biurokracja odbierają obywatelowi prawo do prywatności i wolności." +
                            " Fascynujący charakter powieści potęguje narracja utrzymana w konwencji absurdu i atmosfera kojarząca się z sennym koszmarem." +
                            " Lektura dla szkół średnich.",
                    "http://ecsmedia.pl/c/proces-b-iext39976625.jpg"));
            bookRepository.save(new Book("Rzeźnia numer pięć", "Kurt Vonnegut", 33, "Wydawnictwo Albatros",
                    "Kurt Vonnegut jest uznanym mistrzem czarnego humoru, ale w tej – może najważniejszej, najbardziej osobistej powieści swojego życia – nie musiał nic wymyślać." +
                            " Jako Amerykanin niemieckiego pochodzenia walczył w drugiej wojnie światowej z Niemcami i wzięty do niewoli przeżył straszliwe bombardowanie Drezna. " +
                            "Billy Pilgrim , amerykański żołnierz schwytany przez Niemców podczas ofensywy w Ardenach w 1944 roku, trafia do obozu jenieckiego. " +
                            "Następnie zostaje przeniesiony do Drezna, gdzie jest przetrzymywany w tytułowej rzeźni. " +
                            "Wraz z niewielką grupką więźniów i żołnierzy ukrywa się przed spadającymi bombami w podziemnej chłodni." +
                            " Jako jeden z nielicznych wychodzi cało z bombardowania miasta.",
                    "http://www.wydawnictwoalbatros.com/ksiazki/870/d_2481.jpg"));
            bookRepository.save(new Book("Mistrz i Małgorzata", "Michaił Bułhakow", 15, "Wydawnictwo Znak",
                    "\"Mistrz i Małgorzata\" to nie tylko najważniejsza powieść XX wieku, ale jedno z najbardziej tajemniczych dzieł światowej literatury," +
                            " pełne zagadek, symboli, niedopowiedzeń. Mogłoby się wydawać, że o arcydziele Bułhakowa powiedziano już wszystko. " +
                            "Po 50 latach od ukazania się książki Grzegorz Przebinda wraz z żoną Leokadią i synem Igorem udowadniają swym nowym tłumaczeniem, " +
                            "że to nieprawda. Odkrywają kolejne warstwy fascynującej historii, rozwiewają wątpliwości i polemizują z poprzednimi przekładami. " +
                            "Oferują nowy klucz do odczytania tej wielkiej księgi, przybliżając ją współczesnemu czytelnikowi. " +
                            "To prawdopodobnie pierwszy przypadek, gdy przypisy czyta się z równą fascynacją co tekst samej powieści.",
                    "https://woblink.com/storable/pub_photos/949167-mistrz-i-malgorzata.jpg"));
            bookRepository.save(new Book("Nieznośna lekkość bytu", "Milan Kundera", 39, "Wydawnictwo W.A.B",
                    "Arcydzieło literatury światowej. Najsłynniejsza powieść Milana Kundery wydana po raz pierwszy w 1984 r.," +
                            " z głośną adaptacją filmową z Danielem Day-Lewisem i Juliette Binoche. " +
                            "Wydana po raz pierwszy w 1984 roku „Nieznośna lekkość bytu” jest najsłynniejszą powieścią Milana Kundery oraz swego rodzaju ukoronowaniem jego twórczości. " +
                            "Zbiegają się w niej wszystkie tematy, do których pisarz w swojej twórczości nieustannie powraca. Splot życiorysów kilku postaci (i psa)," +
                            " które na różne sposoby dochodzą do wniosku, że jedynym, co zostaje im dane jest właśnie tytułowa lekkość. " +
                            "Głównym problemem, który interesuje Kunderę w tej książce jest kwestia przypadku jako czynnika, który determinuje ludzkie losy. " +
                            "Miłość jest przypadkiem. To przypadek decyduje, że zakochujemy się właśnie w tej, a nie w innej osobie. " +
                            "Przypadek decyduje również o tym, czym zajmujemy się przez całe życie, kim jesteśmy, z jakimi ludźmi się stykamy." +
                            " W epoce totalitaryzmu zaś czynnik losowy jest tym bardziej paradoksalny," +
                            " im bardziej wszechwładny ustrój stara się go wyeliminować i narzucić obywatelom sztuczne ograniczenia. " +
                            "Pomiędzy tymi dwoma żywiołami – prawdziwym i wymyślonym – poszczególne biografie wirują jak folia na wietrze. " +
                            "Sposób w jaki napisana jest „Nieznośna lekkość bytu” doskonale koresponduje z tematem – napisana jest lekko, pozornie chaotycznie." +
                            " Wypełniona stwierdzeniami o charakterze ogólnym, których nie można traktować inaczej niż jako stwierdzenia właściwie nieważne...",
                    "https://esensja.pl/obrazki/okladkiks/232136_nieznosna-lekkosc-bytu_2014_500.jpg"));
            bookRepository.save(new Book("Dom z liści", "Mark Z. Danielewski", 65, "Wydawnictwo Mag",
                    "Johnny Wagabunda, były pracownik salonu tatuażu w Los Angeles, znajduje notes Zampano, starszego pana i odludka, który zmarł w swoim zagraconym mieszkaniu." +
                            " Notes zawiera opatrzoną licznymi przypisami historię „Relacji Navidsona”. " +
                            "Fotoreporter Will Navidson wprowadził się z rodziną do nowego domu – dalsze wydarzenia zostały zarejestrowane na taśmach filmowych oraz w postaci wywiadów." +
                            " Od tamtej pory Navidsonowie stali się sławni, a Zampano – robiąc notatki na luźnych kartkach papieru, " +
                            "serwetkach i w gęsto zapisanych notatnikach – skompilował wyczerpującą pracę na temat wydarzeń w domu przy Ash Tree Lane. " +
                            "Jednakże ani Wagabunda, ani nikt z jego znajomych nigdy nie słyszeli o „Relacji Navidsona”. Teraz zaś im więcej Johnny czyta o domu Navidsonów," +
                            " tym bardziej zaczyna się bać i popadać w paranoję. " +
                            "Najgorsze jest to, że nie może potraktować znalezionych zapisków jako zwykłych majaczeń starego wariata. Zaczyna zauważać zachodzące w otoczeniu zmiany...",
                    "http://www.mag.com.pl/upload/horror/dom_z_lisci.jpg"));
            bookRepository.save(new Book("Kronika ptaka nakręcacza", "Haruki Murakami", 49, "Wydawnictwo MUZA S.A",
                    "Życie Toru Okady po stracie pracy jest monotonne i pozbawione ambicji. Żona zarabia wystarczająco dużo na utrzymanie ich obojga, a Toru prowadzi dom. " +
                            "Akcja \"Kroniki ptaka nakręcacza\", podobnie jak w większości książek Haruki Murakami jest bardzo skomplikowana," +
                            " ale nie ona jest najważniejsza. Jeden z recenzentów streścił ją tak: Toru Okada traci najpierw pracę, potem kota, potem żonę a w końcu - rozum.",
                    "http://images.nexto.pl/upload/sklep/muza/ebook/kronika_ptaka_nakrecacza-haruki_murakami-muza/public/kronika_ptaka_nakrecacza-muza-ebook-cov.jpg"));
            bookRepository.save(new Book("Nie kończąca się historia", "Michael Ende", 40, "Wydawnictwo Znak",
                    "Bastian Baltazar Buks wchodzi przypadkiem do antykwariatu," +
                            " gdzie znajduje starą zakurzoną księgę zatytułowaną \"Nie kończąca się historia\"." +
                            " Czytając ją, przenosi się do cudownej krainy fantazji pożeranej przez tajemniczą Nicość. " +
                            "Dzieje się tak dlatego, że ludzie nie wierzą już w marzenia. Bastian musi z czytelnika zamienić się w prawdziwego bohatera tej opowieści." +
                            " Tylko on może uratować Cesarzową oraz powstrzymać zagładę całej Fantazjany…",
                    "http://ecsmedia.pl/c/nie-konczaca-sie-historia-b-iext35246351.jpg"));

            //Dodawanie przykładowych użytkowników
            userService.save(new UserDTO("admin", "password", "", "", "", userType.ADMIN));
            userService.save(new UserDTO("user", "password", "Imię", "Nawzisko", "email@email", userType.USER));
        };
    }
}
