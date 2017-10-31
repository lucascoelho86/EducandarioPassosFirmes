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
