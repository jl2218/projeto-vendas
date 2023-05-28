/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author joaop
 */
public class VendasDAO {

    private Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //CADASTRAR VENDA
    public void cadastrarVenda(Vendas obj) {
        try {
            //1º PASSO - CRIAR O COMANDO SQL
            String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda, observacoes)"
                    + "values (?,?,?,?)";

            //2º PASSO - CONECTAR O BANCO DE DADOS E ORGANIZAR O COMANDO SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            //3º PASSO - EXECUTAR O COMANDO SQL
            stmt.execute();
            stmt.close();;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }

    }

    //RETORNA A ULTIMA VENDA
    public int retornaUltimaVenda() {

        try {
            int idvenda = 0;
            String sql = "select max(id) id from tb_vendas";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();

                p.setId(rs.getInt("id"));

                idvenda = p.getId();
            }

            return idvenda;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
    //METODO LISTAR PRODUTOS

    public List<Vendas> listarVendasPorPeriodo(LocalDate data_inicio, LocalDate data_fim) {
        try {
            //CRIAR A LISTA
            List<Vendas> lista = new ArrayList<>();

            //CRIAR O SQL, ORGANIZAR E EXECUTAR
            String sql = "select v.id, date_format(v.data_venda, '%d/%m/%y') as data_formatada, c.nome, v.total_venda, v.observacoes from tb_vendas as v "
                    + "inner join tb_clientes as c on (v.cliente_id = c.id) where v.data_venda BETWEEN ? AND ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("observacoes"));
                
                obj.setCliente(c);

                lista.add(obj);

            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }
    
    //METODO QUE CALCULA TOTAL DA VENDA POR DATA
    public double retornaTotalVenda(LocalDate data_venda){
        try {
            double totalVenda = 0;
            
            String sql = "select sum(total_venda) as total from tb_vendas where data_venda = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, data_venda.toString());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                totalVenda = rs.getDouble("total");
            }
            
            return totalVenda;
        } catch (SQLException e) {
        throw new RuntimeException(e);
        }
        
        
    }

}
