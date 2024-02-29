package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Matricula;

public class MatriculaDAO extends DAO {
    
    public MatriculaDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }
    
    
    public boolean insert(Matricula matricula) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "INSERT INTO matricula (cor, placa, ano, matricula) "
                       + "VALUES ('"+matricula.getTipo()+ "', " + matricula.getPlaca() + ", '"  
                       + matricula.getAno() + "', " + matricula.getMatricula() + ")";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }

    
    public Matricula get(String cor) {
        Matricula matricula = null;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM matricula WHERE cor= '" + cor + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);    
            if(rs.next()){            
                 matricula = new Matricula(rs.getString("cor"), rs.getInt("placa"), rs.getString("ano"), rs.getInt("matricula"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return matricula;
    }
    
    
    public List<Matricula> getAll() {
        return getByOrder("");
    }

    
    public List<Matricula> getByOrder(String orderBy) {   
    
        List<Matricula> matriculas = new ArrayList<Matricula>();
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM matricula" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);               
            while(rs.next()) {                 
                Matricula u = new Matricula(rs.getString("cor"), rs.getInt("placa"), rs.getString("ano"), rs.getInt("matricula"));
                matriculas.add(u);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return matriculas;
    }
    
    public List<Matricula> getTipoMasculino() {
		List<Matricula> Matriculas = new ArrayList<Matricula>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM matricula WHERE matricula.cor LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Matricula u = new Matricula(rs.getString("cor"), rs.getInt("placa"), rs.getString("ano"), rs.getInt("matricula"));
	            Matriculas.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Matriculas;
	}


    public boolean update(Matricula matricula) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "UPDATE matricula SET placa = " + matricula.getPlaca() + ", ano = '"  
                       + matricula.getAno() + "', matricula = " + matricula.getMatricula() 
                       + " WHERE cor = '" + matricula.getTipo() + "'";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    public boolean delete(String cor) {
        boolean status = false;
        try {  
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM matricula WHERE cor = '" + cor + "'";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {  
            throw new RuntimeException(u);
        }
        return status;
    }
    
    
    public boolean autenticar(int placa, String ano) {
        boolean resp = false;
        
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM matricula WHERE placa LIKE " + placa + " AND ano LIKE '" + ano  + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }   
}
