package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.pacote1.entidades.Funcionario;

public class FuncionarioDAO {

	private Connection con = Conexao.getConnection();
	
	public Funcionario buscarPorIdSenha(String pLogin, String pSenha){
		String sql = "SELECT * FROM FUNCIONARIO WHERE ID_PESSOA = ? AND SENHA = ?";
		Funcionario funcionario = null;
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
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
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pFuncionario.getId());
			preparador.setString(2, pFuncionario.getSenha());
			
			preparador.execute();
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
