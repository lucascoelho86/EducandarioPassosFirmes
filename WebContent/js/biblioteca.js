/**
 * Funções utilizadas no projeto
 */

function formatarCPF(event){
	var valor = document.getElementById("cpf").value;
	var tamanhoValor = valor.length;
	var tecla = event.keyCode;
	
	if(tecla != 8 && tecla != 46){
		if(tamanhoValor == 3 || tamanhoValor == 7){
			valor = valor.concat(".");
		}else if(tamanhoValor == 11){
			valor = valor.concat("-");
		}
	}
	document.getElementById("cpf").value = valor;
}

function formatarCPFOnload(){
	var valor = document.getElementById("cpf").value;
	var tamanhoValor = valor.length;
	
	if(tamanhoValor == 11){
		var primeiraParteCPF = valor.substring(0, 3);
		var segundaParteCPF = valor.substring(3, 6);
		var terceiraParteCPF = valor.substring(6, 9);
		var quartaParteCPF = valor.substring(9, 11);
		
		valor = primeiraParteCPF.concat(".").concat(segundaParteCPF).concat(".").concat(terceiraParteCPF).concat("-").concat(quartaParteCPF);
	}
	
	document.getElementById("cpf").value = valor;
}

function formatarMatricula(event){
	var valor = document.getElementById("matricula").value;
	var tamanhoValor = valor.length;
	var tecla = event.keyCode;
	
	if(tecla != 8 && tecla != 46){
		if(tamanhoValor == 4 || tamanhoValor == 7){
			valor = valor.concat(".");
		}
	}
	document.getElementById("matricula").value = valor;
}

function formatarMatriculaOnload(){
	var valor = document.getElementById("matricula").value;
	var matriculaPrimeiraParte = valor.substring(0, 4);
	var matriculaSegundaParte = valor.substring(4, 6);
	var matriculaTerceiraParte = valor.substring(6);
	
	valor = matriculaPrimeiraParte.concat(".").concat(matriculaSegundaParte).concat(".").concat(matriculaTerceiraParte);
	
	document.getElementById("matricula").value = valor;
}

function formatarCamposData(pCampo, pValue, pEvento){
	var valor = pValue.value;
	var tamanhoValor = valor.length;
	var tecla = pEvento.keyCode;
	
	if(tecla != 8 && tecla != 46){
		if(tamanhoValor == 2 || tamanhoValor == 5){
			valor = valor.concat("/");
		}
	}
	
	document.getElementById(pCampo).value = valor;
}

function formatarCamposDataAlunoOnload(){
	var valorDtNasc = document.getElementById("dtNascimento").value;
	var valorDtAdm = document.getElementById("dtMatricula").value;
	var valorDtNascResp = document.getElementById("dtNascimentoResp").value;
	var resDtNasc = valorDtNasc.split("-");
	var resDtAdm = valorDtAdm.split("-");
	var resDtNascResp = valorDtNascResp.split("-");
	var anoDtNasc = resDtNasc[0];
	var mesDtNasc = resDtNasc[1];
	var diaDtNasc = resDtNasc[2];
	var anoDtAdm = resDtAdm[0];
	var mesDtAdm = resDtAdm[1];
	var diaDtAdm = resDtAdm[2];
	var anoDtNascResp = resDtNascResp[0];
	var mesDtNascResp = resDtNascResp[1];
	var diaDtNascResp = resDtNascResp[2];
	
	valorDtNasc = diaDtNasc.concat("/").concat(mesDtNasc).concat("/").concat(anoDtNasc);
	valorDtAdm = diaDtAdm.concat("/").concat(mesDtAdm).concat("/").concat(anoDtAdm);
	valorDtNascResp = diaDtNascResp.concat("/").concat(mesDtNascResp).concat("/").concat(anoDtNascResp);
	document.getElementById("dtNascimento").value = valorDtNasc;
	document.getElementById("dtMatricula").value = valorDtAdm;
	document.getElementById("dtNascimentoResp").value = valorDtNascResp;
}

function formatarCamposDataOnload(){
	var valorDtNasc = document.getElementById("dtNascimento").value;
	var valorDtAdm = document.getElementById("dtAdmissao").value;
	var resDtNasc = valorDtNasc.split("-");
	var resDtAdm = valorDtAdm.split("-");
	var anoDtNasc = resDtNasc[0];
	var mesDtNasc = resDtNasc[1];
	var diaDtNasc = resDtNasc[2];
	var anoDtAdm = resDtAdm[0];
	var mesDtAdm = resDtAdm[1];
	var diaDtAdm = resDtAdm[2];
	
	valorDtNasc = diaDtNasc.concat("/").concat(mesDtNasc).concat("/").concat(anoDtNasc);
	valorDtAdm = diaDtAdm.concat("/").concat(mesDtAdm).concat("/").concat(anoDtAdm);
	document.getElementById("dtNascimento").value = valorDtNasc;
	document.getElementById("dtAdmissao").value = valorDtAdm;
}

function formatarCampoCarteiraEstudante(event){
	var valor = document.getElementById("nrCarteiraEstudante").value;
	var tamanhoValor = valor.length;
	var tecla = event.keyCode;
	
	if(tecla != 8 && tecla != 46){
		if(tamanhoValor == 4){
			valor = valor.concat("-");
		}
	}
	document.getElementById("nrCarteiraEstudante").value = valor;
}

function formatarCampoCarteiraEstudanteOnload(){
	var valor = document.getElementById("nrCarteiraEstudante").value;
	var carteiraPrimeiraParte;
	var carteiraSegundaParte;
	if(valor != null && valor != ""){
		carteiraPrimeiraParte = valor.substring(0, 4);
		carteiraSegundaParte = valor.substring(4);
		valor = carteiraPrimeiraParte.concat("-").concat(carteiraSegundaParte);
	}
	
	document.getElementById("nrCarteiraEstudante").value = valor;
}

