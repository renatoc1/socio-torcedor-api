package com.api.socioTorcedor.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.socioTorcedor.model.to.SocioTorcedorTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SOCIO_TORCEDOR")
public class SocioTorcedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;

	@Column(name = "DT_NASCIMENTO")
	private LocalDate dtNascimento;
	
	@Column(name = "ID_TIME_CORACAO")
	private Long idTimeCoracao;

	public static SocioTorcedor parse(SocioTorcedorTO socioTorcedorTO) {
		return SocioTorcedor.builder().email(socioTorcedorTO.getEmail()).nomeCompleto(socioTorcedorTO.getNomeCompleto())
				.dtNascimento(socioTorcedorTO.getDtNascimento()).idTimeCoracao(socioTorcedorTO.getIdTimeCoracao()).build();
	}

	public SocioTorcedor atualizar(SocioTorcedor socioTorcedor, SocioTorcedorTO socioTorcedorTO) {
		socioTorcedor.setEmail(socioTorcedorTO.getEmail());
		socioTorcedor.setNomeCompleto(socioTorcedorTO.getNomeCompleto());
		socioTorcedor.setDtNascimento(socioTorcedorTO.getDtNascimento());
		socioTorcedor.setIdTimeCoracao(socioTorcedorTO.getIdTimeCoracao());
		return socioTorcedor;
	}

}
