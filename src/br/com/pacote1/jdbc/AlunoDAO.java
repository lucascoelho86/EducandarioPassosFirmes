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
		
		String sql = "SELECT * FROM Aluno WHERE id_pessoa = ?";
		
		Aluno aluno = null;
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pMatricula);
			
			ResultSet resultado = preparador.executeQuery();

			while(resultado.next()){
				aluno = new Aluno();
				aluno.setId(resultado.getString("id_pessoa"));
				
			}
			
			preparador.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aluno;
	}
	
}
