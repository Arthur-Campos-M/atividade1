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


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        
    int status;
    
    try{
       prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES(?,?,?,?)");
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
    
    public List<ProdutosDTO> getFilmes(){
              
      try{
          
           prep = conn.prepareStatement("SELECT * FROM filmes");
           resultset = prep.executeQuery();
           
           List<ProdutosDTO> listaProdutos = new ArrayList<>();
           
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
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}

