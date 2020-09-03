package com.api.socioTorcedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.socioTorcedor.model.SocioTorcedor;
import com.api.socioTorcedor.model.to.SocioTorcedorTO;
import com.api.socioTorcedor.service.SocioTorcedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"Sócio Torcedor"})
@RestController
@RequestMapping("/socioTorcedor")
public class SocioTorcedorController {

	@Autowired
	private SocioTorcedorService socioTorcedorService;
	
	@ApiOperation(value = "Busca todos os Sócios Torcedores")
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Page<SocioTorcedor>> buscaTodosSociosTorcedores(Pageable pageable) {
		
		Page<SocioTorcedor> sociosTorcedores = socioTorcedorService.buscaTodosSociosTorcedores(pageable);
		
		return new ResponseEntity<Page<SocioTorcedor>>(sociosTorcedores, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Busca Sócio Torcedor por id")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SocioTorcedor> buscaSocioTorcedorPorId(@PathVariable("id") Long id) {
		
		SocioTorcedor socioTorcedor = socioTorcedorService.buscarSocioTorcedorPorId(id);
		
		return new ResponseEntity<SocioTorcedor>(socioTorcedor, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Busca Sócio Torcedor por email")
	@GetMapping(value = "email/{email}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SocioTorcedor> buscaSocioTorcedorPorEmail(@PathVariable("email") String email) {
		
		SocioTorcedor socioTorcedor = socioTorcedorService.buscarSocioTorcedorPorEmail(email);
		
		return new ResponseEntity<SocioTorcedor>(socioTorcedor, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Cria novo Sócio Torcedor")
	@PostMapping(value = "")
	public ResponseEntity<SocioTorcedor> criaSocioTorcedor(@RequestBody SocioTorcedorTO socioTorcedorTO) {
		
		SocioTorcedor socioTorcedor = socioTorcedorService.criaSocioTorcedor(socioTorcedorTO);
		
		return new ResponseEntity<SocioTorcedor>(socioTorcedor, HttpStatus.CREATED);
		
	}
	
	@ApiOperation(value = "Atualiza Sócio Torcedor")
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<SocioTorcedor> atualizaSocioTorcedor(@PathVariable("id") Long id, @RequestBody SocioTorcedorTO socioTorcedorTO) {
		
		SocioTorcedor socioTorcedor = socioTorcedorService.atualizaSocioTorcedor(id, socioTorcedorTO);
		
		return new ResponseEntity<SocioTorcedor>(socioTorcedor, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "Deleta Sócio Torcedor")
	@DeleteMapping("/{id}")
	public ResponseEntity<SocioTorcedor> deletaSocioTorcedor(@PathVariable("id") Long id) {
		
		socioTorcedorService.deletaSocioTorcedor(id);
		
		return new ResponseEntity<SocioTorcedor>(HttpStatus.NO_CONTENT);
		
	}
	
}
