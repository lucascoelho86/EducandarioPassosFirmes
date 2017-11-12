package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.Responsavel;

public class ResponsavelDAO extends Conexao{

	 public void cadastrar(Responsavel pResponsavel){
		
		String sql = "INSERT INTO RESPONSAVEL (ID_PESSOA, PARENTESCO, ESTADO_CIVIL, ESCOLARIDADE, PROFISSAO, RENDA) values (?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pResponsavel.getId());
			preparador.setString(2, pResponsavel.getParentesco());
			preparador.setString(3, pResponsavel.getEstadoCivil());
			preparador.setString(4, pResponsavel.getEscolaridade());
			preparador.setString(5, pResponsavel.getProfissao());
			preparador.setDouble(6, pResponsavel.getRenda());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public void excluir(String pId){
			
		String sql = "DELETE FROM RESPONSAVEL WHERE id_pessoa=?";
			
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
				
				preparador.setString(1, pId);
				
			preparador.execute();
			preparador.close();
				
			System.out.println("Excluído com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
