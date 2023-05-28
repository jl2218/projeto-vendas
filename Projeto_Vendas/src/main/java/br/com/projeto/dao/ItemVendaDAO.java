/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.ItemVenda;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joaop
 */
public class ItemVendaDAO {

    private Connection con;

    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //METODO QUE CADASTRA ITENS
    public void cadastraItem(ItemVenda obj) {
        try {
            //1º PASSO - CRIAR O COMANDO SQL
            String sql = "insert into tb_itensvendas (venda_id, produto_id, qtd, subtotal)"
                    + "values (?,?,?,?)";

            //2º PASSO - CONECTAR O BANCO DE DADOS E ORGANIZAR O COMANDO SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            //3º PASSO - EXECUTAR O COMANDO SQL
            stmt.execute();
            stmt.close();;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }
    }
    
    //METODO QUE LISTA ITENS DE UMA VENDA POR ID
    public List<ItemVenda> listarItensPorVenda(int venda_id) {
        try {
            //CRIAR A LISTA
            List<ItemVenda> lista = new ArrayList<>();

            //CRIAR O SQL, ORGANIZAR E EXECUTAR
            String sql = "select p.descricao, i.qtd, p.preco, i.subtotal from tb_itensvendas as i " 
                    + "inner join tb_produtos as p on(i.produto_id = p.id) where venda_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();
                
                prod.setDescricao(rs.getString("p.descricao"));                
                item.setQtd(rs.getInt("i.qtd"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));      
                
                item.setProduto(prod);

                lista.add(item);

            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }
        

    }

}
