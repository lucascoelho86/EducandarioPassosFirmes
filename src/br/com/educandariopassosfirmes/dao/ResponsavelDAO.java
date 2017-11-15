package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			
		String sql = "DELETE FROM RESPONSAVEL WHERE ID_PESSOA=?";
			
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
	 
	 public Responsavel consultar(String pId){
			String sql = "SELECT * FROM RESPONSAVEL ";
			String where = "WHERE ";
			String sql2 = "ID_PESSOA = ?";
			String sqlComplementar = "";
			Responsavel responsavel = null;
			int contador=0;
			try{
				
				if(pId != null && !pId.equals("")){
					sqlComplementar = sql2;
				}
				
				if(!sqlComplementar.equals("")){
					sql = sql + where + sqlComplementar;
				}
				
				PreparedStatement preparador = getPreparedStatement(sql);
				
				if(pId != null && !pId.equals("")){
					contador++;
					preparador.setString(contador, pId);
				}
				
				ResultSet resultado = preparador.executeQuery();
				
				if(resultado.next()){
					responsavel = new Responsavel();
					
					responsavel.setId(resultado.getString("ID_PESSOA"));
					responsavel.setParentesco(resultado.getString("PARENTESCO"));
					responsavel.setEstadoCivil(resultado.getString("ESTADO_CIVIL"));
					responsavel.setEscolaridade(resultado.getString("ESCOLARIDADE"));
					responsavel.setProfissao(resultado.getString("PROFISSAO"));
					responsavel.setRenda(resultado.getDouble("RENDA"));
					
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return responsavel;
			
		}
	 
	 public void alterar(Responsavel pResponsavel){
			
			String sql = "UPDATE RESPONSAVEL SET PARENTESCO=?, ESTADO_CIVIL=?, ESCOLARIDADE=?, PROFISSAO=?, RENDA=? WHERE ID_PESSOA=?";
			
			try {
				PreparedStatement preparador = getPreparedStatement(sql);
				
				preparador.setString(1, pResponsavel.getParentesco());
				preparador.setString(2, pResponsavel.getEstadoCivil());
				preparador.setString(3, pResponsavel.getEscolaridade());
				preparador.setString(4, pResponsavel.getProfissao());
				preparador.setDouble(5, pResponsavel.getRenda());
				preparador.setString(6, pResponsavel.getId());
				
				preparador.execute();
				preparador.close();
				
				System.out.println("Alterado com sucesso!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
