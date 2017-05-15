package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pacote1.entidades.Pessoa;

public class PessoaDAO {

	private Connection con = Conexao.getConnection();
	
	public void cadastrar(Pessoa pessoa){
		
		String sql = "INSERT INTO PESSOA (id_pessoa, nome, login, senha) values (?,?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, pessoa.getId());
			preparador.setString(2, pessoa.getNome());
			preparador.setString(3, pessoa.getLogin());
			preparador.setString(4, pessoa.getSenha());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Pessoa pessoa){
		
		String sql = "UPDATE PESSOA SET nome=?, login=?, senha=? WHERE id_pessoa=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pessoa.getNome());
			preparador.setString(2, pessoa.getLogin());
			preparador.setString(3, pessoa.getSenha());
			preparador.setInt(4, pessoa.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(Pessoa pessoa){
		
		String sql = "DELETE FROM PESSOA WHERE id_pessoa=?";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setInt(1, pessoa.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Excluído com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Pessoa> buscaTodos(){
		
		String sql = "SELECT * FROM PESSOA";
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId(resultado.getInt("id_pessoa"));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setLogin(resultado.getString("login"));
				pessoa.setSenha(resultado.getString("senha"));
				
				listPessoa.add(pessoa);
			}
			
			preparador.close();
			
			System.out.println("Consultado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listPessoa;
	}
	
}
