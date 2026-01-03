package br.com.alura.screenmatch.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate anoDeFalecimento;
    private LocalDate anoDeNascimento;
    private String nome;

    public Autor(){}

    public Autor(Long id, LocalDate anoDeFalecimento, LocalDate anoDeNascimento, String nome) {
        this.id = id;
        this.anoDeFalecimento = anoDeFalecimento;
        this.anoDeNascimento = anoDeNascimento;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAnoDeFalecimento() {
        return anoDeFalecimento;
    }

    public void setAnoDeFalecimento(LocalDate anoDeFalecimento) {
        this.anoDeFalecimento = anoDeFalecimento;
    }

    public LocalDate getAnoDeNascimento() {
        return anoDeNascimento;
    }

    public void setAnoDeNascimento(LocalDate anoDeNascimento) {
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