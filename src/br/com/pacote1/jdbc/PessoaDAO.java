package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pacote1.entidades.Pessoa;

public class PessoaDAO {

	private Connection con = Conexao.getConnection();
	
	public void cadastrar(Pessoa pessoa){
		
		String sql = "INSERT INTO PESSOA (id_pessoa, dt_nascimento, naturalidade, endereco, numero, bairro, cidade, estado, telefone, nome) values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pessoa.getId());
			preparador.setDate(2, Date.valueOf(pessoa.getDtNascimento()));
			preparador.setString(3, pessoa.getNaturalidade());
			preparador.setString(4, pessoa.getEndereco());
			preparador.setInt(5, pessoa.getNumero());
			preparador.setString(6, pessoa.getBairro());
			preparador.setString(7, pessoa.getCidade());
			preparador.setString(8, pessoa.getEstado());
			preparador.setString(9, pessoa.getTelefone());
			preparador.setString(10, pessoa.getNome());
			
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
			
//			preparador.setInt(1, pessoa.getId());
			
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
				
				listPessoa.add(pessoa);
			}
			
			preparador.close();
			
			System.out.println("Consultado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listPessoa;
	}
	
	public Pessoa buscarPorId(Integer pId){
		String sql = "SELECT * FROM PESSOA WHERE ID_PESSOA = ?";
		Pessoa pessoa = null;
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, pId);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				pessoa = new Pessoa();
				
//				pessoa.setId(resultado.getInt("id_pessoa"));
//				pessoa.setNome(resultado.getString("nome"));
//				pessoa.setLogin(resultado.getString("login"));
//				pessoa.setSenha(resultado.getString("senha"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return pessoa;
		
	}
	
	public Pessoa buscarPorIdSenha(String pLogin, String pSenha){
		String sql = "SELECT * FROM PESSOA WHERE LOGIN = ? AND SENHA = ?";
		Pessoa pessoa = null;
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pLogin);
			preparador.setString(2, pSenha);
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				pessoa = new Pessoa();
				
//				pessoa.setId(resultado.getInt("id_pessoa"));
//				pessoa.setNome(resultado.getString("nome"));
//				pessoa.setLogin(resultado.getString("login"));
//				pessoa.setSenha(resultado.getString("senha"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return pessoa;
		
	}
	
	public List<Pessoa> buscarPorNome(Integer pNome){
		String sql = "SELECT * FROM PESSOA nome like ?";
		List<Pessoa> lista = new ArrayList<Pessoa>();
		try{
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, "%" + pNome + "%");
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				Pessoa pessoa = new Pessoa();
				
//				pessoa.setId(resultado.getInt("id_pessoa"));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setNome(resultado.getString("login"));
				pessoa.setNome(resultado.getString("senha"));
				
				lista.add(pessoa);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return lista;
		
	}	
	
}
