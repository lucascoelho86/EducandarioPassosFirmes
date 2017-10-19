package br.com.educandariopassosfirmes.entidades;

public class Turma {

	private int idTurma;
	private String dsTurma;
	private String turno;
	private int qtMaxAlunos;
	
	public int getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(int idTurma) {
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
	
}
