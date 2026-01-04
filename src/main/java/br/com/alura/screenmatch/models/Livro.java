package br.com.alura.screenmatch.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<String> idioma;
    private Integer downloads;
    private String titulo;
    @ManyToOne
    private Autor autor;

    public Livro(DadosLivro dadosLivro) {
        this.idioma = dadosLivro.idioma();
        this.downloads = dadosLivro.downloads();
        this.titulo = dadosLivro.titulo();
    }

    public void setAutor(Autor autor){
        this.autor = autor;
    }

    public Autor getAutor() {
        return autor;
    }

    public Livro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", idioma='" + idioma + '\'' +
                ", downloads=" + downloads +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
