package com.advise.microservicio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Periodos implements Serializable{
	
	private static final long serialVersionUID = 4890035247945272191L;
	
	private Date fechaInicio;
	private Date fechaFin;
	private ArrayList<Date> listaFechas;
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public ArrayList<Date> getListaFechas() {
		return listaFechas;
	}
	public void setListaFechas(ArrayList<Date> listaFechas) {
		this.listaFechas = listaFechas;
	}
}
