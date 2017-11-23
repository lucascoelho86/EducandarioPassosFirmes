package br.com.educandariopassosfirmes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.educandariopassosfirmes.entidades.Pessoa;

public class PessoaDAO extends Conexao{

	public void incluir(Pessoa pessoa){
		
		String sql = "INSERT INTO PESSOA (ID_PESSOA, NOME, DT_NASCIMENTO, NATURALIDADE, CEP, ENDERECO, NUMERO, BAIRRO, CIDADE, ESTADO, TELEFONE, IDENTIDADE) values (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pessoa.getId());
			preparador.setString(2, pessoa.getNome());
			preparador.setDate(3, pessoa.getDtNascimento());
			preparador.setString(4, pessoa.getNaturalidade());
			preparador.setString(5, pessoa.getCep());
			preparador.setString(6, pessoa.getEndereco());
			preparador.setInt(7, pessoa.getNumero());
			preparador.setString(8, pessoa.getBairro());
			preparador.setString(9, pessoa.getCidade());
			preparador.setString(10, pessoa.getEstado());
			preparador.setString(11, pessoa.getTelefone());
			preparador.setString(12, pessoa.getIdentidade());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(Pessoa pessoa){
		
		String sql = "UPDATE PESSOA SET NOME=?, DT_NASCIMENTO=?, NATURALIDADE=?, CEP=?, ENDERECO=?, NUMERO=?, BAIRRO=?, CIDADE=?, ESTADO=?, TELEFONE=?, IDENTIDADE=? WHERE ID_PESSOA=?";
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
			preparador.setString(1, pessoa.getNome());
			preparador.setDate(2, pessoa.getDtNascimento());
			preparador.setString(3, pessoa.getNaturalidade());
			preparador.setString(4, pessoa.getCep());
			preparador.setString(5, pessoa.getEndereco());
			preparador.setInt(6, pessoa.getNumero());
			preparador.setString(7, pessoa.getBairro());
			preparador.setString(8, pessoa.getCidade());
			preparador.setString(9, pessoa.getEstado());
			preparador.setString(10, pessoa.getTelefone());
			preparador.setString(11, pessoa.getIdentidade());
			preparador.setString(12, pessoa.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM PESSOA WHERE id_pessoa=?";
		
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
	
	public List<Pessoa> buscaTodos(){
		
		String sql = "SELECT * FROM PESSOA";
		List<Pessoa> listPessoa = new ArrayList<Pessoa>();
		
		try {
			PreparedStatement preparador = getPreparedStatement(sql);
			
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
	
	public List<Pessoa> buscarPorNome(Integer pNome){
		String sql = "SELECT * FROM PESSOA nome like ?";
		List<Pessoa> lista = new ArrayList<Pessoa>();
		try{
			PreparedStatement preparador = getPreparedStatement(sql);
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
	
	public ArrayList<Pessoa> consultar(String pId, String pNome){
		String sql = "SELECT * FROM PESSOA ";
		String where = "WHERE ";
		String sql2 = "ID_PESSOA = ?";
		String sql3 = "NOME LIKE ?";
		String conector = "";
		String sqlComplementar = "";
		Pessoa pessoa = null;
		ArrayList<Pessoa> listPessoa = new ArrayList<Pessoa>();
		int contador=0;
		try{
			
			if(pId != null && !pId.equals("")){
				sqlComplementar = sql2;
				conector = "\n AND ";
			}
			
			if(pNome != null && !pNome.equals("")){
				sqlComplementar = sqlComplementar + conector + sql3;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = getPreparedStatement(sql);
			
			if(pId != null && !pId.equals("")){
				contador++;
				preparador.setString(contador, pId);
			}
			
			if(pNome != null && !pNome.equals("")){
				contador++;
				preparador.setString(contador, "%" + pNome + "%");
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				pessoa = new Pessoa();
				
				pessoa.setId(resultado.getString("ID_PESSOA"));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setDtNascimento(resultado.getDate("DT_NASCIMENTO"));
				pessoa.setNaturalidade(resultado.getString("NATURALIDADE"));
				pessoa.setCep(resultado.getString("CEP"));
				pessoa.setEndereco(resultado.getString("ENDERECO"));
				pessoa.setNumero(resultado.getInt("NUMERO"));
				pessoa.setBairro(resultado.getString("BAIRRO"));
				pessoa.setCidade(resultado.getString("CIDADE"));
				pessoa.setEstado(resultado.getString("ESTADO"));
				pessoa.setTelefone(resultado.getString("TELEFONE"));
				pessoa.setIdentidade(resultado.getString("IDENTIDADE"));
				
				
				listPessoa.add(pessoa);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return listPessoa;
		
	}
	
}
