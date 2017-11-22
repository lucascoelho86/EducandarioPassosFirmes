package br.com.educandariopassosfirmes.entidades;

public class Turma {

	private String idTurma;
	private String dsTurma;
	private String turno;
	private int qtMaxAlunos;
	private String sala;

	public String getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}

	public String getDsTurma() {
		return dsTurma;
	}

	public void setDsTurma(String dsTurma) {
		this.dsTurma = dsTurma;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getQtMaxAlunos() {
		return qtMaxAlunos;
	}

	public void setQtMaxAlunos(int qtMaxAlunos) {
		this.qtMaxAlunos = qtMaxAlunos;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

}
