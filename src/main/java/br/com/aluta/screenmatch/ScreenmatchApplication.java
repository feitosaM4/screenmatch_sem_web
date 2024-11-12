package br.com.aluta.screenmatch;

import br.com.aluta.screenmatch.model.DadosEpisodio;
import br.com.aluta.screenmatch.model.DadosSeries;
import br.com.aluta.screenmatch.model.DadosTemporadas;
import br.com.aluta.screenmatch.principal.Principal;
import br.com.aluta.screenmatch.service.ConsumoApi;
import br.com.aluta.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal =new Principal();
		principal.exibeMenu();


	}
}



