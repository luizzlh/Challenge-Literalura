package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.models.Autor;
import br.com.alura.screenmatch.models.DadosAutor;
import br.com.alura.screenmatch.models.DadosLivro;
import br.com.alura.screenmatch.models.DadosResposta;
import br.com.alura.screenmatch.repository.AutorRepository;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private AutorRepository repositorio;


    public Principal(AutorRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 0) {
            var menu = """
                    1 - buscar livro pelo título
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroWeb();
                    break;
                case 2:
                    buscarLivrosRegistrados();
                    break;
//                case 3:
//                    listarSeriesBuscadas();
//                    break;
//                case 4:
//                    buscarSeriePorTitulo();
//                    break;
//                case 5:
//                    buscarSeriesPorAtor();
//                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroWeb() {
        DadosLivro dados = getDadosLivro();
        DadosAutor dadosAutor = dados.autor().getFirst();
        Autor autor = new Autor(dadosAutor);
        autor.setLivros(dados);
        repositorio.save(autor);
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Insira o nome do livro que você deseja procurar");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        DadosResposta dadosResposta = conversor.obterDados(json, DadosResposta.class);
        return dadosResposta.results().get(0);
    }

    private void buscarLivrosRegistrados() {
    }

//    private void buscarEpisodioPorSerie(){
//        listarSeriesBuscadas();
//        System.out.print("Escolha uma Série pelo nome: ");
//        var nomeSerie = leitura.nextLine();
//        Optional<Autor> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
//
//        if(serie.isPresent()){
//            var serieEncontrada = serie.get();
//            List<DadosTemporada> temporadas = new ArrayList<>();
//
//            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
//                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo()
//                        .replace(" ", "+") + "&season=" + i + API_KEY);
//                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
//                temporadas.add(dadosTemporada);
//            }
//            temporadas.forEach(System.out::println);
//
//            List<Livro> livros = temporadas.stream()
//                    .flatMap(dadosTemporada -> dadosTemporada.episodios().stream()
//                            .map(dadosEpisodio -> new Livro(dadosTemporada.numero(), dadosEpisodio)))
//                    .collect(Collectors.toList());
//            serieEncontrada.setEpisodios(livros);
//            repositorio.save(serieEncontrada);
//        }else{
//            System.out.println("Autor não encontrada!");
//        }
//    }
//
//    private void listarSeriesBuscadas(){
//        series = repositorio.findAll();
//        series.stream()
//                        .sorted(Comparator.comparing(Autor::getGenero))
//                                .forEach(System.out::println);
//        dadosSeries.forEach(System.out::println);
//    }
//
//    private void buscarSeriePorTitulo() {
//        System.out.print("Escolha uma Série pelo nome: ");
//        var nomeSerie = leitura.nextLine();
//        serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);
//
//        if(serieBusca.isPresent()){
//            System.out.println("Dados da série: " + serieBusca.get());
//        }else{
//            System.out.println("Série não encontrada!");
//        }
//    }
//
//    private void buscarSeriesPorAtor() {
//        System.out.println("Qual o nome para busca?");
//        var nomeAtor = leitura.nextLine();
//        System.out.println("Avaliações a partir de que valor?");
//        var avaliacao = leitura.nextDouble();
//        List<Autor> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
//        System.out.println("Séries em que " + nomeAtor + " trabalhou: ");
//        seriesEncontradas.forEach(autor -> System.out.println(autor.getTitulo() + " avaliação: " + autor.getAvaliacao()));
//    }
//
//    private void buscarTop5Series() {
//        List<Autor> autorTop = repositorio.findTop5ByOrderByAvaliacaoDesc();
//        autorTop.forEach(autor -> System.out.println(autor.getTitulo() + " avaliação: " + autor.getAvaliacao()));
//    }
//
//    private void buscarSeriesPorCategoria() {
//        System.out.println("Deseja buscar series de qual categoria/gênero?");
//        var nomeGenero = leitura.nextLine();
//        Categoria categoria = Categoria.fromPortugues(nomeGenero);
//        List<Autor> seriesPorCategoria = repositorio.findByGenero(categoria);
//        seriesPorCategoria.forEach(System.out::println);
//    }
//
//    private void buscarSeriePorTotalDeTemporadas() {
//        System.out.print("Digite o total de temporadas: ");
//        var totalTemporadas = leitura.nextInt();
//        leitura.nextLine();
//        System.out.print("Digite a avaliação: ");
//        var avaliacao = leitura.nextDouble();
//        leitura.nextLine();
//        List<Autor> seriesPorTotalTemporada = repositorio
//                .seriesPorTemporadaEAvaliacao(totalTemporadas, avaliacao);
//        seriesPorTotalTemporada.forEach(autor -> System.out.println(autor.getTitulo() + " - avaliação: " + autor.getAvaliacao()));
//    }
//
//    private void buscarEpisodioPorTrecho(){
//        System.out.print("Digite o trecho do episódio: ");
//        var trechoEpisodio = leitura.nextLine();
//        List<Livro> episodiosEncontrados = repositorio.episodiosPorTrecho(trechoEpisodio);
//        episodiosEncontrados.forEach(livro ->
//                System.out.printf("Série: %s Temporada: %d - Episódio: %d - %s \n",
//                        livro.getSerie().getTitulo(), livro.getTemporada(),
//                        livro.getNumero(), livro.getTitulo()));
//    }
//
//    private void topEpisodiosPorSerie(){
//        buscarSeriePorTitulo();
//
//        if(serieBusca.isPresent()){
//            Autor autor = serieBusca.get();
//            List<Livro> topLivros = repositorio.topEpisodiosPorSerie(autor);
//            topLivros.forEach(livro ->
//                    System.out.printf("Série: %s Temporada: %d - Episódio: %d - %s Avaliação: %s\n",
//                            livro.getSerie().getTitulo(), livro.getTemporada(),
//                            livro.getNumero(), livro.getTitulo(), livro.getAvaliacao()));
//        }
//    }
//
//    private void buscarEpisodiosDepoisDeUmaData(){
//        buscarSeriePorTitulo();
//
//        if(serieBusca.isPresent()){
//            System.out.print("Digite o ano limite de lançamento: ");
//            var anoLancamento = leitura.nextInt();
//            leitura.nextLine();
//            Autor autor = serieBusca.get();
//
//            List<Livro> episodiosAno = repositorio.episodiosPorSerieEAno(autor, anoLancamento);
//            episodiosAno.forEach(System.out::println);
//
//        }
//    }
}