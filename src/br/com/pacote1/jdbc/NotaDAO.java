package br.com.pacote1.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pacote1.entidades.Aluno;
import br.com.pacote1.entidades.Nota;

public class NotaDAO {

	private Connection con = Conexao.getConnection();
	
	public void cadastrar(Nota pNota){
		
		String sql = "INSERT INTO NOTA (id_aluno, id_disciplina, id_tp_nota, nota, unidade) values (?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = con.prepareStatement(sql);
			
			preparador.setString(1, pNota.getIdAluno());
			preparador.setInt(2, pNota.getIdDisciplina());
			preparador.setInt(3, pNota.getIdTpNota());
			preparador.setDouble(4, pNota.getNota());
			preparador.setInt(5, pNota.getUnidade());
			
			preparador.execute();
			preparador.close();
			
			System.out.println("Cadastrado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Nota> consultar(String pMatricula, Integer pUnidade){
		
		String sql = "SELECT * FROM NOTA ";
		String where = "WHERE ";
		String sql2 = "ID_ALUNO = ?";
		String sql3 = "UNIDADE = ?";
		String sqlComplementar = "";
		String conector = "";
		Nota nota = null;
		List<Nota>listNotas = new ArrayList<Nota>();
		int contador=0;
		try{
			if(pMatricula != null && !pMatricula.equals("")){
				sqlComplementar = sql2;
				conector = "\n AND ";
			}
			
			if(pUnidade != null && pUnidade != 0){
				sqlComplementar = sqlComplementar + conector + sql3;
			}
			
			if(!sqlComplementar.equals("")){
				sql = sql + where + sqlComplementar;
			}
			
			PreparedStatement preparador = con.prepareStatement(sql);
			
			if(pMatricula != null && !pMatricula.equals("")){
				contador++;
				preparador.setString(contador, pMatricula);
			}
			
			if(pUnidade != null && pUnidade != 0){
				contador++;
				preparador.setInt(contador, pUnidade);
			}
			
			ResultSet resultado = preparador.executeQuery();
			
			while(resultado.next()){
				nota = new Nota();
				
				nota.setIdAluno(resultado.getString("id_aluno"));
				nota.setIdDisciplina(resultado.getInt("id_disciplina"));
				nota.setIdTpNota(resultado.getInt("id_tp_nota"));
				nota.setNota(resultado.getDouble("nota"));
				nota.setUnidade(resultado.getInt("unidade"));
				
				listNotas.add(nota);
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return listNotas;
	}
	
	public void excluir(String pId){
		
		String sql = "DELETE FROM NOTA WHERE id_aluno=?";
		
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
