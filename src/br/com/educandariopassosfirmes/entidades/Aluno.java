package br.com.educandariopassosfirmes.entidades;

import java.sql.Date;

public class Aluno {

	private String id;
	private String idTurma;
	private String idResponsavel;
	private Date dtMatricula;
	private String necessidadeEspecial;
	private String cdCarteiraEstudante;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}
	public String getIdResponsavel() {
		return idResponsavel;
	}
	public void setIdResponsavel(String idResponsavel) {
		this.idResponsavel = idResponsavel;
	}
	public Date getDtMatricula() {
		return dtMatricula;
	}
	public void setDtMatricula(Date dtMatricula) {
		this.dtMatricula = dtMatricula;
	}
	public String getNecessidadeEspecial() {
		return necessidadeEspecial;
	}
	public void setNecessidadeEspecial(String necessidadeEspecial) {
		this.necessidadeEspecial = necessidadeEspecial;
	}
	public String getCdCarteiraEstudante() {
		return cdCarteiraEstudante;
	}
	public void setCdCarteiraEstudante(String cdCarteiraEstudante) {
		this.cdCarteiraEstudante = cdCarteiraEstudante;
	}
	
}
