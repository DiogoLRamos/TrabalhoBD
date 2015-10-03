/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoDao {

    public void adicionar(Produto produto) {
        try {
            String sql = "insert into produto(valor,quantidade, descricao) values (?,?,?)";

            PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setDouble(1, produto.getValor());
            stmt.setInt(2, produto.getQuantidade());
             stmt.setString(3, produto.getDescricao());
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
               produto.setIdproduto(rs.getInt(1));
            }
        } catch (SQLException ex) {

        } finally {
            ConexaoDao.closedConection();
        }
    }

    //Remove produto	
    public void remover(int id) {
        try {
            String sql = "delete from produto where idProduto=?";
            PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException ex) {

        } finally {
            ConexaoDao.closedConection();
        }
    }

    //Altera produto
    public void alterar(Produto produto, int id) {
        try {
            String sql = "update produto set valor=?, quantidade=? , descricao=? where idproduto=?";

            PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql);
            stmt.setDouble(1, produto.getValor());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {

        } finally {
            ConexaoDao.closedConection();
        }
    }

    //Pesquisa produto
    public ArrayList<Produto> getLista() throws SQLException {
        String sql = "select * from produto";

        PreparedStatement stmt = ConexaoDao.getConnection().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        ArrayList<Produto> produtos = new ArrayList<Produto>();
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setIdproduto(rs.getInt("idprodutor"));
            produto.setValor(rs.getDouble("valor"));
            produto.setQuantidade(rs.getInt("quantidade"));
            produto.setDescricao(rs.getString("descricao"));
            produtos.add(produto);
        }
        ConexaoDao.closedConection();
        return produtos;
    }

}
