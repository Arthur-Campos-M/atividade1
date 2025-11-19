/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
    int status;
    
    try{
       prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?)");
       prep.setString(1, produto.getNome());
       prep.setInt(2, produto.getValor());
       prep.setString(3, produto.getStatus());
       status = prep.executeUpdate();
       return status;
    } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        
    }
    }
    
    public ProdutosDAO() {
    conn = new conectaDAO().connectDB();
}
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try{
          
           prep = conn.prepareStatement("SELECT * FROM produtos");
           resultset = prep.executeQuery();
           
           ArrayList<ProdutosDTO> listaProdutos = new ArrayList<>();
           
           while(resultset.next()) {
               ProdutosDTO produto = new ProdutosDTO();
               produto.setId(resultset.getInt("id"));
               produto.setNome(resultset.getString("nome"));
               produto.setValor(resultset.getInt("valor"));
               produto.setStatus(resultset.getString("status"));

               listaProdutos.add(produto);               
           }
           return listaProdutos;
      }  catch (Exception e) {
                    return null;
                }
            
    }
    
    public void venderProduto(int idProduto){
            try{
                prep = conn.prepareStatement("UPDATE produtos SET status = ? where id = ?");
                prep.setString(1, "vendido");
                prep.setInt(2, idProduto);
                prep.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto atualizado para Vendido com sucesso!");
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
            }
        }
  
}

