package br.com.educandariopassosfirmes.entidades;

public class Nota {

	private String idAluno;
	private int idDisciplina;
	private int idTpNota;
	private double nota;
	private int unidade;
	public String getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(String idAluno) {
		this.idAluno = idAluno;
	}
	public int getIdDisciplina() {
		return idDisciplina;
	}
	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public int getIdTpNota() {
		return idTpNota;
	}
	public void setIdTpNota(int idTpNota) {
		this.idTpNota = idTpNota;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public int getUnidade() {
		return unidade;
	}
	public void setUnidade(int unidade) {
		this.unidade = unidade;
	}
	
}
