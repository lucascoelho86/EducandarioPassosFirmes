package br.com.educandariopassosfirmes.util;

public final class BibliotecaFormatarDados {
	
	public static String formatarCPF(String pCampo){
		String cpf = "";
		if(pCampo != null && !pCampo.equals("")){
			String cpfPrimeiraParte = pCampo.substring(0, 3);
			String cpfSegundaParte = pCampo.substring(3, 6);
			String cpfTerceiraParte = pCampo.substring(6, 9);
			String cpfQuartaParte = pCampo.substring(9, 11);
			
			cpf = cpfPrimeiraParte + "." + cpfSegundaParte + "." + cpfTerceiraParte + "-" + cpfQuartaParte;
		}
		
		return cpf;
		
	}
	
	public static String formatarMatricula(String pCampo){
		String matricula = "";
		if(pCampo != null && !pCampo.equals("")){
			String cpfPrimeiraParte = pCampo.substring(0, 4);
			String cpfSegundaParte = pCampo.substring(4, 6);
			String cpfTerceiraParte = pCampo.substring(6);
			
			matricula = cpfPrimeiraParte + "." + cpfSegundaParte + "." + cpfTerceiraParte;
		}
		
		return matricula;
		
	}
	
	public static String completarNumeroComZerosEsquerda(String pString, int pTmFormato) {
		int contador = pString.length();

		for (int i = contador; i < pTmFormato; i++) {
			pString = "0" + pString;
		}

		return pString;
	}

}
