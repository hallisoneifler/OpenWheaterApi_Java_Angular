package br.com.segware;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Relatorio {

	private Integer codSequencial;
	private String codCliente;
	private String codEvento;
	private Tipo tipoEvento;
	private LocalTime dataInicio;
	private LocalTime dataFim;
	private String codAtendente;
	private long tempoMedioAtendimentoSegundos;
	
	public Relatorio(String codSequencial, String codCliente, String codEvento,
			String tipoEvento, String dataInicio, String dataFim, String codAtendente) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
		
		this.codSequencial = Integer.parseInt(codSequencial);
		this.codCliente = codCliente;
		this.codEvento = codEvento;
		this.tipoEvento = Tipo.valueOf(tipoEvento);
		this.codAtendente = codAtendente;
		
		this.dataInicio = LocalTime.parse(dataInicio, formatter);
		this.dataFim = LocalTime.parse(dataFim, formatter);
		
		Duration d = Duration.between(this.dataInicio, this.dataFim);
		this.tempoMedioAtendimentoSegundos = d.getSeconds();

	}
	public Integer getCodSequencial() {
		return codSequencial;
	}
	public void setCodSequencial(Integer codSequencial) {
		this.codSequencial = codSequencial;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCodEvento() {
		return codEvento;
	}
	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}
	public Tipo getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(Tipo tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public LocalTime getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalTime getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalTime dataFim) {
		this.dataFim = dataFim;
	}
	public String getCodAtendente() {
		return codAtendente;
	}
	public void setCodAtendente(String codAtendente) {
		this.codAtendente = codAtendente;
	}
	public long getTempoMedioAtendimentoSegundos() {
		return tempoMedioAtendimentoSegundos;
	}
	public void setTempoMedioAtendimentoSegundos(long tempoMedioAtendimentoSegundos) {
		this.tempoMedioAtendimentoSegundos = tempoMedioAtendimentoSegundos;
	}
	
	
}
