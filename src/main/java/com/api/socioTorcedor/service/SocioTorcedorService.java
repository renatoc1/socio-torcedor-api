package com.api.socioTorcedor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.api.socioTorcedor.exception.SocioTorcedorException;
import com.api.socioTorcedor.model.SocioTorcedor;
import com.api.socioTorcedor.model.to.SocioTorcedorTO;
import com.api.socioTorcedor.repository.SocioTorcedorRepository;

@Service
public class SocioTorcedorService {

	@Autowired
	private SocioTorcedorRepository socioTorcedorRepository;
	
	public Page<SocioTorcedor> buscaTodosSociosTorcedores(Pageable pageable) {
		Page<SocioTorcedor> sociosTorcedores = socioTorcedorRepository.findAll(pageable);
		if (null == sociosTorcedores || !sociosTorcedores.hasContent()) {
			throw new SocioTorcedorException("Socio Torcedor nao encontrado", HttpStatus.NOT_FOUND);
		}
		return sociosTorcedores;
	}

	public SocioTorcedor buscarSocioTorcedorPorId(Long id) {
		return socioTorcedorRepository.findById(id)
				.orElseThrow(() -> new SocioTorcedorException("Socio Torcedor nao encontrado", HttpStatus.NOT_FOUND));
	}

	public SocioTorcedor criaSocioTorcedor(SocioTorcedorTO socioTorcedorTO) {
		SocioTorcedor socioTorcedor = socioTorcedorRepository.findByEmail(socioTorcedorTO.getEmail());
		
		if (null != socioTorcedor) {
			throw new SocioTorcedorException("Socio Torcedor ja cadastrado", HttpStatus.BAD_REQUEST);
		}
		socioTorcedorRepository.save(SocioTorcedor.parse(socioTorcedorTO));
		return socioTorcedor;
	}

	public SocioTorcedor atualizaSocioTorcedor(Long id, SocioTorcedorTO socioTorcedorTO) {
		SocioTorcedor socioTorcedor = socioTorcedorRepository.findById(id)
				.orElseThrow(() -> new SocioTorcedorException("Socio Torcedor nao encontrado", HttpStatus.NOT_FOUND));
		socioTorcedor.atualizar(socioTorcedor, socioTorcedorTO);
		socioTorcedorRepository.save(socioTorcedor);
		return socioTorcedor;
	}

	public void deletaSocioTorcedor(Long id) {
		SocioTorcedor socioTorcedor = socioTorcedorRepository.findById(id)
				.orElseThrow(() -> new SocioTorcedorException("Socio Torcedor nao encontrado", HttpStatus.NOT_FOUND));
		socioTorcedorRepository.delete(socioTorcedor);
	}

	public SocioTorcedor buscarSocioTorcedorPorEmail(String email) {
		return socioTorcedorRepository.findByEmail(email);
	}
	
}
