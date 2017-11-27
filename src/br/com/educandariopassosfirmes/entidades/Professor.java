package br.com.educandariopassosfirmes.entidades;

public class Professor extends Funcionario {

	@Override
	public double getBonificacao(double pSalario) {
		return pSalario * 1.2;
	}

}
