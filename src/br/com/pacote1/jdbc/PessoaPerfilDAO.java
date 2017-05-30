package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pacote1.entidades.PessoaPerfil;

public class PessoaPerfilDAO {

	private Connection con = Conexao.getConnection();
	
	 public void cadastrar(PessoaPerfil pPessoaPerfil){
		
		String sql = "INSERT INTO PESSOA_PERFIL (id_pessoa, id_perfil) values (?, ?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pPessoaPerfil.getId());
			preparador.setInt(1, pPessoaPerfil.getId_perfil());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