function formatarCampoTelefone(pCampo, pValue, pEvento){
	var valor = pValue.value;
	var tamanhoValor = valor.length;
	var abrirParentese = "(";
	
	var tecla = pEvento.keyCode;
		
	if(tecla != 8 && tecla != 46){
		if(tamanhoValor == 2){
			valor = abrirParentese.concat(valor).concat(")");
		}else if((tamanhoValor == 8 && valor.substring(4,5) != "9") || (tamanhoValor == 9 && valor.substring(4,5) == "9")){
			valor = valor.concat("-");
		}
	}		
	
	document.getElementById(pCampo).value = valor;
}

function formatarCampoTelefoneOnload(){
	var valor = document.getElementById("telefone").value;
	var tamanhoValor = valor.length;
	var abrirParentese = "(";
	
	if(tamanhoValor == 10){
		var ddd = valor.substring(0, 2);
		var primeiraParte = valor.substring(2, 6);
		var segundaParte = valor.substring(6, 10);
		valor = abrirParentese.concat(ddd).concat(")").concat(primeiraParte).concat("-").concat(segundaParte);
	}else if(tamanhoValor == 11){
		var ddd = valor.substring(0, 2);
		var primeiraParte = valor.substring(2, 7);
		var segundaParte = valor.substring(7, 11);
		valor = abrirParentese.concat(ddd).concat(")").concat(primeiraParte).concat("-").concat(segundaParte);
	}
		
	document.getElementById("telefone").value = valor;
}

function mascara(o,f){
	v_obj=o
	v_fun=f
	setTimeout("execmascara()",1)
}

function execmascara(){
	v_obj.value=v_fun(v_obj.value)
}

function moeda(v){ 
	v=v.replace(/\D/g,"") // permite digitar apenas numero 
	v=v.replace(/(\d{1})(\d{17})$/,"$1.$2") // coloca ponto antes dos ultimos digitos 
	v=v.replace(/(\d{1})(\d{13})$/,"$1.$2") // coloca ponto antes dos ultimos 13 digitos 
	v=v.replace(/(\d{1})(\d{10})$/,"$1.$2") // coloca ponto antes dos ultimos 10 digitos 
	v=v.replace(/(\d{1})(\d{7})$/,"$1.$2") // coloca ponto antes dos ultimos 7 digitos 
	v=v.replace(/(\d{1})(\d{1,2})$/,"$1,$2") // coloca virgula antes dos ultimos 4 digitos 
	return v;
}

function Limpar(valor, validos) {
	// retira caracteres invalidos da string
	var result = "";
	var aux;
	for (var i=0; i < valor.length; i++) {
		aux = validos.indexOf(valor.substring(i, i+1));
		if (aux>=0) {
			result += aux;
		}
	}
	return result;
}

//Formata número tipo moeda usando o evento onKeyDown
function Formata(campo,tammax,teclapres,decimal) {
	var tecla = teclapres.keyCode;
	vr = Limpar(campo.value,"0123456789");
	tam = vr.length;
	dec=decimal

	if (tam < tammax && tecla != 8){
		tam = vr.length + 1 ;
	}

	if (tecla == 8 ){
		tam = tam - 1 ;
	}

	if ( tecla == 8 || tecla >= 48 && tecla <= 57 || tecla >= 96 && tecla <= 105 ){

		if ( tam <= dec ){
			campo.value = vr ;
		}
	
		if ( (tam > dec) && (tam <= 5) ){
			campo.value = vr.substr( 0, tam - 2 ) + "," + vr.substr( tam - dec, tam ) ;
		}
		if ( (tam >= 6) && (tam <= 8) ){
			campo.value = vr.substr( 0, tam - 5 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - dec, tam ) ; 
		}
		if ( (tam >= 9) && (tam <= 11) ){
			campo.value = vr.substr( 0, tam - 8 ) + "." + vr.substr( tam - 8, 3 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - dec, tam ) ;
		}
		if ( (tam >= 12) && (tam <= 14) ){
			campo.value = vr.substr( 0, tam - 11 ) + "." + vr.substr( tam - 11, 3 ) + "." + vr.substr( tam - 8, 3 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - dec, tam ) ;
		}
		if ( (tam >= 15) && (tam <= 17) ){
			campo.value = vr.substr( 0, tam - 14 ) + "." + vr.substr( tam - 14, 3 ) + "." + vr.substr( tam - 11, 3 ) + "." + vr.substr( tam - 8, 3 ) + "." + vr.substr( tam - 5, 3 ) + "," + vr.substr( tam - 2, tam ) ;
		}
	} 

}

function SomenteNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;   
    if((tecla>47 && tecla<58))
    	return true;
    else{
    	if (tecla==8 || tecla==0)
    		return true;
    	else 
    		return false;
    }
}

function letras(){
	tecla = event.keyCode;
	if (tecla >= 33 && tecla <= 64){ 
	    return false;
	}else{
	   return true;
	}
}

//Verifica se algum "radiobutton" esta selecionado na colecao com nome "pNmRadioButton" passado como parametro.
//Se  pSemMensagem igual a true, não exibe mensagem
//Utilizado em telas de consulta.
function isRadioButtonConsultaSelecionado(pNmRadioButton) {
	var i = 0;

	radioButton = eval(pNmRadioButton);
	
	if (radioButton == null) {
		return false;
	}
	
	if (radioButton.checked) {
		// Quando existe apenas um radio button no formulario
		return true;
	}
	
	for (i = 0; i < radioButton.length; i++) {
		if (radioButton.item(i).checked == true) {
			return true;
		}
	}
	
	return false;
}
