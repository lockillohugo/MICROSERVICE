package com.advise.microservicio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Faltantes implements Serializable{
	
	private static final long serialVersionUID = -4613007090017657003L;
	
	private String fechaInicio;
	private String fechaFin;
	private ArrayList<String> listaPeriodos;
	private ArrayList<String> listaFaltantes;
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public ArrayList<String> getListaPeriodos() {
		return listaPeriodos;
	}
	public void setListaPeriodos(ArrayList<String> listaPeriodos) {
		this.listaPeriodos = listaPeriodos;
	}
	public ArrayList<String> getListaFaltantes() {
		return listaFaltantes;
	}
	public void setListaFaltantes(ArrayList<String> listaFaltantes) {
		this.listaFaltantes = listaFaltantes;
	}	
}
