package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.pacote1.entidades.Responsavel;

public class ResponsavelDAO {

	private Connection con = Conexao.getConnection();
	
	 public void cadastrar(Responsavel pResponsavel){
		
		String sql = "INSERT INTO RESPONSAVEL (id_pessoa) values (?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pResponsavel.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
