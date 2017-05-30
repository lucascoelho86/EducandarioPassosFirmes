package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.pacote1.entidades.PerfilFuncao;
import br.com.pacote1.entidades.Pessoa;
import br.com.pacote1.entidades.PessoaPerfil;

public class PerfilFuncaoDAO {

	private Connection con = Conexao.getConnection();
	
	 public PerfilFuncao consultar(int pFuncao){
		
		String sql = "SELECT * FROM PERFIL_FUNCAO WHERE ID_FUNCAO = ?";
		PerfilFuncao perfilFuncao = null;
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, pFuncao);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				
				perfilFuncao = new PerfilFuncao();
				perfilFuncao.setId_funcao(resultado.getInt("id_funcao"));
				perfilFuncao.setId_perfil(resultado.getInt("id_perfil"));
				
			}
			
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return perfilFuncao;
	}
	
}
