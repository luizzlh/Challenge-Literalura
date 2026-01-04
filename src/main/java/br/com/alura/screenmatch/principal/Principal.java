package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.models.*;
import br.com.alura.screenmatch.repository.AutorRepository;
import br.com.alura.screenmatch.repository.LivroRepository;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private AutorRepository autorRepository;
    private LivroRepository livroRepository;


    public Principal(AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
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
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEmUmDeterminadoAno();
                    break;
                case 5:
                    listarLivrosEmUmDeterminadoIdioma();
                    break;
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

        autor.setLivros(dados, autor);
        autorRepository.save(autor);
        System.out.println("----- LIVRO -----");
        System.out.print("Título: " + dados.titulo() + "\n" +
                "Autor: " + autor.getNome() + "\n" +
                "Idioma: " + dados.idioma().getFirst() + "\n" +
                "Número de downloads: " + dados.downloads() + "\n");
        System.out.println("-----------------");
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Insira o nome do livro que você deseja procurar");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        DadosResposta dadosResposta = conversor.obterDados(json, DadosResposta.class);
        return dadosResposta.results().get(0);
    }

    public void imprimeLivrosFormatado(List<Livro> livros){
        livros.forEach(livro ->
                System.out.println("----- LIVRO -----" + "\n" +
                        "Título: " + livro.getTitulo() + "\n" +
                        "Autor: " + livro.getAutor().getNome() + "\n" +
                        "Idioma: " + livro.getIdioma() + "\n" +
                        "Número de downloads: " + livro.getDownloads() + "\n"+
                        "-----------------" + "\n"));
    }

    public void imprimeAutoresFormatado(List<Autor> autores){
        autores.forEach(autor -> System.out.println("----- LIVRO -----" + "\n" +
                "Autor: " + autor.getNome() + "\n" +
                "Ano de nascimento: " + autor.getAnoDeNascimento() + "\n" +
                "Ano de falecimento: " + autor.getAnoDeFalecimento() + "\n" +
                "Livros: " + autor.getLivros().stream().map(livro -> livro.getTitulo()).toList() + "\n"+
                "-----------------" + "\n"));
    }

    public void listarLivrosRegistrados() {
        List<Livro> livros = autorRepository.findAll()
                        .stream().flatMap(autor -> autor.getLivros().stream())
                        .toList();
        imprimeLivrosFormatado(livros);
    }

    public void listarAutoresRegistrados(){
        List<Autor> autores = autorRepository.findAll();
        imprimeAutoresFormatado(autores);
    }

    public void listarAutoresVivosEmUmDeterminadoAno(){
        System.out.println("Insira o ano em que deseja pesquisar");
        var ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = autorRepository.findAllByAnoDeFalecimentoGreaterThanAndAnoDeNascimentoLessThanEqual(ano, ano);
        imprimeAutoresFormatado(autores);
    }

    public void listarLivrosEmUmDeterminadoIdioma(){
        System.out.println("Insira o idioma para realizar a busca: ");
        System.out.println("""
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var idioma = leitura.nextLine();

        switch (idioma){
            case "es":
                listarLivrosEmEspanhol();
                break;
            case "en":
                listarLivrosEmIngles();
                break;
            case "fr":
                listarLivrosEmFrances();
                break;
            case "pt":
                listarLivrosEmPortugues();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }

    public void listarLivrosEmEspanhol(){
        List<String> idioma = new ArrayList<>();
        idioma.add("es");
        List<Livro> livros = livroRepository.findLivroByIdiomaEquals(idioma);
        if (livros.isEmpty()){
            System.out.println("Não existe livros neste idioma!");
        }else{
            imprimeLivrosFormatado(livros);
        }
    }

    public void listarLivrosEmIngles(){
        List<String> idioma = new ArrayList<>();
        idioma.add("en");
        List<Livro> livros = livroRepository.findLivroByIdiomaEquals(idioma);
        if (livros.isEmpty()){
            System.out.println("Não existe livros neste idioma!");
        }else{
            imprimeLivrosFormatado(livros);
        }
    }

    public void listarLivrosEmFrances(){
        List<String> idioma = new ArrayList<>();
        idioma.add("fr");
        List<Livro> livros = livroRepository.findLivroByIdiomaEquals(idioma);
        imprimeLivrosFormatado(livros);
    }

    public void listarLivrosEmPortugues(){
        List<String> idioma = new ArrayList<>();
        idioma.add("pt");
        List<Livro> livros = livroRepository.findLivroByIdiomaEquals(idioma);
        imprimeLivrosFormatado(livros);
    }
}