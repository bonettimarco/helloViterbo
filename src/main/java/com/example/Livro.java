//implementação JPA

package com.example

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import java.io.*;

  @Entity
  public class Livro {
	  @Id @GeneratedValue
	  private Long id;
	  @Column(nullable=false)
	  private String titulo;
	  private String autor;
	  private Float preco;
	  @Column(length=2000)
	  private String descricao;
	  private Integer numPaginas;
	  
	  public Livro() {}
	  
	  public Livro(String titulo, String autor, Float preco, String descricao, Integer numPaginas){
		  this.titulo = titulo;
		  this.autor = autor;
		  this.preco = preco;
		  this.descricao = descricao;
		  this.numPaginas = numPaginas;
	  }
	  
  	  public void setTitulo(String titulo) {
		  this.titulo = titulo;
	  }
	  public void setAutor(String autor) {
		  this.autor = autor;
	  }
	  public void setPreco(Float preco) {
		  this.preco = preco;
	  }
	  public void setDescricao(String descricao) {
		  this.descricao = descricao;
	  }
	  public void setNumPaginas(Integer numPaginas) {
		  this.numPaginas = numPaginas;
	  }

	  public String getTitulo() {
		  return this.titulo;
	  }
	  
	  @Override
	  public String toString() {
		  return "Um livro de " + this.autor;
	  }
	  
  }

