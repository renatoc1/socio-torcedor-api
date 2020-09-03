package com.api.socioTorcedor.model.to;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocioTorcedorTO {

	private String email;

	private String nomeCompleto;

	private LocalDate dtNascimento;
	
	private Long idTimeCoracao;

}
