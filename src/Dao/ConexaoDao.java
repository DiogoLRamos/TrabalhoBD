/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raquel_dutra
 */
  
public class ConexaoDao {
    
    private static Connection conection;

    public static Connection getConnection() {
        
	try {
            if(conection == null || conection.isClosed()){
                Class.forName("com.mysql.jdbc.Driver");
                String url= "jdbc:mysql://localhost:3306/mydb";
                String user= "root"; 
                String senha= "";
           
                conection= DriverManager.getConnection(url,user ,senha);
                conection.setAutoCommit(false);
           
                Logger.getLogger(ConexaoDao.class.getName()).log(Level.INFO, "Conexão estabelecida.");
            }
	} catch (SQLException e) {
            throw new RuntimeException(e);
        }catch(ClassNotFoundException ex){
            
        }
        
        return conection;
    }
    
    public static void closedConection(){
        try{
            conection.commit();
            if(!conection.isClosed()){
            conection.close();
            }
        }catch(SQLException EX){
            try{
                conection.rollback();
            }catch(SQLException EX1){
                 Logger.getLogger(ConexaoDao.class.getName()).log(Level.INFO,
                         "Não foi possivel realizar um rollback.");
            }
        }
            
    }
}

      
