package br.com.aluta.screenmatch.principal;


import br.com.aluta.screenmatch.model.DadosSeries;
import br.com.aluta.screenmatch.model.DadosTemporadas;
import br.com.aluta.screenmatch.model.Serie;
import br.com.aluta.screenmatch.repository.SerieRepository;
import br.com.aluta.screenmatch.service.ConsumoApi;
import br.com.aluta.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=7e70f656";
    private final List<DadosSeries> dadosSeries = new ArrayList<>();

    private SerieRepository repositorio;

    public Principal (SerieRepository repositorio) {
        this.repositorio = repositorio;
    }
    public void exibeMenu() {

        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar Séries
                    2 - Buscar Episódios
                    3 - Listar séries buscadas
                    
                    0- Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    private void buscarSerieWeb() {
        DadosSeries dados = getDadosSerie();
        Serie serie = new Serie(dados);
        //dadosSeries.add(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSeries getDadosSerie() {
        System.out.println("Digite o nome da Série para a busca");

        var nomeSerie = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        return conversor.obterDados(json, DadosSeries.class);
    }

    private void buscarEpisodioPorSerie() {
        DadosSeries dadosSeries = getDadosSerie();
        List<DadosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSeries.totalTemporadas(); i++){
            var json = consumoApi.obterDados(ENDERECO + dadosSeries.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporadas dadosTemporadas = conversor.obterDados(json, DadosTemporadas.class);
            temporadas.add(dadosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }
    private void listarSeriesBuscadas(){

        List<Serie> series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
}





