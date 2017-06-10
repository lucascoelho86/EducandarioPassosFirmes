package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.pacote1.entidades.PessoaPerfil;

public class PessoaPerfilDAO {

	private Connection con = Conexao.getConnection();
	
	 public void cadastrar(PessoaPerfil pPessoaPerfil){
		
		String sql = "INSERT INTO PESSOA_PERFIL (id_pessoa, id_perfil) values (?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pPessoaPerfil.getId());
			preparador.setInt(2, pPessoaPerfil.getId_perfil());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	 public PessoaPerfil consultar(String pId){
			
			String sql = "SELECT * FROM PESSOA_PERFIL WHERE id_pessoa = ?";
			
			PessoaPerfil pessoaPerfil = null;
			
			try {
				PreparedStatement preparador = con.prepareStatement(sql);
				
				preparador.setString(1, pId);
				
				ResultSet resultado = preparador.executeQuery();

				while(resultado.next()){
					pessoaPerfil = new PessoaPerfil();
					pessoaPerfil.setId(resultado.getString("id_pessoa"));
					pessoaPerfil.setId_perfil(resultado.getInt("id_perfil"));					
				}
				
				preparador.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return pessoaPerfil;
		}
	 
	 public void alterar(PessoaPerfil pPessoaPerfil){
			
			String sql = "UPDATE PESSOA_PERFIL SET id_pessoa=?, id_perfil=? WHERE id_pessoa=?";
			
			try {
				PreparedStatement preparador = con.prepareStatement(sql);
				
				preparador.setString(1, pPessoaPerfil.getId());
				preparador.setInt(2, pPessoaPerfil.getId_perfil());
				preparador.setString(3, pPessoaPerfil.getId());
				
				preparador.execute();
				preparador.close();
				
				System.out.println("Alterado com sucesso!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	 
	 public void excluir(String pId){
			
			String sql = "DELETE FROM PESSOA_PERFIL WHERE id_pessoa=?";
			
			try {
				PreparedStatement preparador = con.prepareStatement(sql);
				
				preparador.setString(1, pId);
				
				preparador.execute();
				preparador.close();
				
				System.out.println("Excluído com sucesso!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
}
