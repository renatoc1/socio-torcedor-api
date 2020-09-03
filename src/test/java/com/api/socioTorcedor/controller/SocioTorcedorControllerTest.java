package com.api.socioTorcedor.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.api.socioTorcedor.model.SocioTorcedor;
import com.api.socioTorcedor.model.to.SocioTorcedorTO;
import com.api.socioTorcedor.service.SocioTorcedorService;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class SocioTorcedorControllerTest {

static final String SOCIO_TORCEDOR_API = "/socioTorcedor";
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
	private SocioTorcedorService socioTorcedorService;

	@Test
	@DisplayName("Deve buscar todos os Sócios Torcedores paginados")
	public void buscaTodosSociosTorcedores() throws Exception {
		
		List<SocioTorcedor> sociosTorcedores = new ArrayList<SocioTorcedor>();
		Page<SocioTorcedor> sociosTorcedoresPaginados = new PageImpl<>(sociosTorcedores);
		Pageable pageable = PageRequest.of(0, 10);
		BDDMockito.given(socioTorcedorService.buscaTodosSociosTorcedores(pageable)).willReturn(sociosTorcedoresPaginados);
		String json = new ObjectMapper().writeValueAsString(pageable);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(SOCIO_TORCEDOR_API)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
			
			mock
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
			
	}
	
	@Test
	@DisplayName("Deve buscar Sócio Torcedor por id")
	public void buscarSocioTorcedorPorId() throws Exception {
		
		BDDMockito.given(socioTorcedorService.buscarSocioTorcedorPorId(1L)).willReturn(Mockito.any(SocioTorcedor.class));

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(SOCIO_TORCEDOR_API.concat("/" + 1))
				.accept(MediaType.APPLICATION_JSON);
			
			mock
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
			
	}
	
	@Test
	@DisplayName("Deve buscar Sócio Torcedor por email")
	public void buscaSocioTorcedorPorEmail() throws Exception {
		
		BDDMockito.given(socioTorcedorService.buscarSocioTorcedorPorEmail("teste@teste.com")).willReturn(Mockito.any(SocioTorcedor.class));
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(SOCIO_TORCEDOR_API.concat("/" + 1))
				.accept(MediaType.APPLICATION_JSON);
			
			mock
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
			
	}
	
	@Test
	@DisplayName("Deve criar novo Sócio Torcedor")
	public void criaSocioTorcedor() throws Exception {
		
		SocioTorcedorTO socioTorcedorTO = SocioTorcedorTO.builder().email("teste@teste.com").nomeCompleto("Teste Teste").idTimeCoracao(1L).build();
		BDDMockito.given(socioTorcedorService.criaSocioTorcedor(socioTorcedorTO)).willReturn(Mockito.any(SocioTorcedor.class));
		String json = new ObjectMapper().writeValueAsString(socioTorcedorTO);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(SOCIO_TORCEDOR_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json);
			
			mock
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn();
			
	}
	
	@Test
	@DisplayName("Deve deletar Sócio Torcedor")
	public void deletaSocioTorcedor() throws Exception {
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.delete(SOCIO_TORCEDOR_API.concat("/" + 1))
				.accept(MediaType.APPLICATION_JSON);
			
			mock
				.perform(request)
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
			
	}
	
}
