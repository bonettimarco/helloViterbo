/*
 * Copyright (c) 2015 SK C&C Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of SK C&C.
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance wih the terms of the license agreement you entered into
 * with SK C&C.
 */
package com.example.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>{
	List<Livro> findByTitulo(String titulo);
}
