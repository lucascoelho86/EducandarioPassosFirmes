package br.com.educandariopassosfirmes.entidades;

import java.sql.Date;

public class Aluno extends Membros {

	private String idTurma;
	private String idResponsavel;
	private String parentesco;
	private Date dtMatricula;
	private String necessidadeEspecial;
	private String detalheNecessidadeEspecial;
	private String cdCarteiraEstudante;
	private String cdCertidaoNascimento;

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

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
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

	public String getDetalheNecessidadeEspecial() {
		return detalheNecessidadeEspecial;
	}

	public void setDetalheNecessidadeEspecial(String detalheNecessidadeEspecial) {
		this.detalheNecessidadeEspecial = detalheNecessidadeEspecial;
	}

	public String getCdCarteiraEstudante() {
		return cdCarteiraEstudante;
	}

	public void setCdCarteiraEstudante(String cdCarteiraEstudante) {
		this.cdCarteiraEstudante = cdCarteiraEstudante;
	}

	public String getCdCertidaoNascimento() {
		return cdCertidaoNascimento;
	}

	public void setCdCertidaoNascimento(String cdCertidaoNascimento) {
		this.cdCertidaoNascimento = cdCertidaoNascimento;
	}

}
