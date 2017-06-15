package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.educandariopassosfirmes.entidades.Funcionario;

public class FuncionarioDAO extends Conexao{

	public Funcionario buscarPorIdSenha(String pLogin, String pSenha){
		String sql = "SELECT * FROM FUNCIONARIO WHERE ID_PESSOA = ? AND SENHA = ?";
		Funcionario funcionario = null;
		try{
			PreparedStatement preparador = getPreparedStatement(sql);
			preparador.setString(1, pLogin);
			preparador.setString(2, pSenha);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				funcionario = new Funcionario();
				
				funcionario.setId(resultado.getString("id_pessoa"));
				funcionario.setSenha(resultado.getString("senha"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return funcionario;
		
	}
	
	public void cadastrar(Funcionario pFuncionario){
		
		String sql = "INSERT INTO FUNCIONARIO (id_pessoa, senha) values (?,?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pFuncionario.getId());
			preparador.setString(2, pFuncionario.getSenha());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Funcionario consultar(String pCpf){
		
		String sql = "SELECT * FROM FUNCIONARIO WHERE id_pessoa = ?";
		
		Funcionario funcionario = null;
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pCpf);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				funcionario = new Funcionario();
				funcionario.setId(resultado.getString("id_pessoa"));
				funcionario.setSenha(resultado.getString("senha"));
				
			}
			
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return funcionario;
	}
	
	public void alterar(Funcionario pFuncionario){
		
		String sql = "UPDATE FUNCIONARIO SET id_pessoa=?, senha=? WHERE id_pessoa=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pFuncionario.getId());
			preparador.setString(2, pFuncionario.getSenha());
			preparador.setString(3, pFuncionario.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM FUNCIONARIO WHERE id_pessoa=?";
		
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
