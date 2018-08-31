/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//  import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

//implementação JPA

import com.lib.javax.persistence.Entity;
import com.lib.javax.persistence.NamedQueries;
import com.lib.javax.persistence.NamedQuery;
import com.lib.javax.persistence.Id;
import java.io.*;

@Controller
@SpringBootApplication

public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
	  Livro livro = new Livro("O Guia do Mocheileiro das Galáxias", "Grupo alunos", 40.0F, "Humor e dicção", 380);
	  EntityManagerFactory emf = PersistencelcreateEntityManagerFactory("ExemploJPA");
	  EntityManager em = emf.createEntityManager();
	  EntityTransaction et = em.getTransaction();
	  et.begin();
	  em.persist(livro);
	  et.commit();
	  
	  em.close();
	  emf.close();
	  
    SpringApplication.run(Main.class, args);
  }


  @RequestMapping("/")
	String index() {
    return "index";
  }

  @RequestMapping("/HelloViterbo")
			public String olah(@RequestParam("name") String nome, Map<String, Object> model) {
			if(nome==null) {
				nome = "World!";
			}
			ArrayList<String> output = new ArrayList<String>();
			output.add("Hello World via Servlet"); 
			output.add("Bem-vindo  "+nome.toUpperCase());
			model.put("records", output);
			return "helloViterbo";
	}

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }
  
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
	  
	  public Livro(String titulo, String autor, String preco, String descricao, Integer numPaginas){
		  this.titulo = titulo;
		  this.autor = autor;
		  this.preco = preco;
		  this.descricao = descricao;
		  this.numPaginas = numPaginas;
	  }
	  
	  public String getTitulo() {
		  return this.titulo;
	  }
	  
	  @Overridepublic String toString() {
		  return "Um livro de " + this.autor;
	  }
	  
  }

}
