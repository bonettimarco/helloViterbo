/*
 * Copyright (c) 2015 SK C&C Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of SK C&C.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with SK LivroC&C.
 */
package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Livro;
import com.example.repo.LivroRepository;

@RestController
public class LivroController {
	@Autowired
	LivroRepository repository;
	
	@RequestMapping(value="/livros", method=RequestMethod.GET)
	@ResponseBody
	public Iterable<Livro> getLivros(){
		return repository.findAll();
	}
	@RequestMapping(value="/livros/{id}", method=RequestMethod.GET)
	public Livro getTitulo(@PathVariable long id){
		return repository.findOne(id);
	}
	
	@RequestMapping(value="/livros", method=RequestMethod.POST)
	public void addLivro(@RequestBody Livro livro){
		repository.save(livro);
	}
}
