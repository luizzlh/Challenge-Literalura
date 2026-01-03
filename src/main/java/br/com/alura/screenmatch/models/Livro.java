package br.com.alura.screenmatch.models;

import jakarta.persistence.*;

@Entity
@Table(name="livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idioma;
    private Double downloads;
    private String titulo;

    public Livro(Long id, String idioma, Double downloads, String titulo) {
        this.id = id;
        this.idioma = idioma;
        this.downloads = downloads;
        this.titulo = titulo;
    }

    public Livro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getDownloads() {
        return downloads;
    }

    public void setDownloads(Double downloads) {
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
