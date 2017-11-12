package br.com.educandariopassosfirmes.util;

/**
 * 
  */
public class Select {
	//~ Atributos/inicializadores estaticos ----------------------------------------------------------------------------------------

		private static Select aSelectQtRegistrosPorPagina = new Select();
		
		//~ Metodos --------------------------------------------------------------------------------------------------------------------

		/**
		 * -
		 *
		 * @return  
		 */
		public static Select getInstancia() {
			if (Select.aSelectQtRegistrosPorPagina == null) {
				Select.aSelectQtRegistrosPorPagina = new Select();
			}

			return Select.aSelectQtRegistrosPorPagina;
		}

	/**
	 * 
	 * @param pValue
	 * @param pDescricao
	 * @param pCampoSelecionado
	 * @param pPrimeiroCampo
	 * @param pUltimoCampo
	 * @return
	 */
	public String getHTML(String pIdSelect, String pNmSelect, String pValue, String pDescricao, boolean pCampoSelecionado, int pPrimeiroCampo, boolean pUltimoCampo) {
		String html = "";

		if(pPrimeiroCampo == 0) {
			html = "<select id=\"" + pIdSelect + "\" name=\"" + pNmSelect + "\"> \n\t" + "<option " + "value=\"" + pPrimeiroCampo + "\">" + "Selecione uma opção" + "</option> \n\t";
		}
		
		if(pCampoSelecionado) {
			html = html + "<option " + "value=\"" + pValue + "\"" + " selected>" + pDescricao + "</option> \n";
		}else {
			html = html + "<option " + "value=\"" + pValue + "\">" + pDescricao + "</option> \n";
		}
		
		if(pUltimoCampo) {
			html = html + "</select>";
		}else {
			html = html + "\t";
		}

		return html;
	}

}
