package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pacote1.entidades.Aluno;

public class AlunoDAO {

	private Connection con = Conexao.getConnection();
	
	 public void cadastrar(Aluno pAluno){
		
		String sql = "INSERT INTO ALUNO (id_pessoa) values (?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pAluno.getId());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 
	public List<Aluno> buscarAlunosAnoMatricula(String pAno){
		
		String sql = "SELECT * FROM Aluno WHERE SUBSTRING(id_pessoa, 1, 4) = ?";
		
		List<Aluno> listAluno = new ArrayList<Aluno>();
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pAno);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				
				Aluno pessoa = new Aluno();
				
				pessoa.setId(resultado.getString("id_pessoa"));
				
				listAluno.add(pessoa);
			}
			
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listAluno;
	}
	
	public Aluno consultar(String pMatricula){
		
		String sql = "SELECT * FROM ALUNO ";
		String where = "WHERE ";
		String sql2 = "ID_PESSOA = ?";
		String sqlComplementar = "";
		Aluno aluno = null;
		int contador=0;
		try{
			if(pMatricula != null && !pMatricula.equals("")){
				sqlComplementar = sql2;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			if(pMatricula != null && !pMatricula.equals("")){
				contador++;
				preparador.setString(contador, pMatricula);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
				aluno = new Aluno();
				
				aluno.setId(resultado.getString("id_pessoa"));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return aluno;
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM Aluno WHERE id_pessoa=?";
		
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
