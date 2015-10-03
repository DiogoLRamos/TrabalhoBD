/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Vendedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author raquel_dutra
 */
  
  
public class VendedorDao { 

    public void adicionar(Vendedor vendedor) {
	try{
            String sql = "insert into vendedor(nome,comissao) values (?,?)";
	        
            PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,vendedor.getNome());
            stmt.setDouble(2,vendedor.getComissao());
            stmt.execute();
             
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                vendedor.setIdVendedor(rs.getInt(1));
            }
        }catch(SQLException ex){
            
        }finally{
            ConexaoDao.closedConection();
        }
    }
    
    //Remove vendedor	
    public void remover(int id) {
        try{
	String sql = "delete from vendedor where idvendedor=?";
        PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql);
	stmt.setInt(1, id);
	stmt.execute();
        
        }catch(SQLException ex){
            
        }finally{
            ConexaoDao.closedConection();
        }
    }
			
    //Altera vendedor
    public void alterar(Vendedor vendedor, int id) {
        try{
	String sql = "update vendedor set nome=?, comissao=? where idvendedor=?";
				 
	PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql);
	stmt.setString(1, vendedor.getNome());
	stmt.setDouble(2, vendedor.getComissao());
        stmt.setInt(3, id);
	stmt.executeUpdate();
        }catch(SQLException ex){
            
        }finally{
             ConexaoDao.closedConection();
        }
    }
				
				
    //Pesquisa vendedor
    public ArrayList<Vendedor> getLista() throws SQLException{
	String sql = "select * from vendedor";
				
	PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql);
	ResultSet rs = stmt.executeQuery();

	ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	while (rs.next()) {
        	Vendedor vendedor = new Vendedor();
		vendedor.setIdVendedor(rs.getInt("idvendedor"));
		vendedor.setNome(rs.getString("nome"));
		vendedor.setComissao(rs.getDouble("comissao"));
                vendedores.add(vendedor);
	}
        ConexaoDao.closedConection();
	return vendedores;
    }

}


 
          
		   

