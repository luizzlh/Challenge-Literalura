package br.com.alura.screenmatch.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer anoDeFalecimento;
    private Integer anoDeNascimento;
    private String nome;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dadosAutor) {
        this.anoDeFalecimento = dadosAutor.anoFalecimento();
        this.anoDeNascimento = dadosAutor.anoNascimento();
        this.nome = dadosAutor.nome();
    }

    public void setLivros(DadosLivro dadosLivro){
        List<Livro> livros1 = new ArrayList<>();
        livros1.add(new Livro(dadosLivro));
        this.livros = livros1;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(Integer anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public Integer getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(Integer anoDeNascimento) {
        this.anoDeNascimento = anoDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", anoDeFalecimento=" + anoDeFalecimento +
                ", anoDeNascimento=" + anoDeNascimento +
                ", nome='" + nome + '\'' +
                '}';
    }
}