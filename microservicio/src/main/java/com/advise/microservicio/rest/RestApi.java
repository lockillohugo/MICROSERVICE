package com.advise.microservicio.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.advise.microservicio.model.Faltantes;
import com.advise.microservicio.model.Periodos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/faltantes/")
public class RestApi {
	
	@GetMapping(path = "api")
	public @ResponseBody Faltantes getData() throws ParseException, JsonMappingException, JsonProcessingException {
		//FORMATO PARA CONTROLAR FECHAS
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		
		//LLAMADA A API REST
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		
		header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(header);
		
		ResponseEntity<String> periodoString = restTemplate.exchange("http://127.0.0.1:8081/periodos/api", HttpMethod.GET, entity, String.class);
		//LLAMADA A API REST
		
		//Convertir respuesta a JSON
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(periodoString.getBody());
		
		//Guardar json en clase
		Faltantes faltante = new Faltantes();
		faltante.setFechaInicio(root.get("fechaCreacion").asText());
		faltante.setFechaFin(root.get("fechaFin").asText());
		faltante.setListaPeriodos(mapper.convertValue(root.get("fechas"), ArrayList.class));
		
		ArrayList<String> fechasFaltantes = new ArrayList<>();
		String newFecha = faltante.getFechaInicio();
		boolean termino = false;
		
		while (!termino) {
			termino = newFecha.equals(faltante.getFechaFin());
			
			if (!faltante.getListaPeriodos().contains(newFecha)) {
				fechasFaltantes.add(newFecha);
			}
			
	        newFecha = fmt.format(addMonth(fmt.parse(newFecha), 1));
		}
		
		faltante.setListaFaltantes(fechasFaltantes);
		
		return faltante;
	}
	
	public static Date addMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return cal.getTime();
    }
}
