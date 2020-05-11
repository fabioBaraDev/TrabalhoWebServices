package br.com.fiap.trabalho.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.fiap.trabalho.entity.Endereco;
import br.com.fiap.trabalho.service.ValidaCEP;

@Service
public class ValidaCEPImpl implements ValidaCEP {

	public Boolean validar(String cep) {
		try {
			RestTemplate rest = new RestTemplate();
	
			String url = "https://viacep.com.br/ws/" + cep + "/json/";
			ResponseEntity<Endereco> response = rest.getForEntity(url, Endereco.class);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}
