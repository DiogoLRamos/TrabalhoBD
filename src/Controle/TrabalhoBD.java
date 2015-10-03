/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.ProdutoDao;
import Dao.VendedorDao;
import Model.Produto;
import Model.Vendedor;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author raquel_dutra
 */
public class TrabalhoBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        
        Vendedor vendedor2 = new Vendedor();
        vendedor2.setNome("Raquel");
        vendedor2.setComissao(10.00);
        
        VendedorDao dao = new VendedorDao();
        
        dao.adicionar(vendedor2);
        JOptionPane.showMessageDialog(null, "USUARIO inserido");
        ArrayList<Vendedor>  listaVendedor = dao.getLista();
        
        String mensagem = "";
        for(int i=0; i < listaVendedor.size(); i ++){
            mensagem += listaVendedor.get(i).getNome()+"\n"+ listaVendedor.get(i).getComissao()+"\n";
        }
        JOptionPane.showMessageDialog(null, mensagem);
          
        vendedor2.setNome("Diogo");
        dao.alterar(vendedor2, 13);
        JOptionPane.showMessageDialog(null, "USUARIO alterado");
        
         String mensagem1 = "";
        for(int i=0; i < listaVendedor.size(); i ++){
            mensagem1 += listaVendedor.get(i).getNome()+"\n"+ listaVendedor.get(i).getComissao()+"\n";
        }
        JOptionPane.showMessageDialog(null, mensagem1);
        
         dao.remover(8);
         JOptionPane.showMessageDialog(null, "USUARIO removido");
         
         
         String mensagem2 = "";
        for(int i=0; i < listaVendedor.size(); i ++){
            mensagem2 += listaVendedor.get(i).getNome()+"\n"+ listaVendedor.get(i).getComissao()+"\n";
        }
        JOptionPane.showMessageDialog(null, mensagem2);
        
   
      
        
         
    }
    
}
