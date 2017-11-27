package br.com.educandariopassosfirmes.entidades;

import java.sql.Date;

public abstract class Funcionario {

	private String id;
	private String formacao;
	private String estadoCivil;
	private int qtDependente;
	private Date dtAdmissao;
	private int cargaHoraria;
	private double salario;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getQtDependente() {
		return qtDependente;
	}

	public void setQtDependente(int qtDependente) {
		this.qtDependente = qtDependente;
	}

	public Date getDtAdmissao() {
		return dtAdmissao;
	}

	public void setDtAdmissao(Date dtAdmissao) {
		this.dtAdmissao = dtAdmissao;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public abstract double getBonificacao(double pSalario);
	
}
