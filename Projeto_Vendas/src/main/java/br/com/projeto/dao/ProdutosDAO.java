/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
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
public class ProdutosDAO {

    private Connection con;

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //METODO CADASTRAR PRODUTOS
    public void cadastrarProdutos(Produtos obj) {
        try {

            String sql = "insert into BDVENDAS.tb_produtos (descricao, preco, qtd_estoque, for_id) values (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();;

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //METODO ALTERAR PRODUTO
    public void alterarProduto(Produtos obj) {

        try {

            //1º PASSO - CRIAR O COMANDO SQL
            String sql = "update BDVENDAS.tb_produtos set descricao=?, preco=?, qtd_estoque=?, for_id=? where id=?";

            //2º PASSO - CONECTAR O BANCO DE DADOS E ORGANIZAR O COMANDO SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());

            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.setInt(5, obj.getId());

            //3º PASSO - EXECUTAR O COMANDO SQL
            stmt.execute();
            stmt.close();;

            JOptionPane.showMessageDialog(null, "Alterado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }
    }

    //METODO EXCLUIR PRODUTO
    public void excluirProdutos(Produtos obj) {

        try {

            //1º PASSO - CRIAR O COMANDO SQL
            String sql = "delete from BDVENDAS.tb_produtos where id = ?";

            //2º PASSO - CONECTAR O BANCO DE DADOS E ORGANIZAR O COMANDO SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //3º PASSO - EXECUTAR O COMANDO SQL
            stmt.execute();
            stmt.close();;

            JOptionPane.showMessageDialog(null, "Excluído com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }
    }

    //METODO LISTAR PRODUTOS
    public List<Produtos> listarProdutos() {
        try {
            //CRIAR A LISTA
            List<Produtos> lista = new ArrayList<>();

            //CRIAR O SQL, ORGANIZAR E EXECUTAR
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from BDVENDAS.tb_produtos as p "
                    + "inner join BDVENDAS.tb_fornecedores as f on (p.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);

            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    //METODO CONSULTA PRODUTO POR NOME
    public Produtos consultaPorNome(String nome) {
        try {
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from BDVENDAS.tb_produtos as p "
                    + "inner join BDVENDAS.tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.preco"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado" + erro);
            return null;
        }

    }

    //METODO BUSCAR PRODUTO POR NOME
    public List<Produtos> buscaProdutoPorNome(String nome) {
        try {
            //CRIAR A LISTA
            List<Produtos> lista = new ArrayList<>();

            //CRIAR O SQL, ORGANIZAR E EXECUTAR
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from BDVENDAS.tb_produtos as p "
                    + "inner join BDVENDAS.tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
            //String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p "
            //  + "inner join tb_fornecedores as f on (p.for_id = f.id where p.descricao like ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    //METODO CONSULTA PRODUTO POR CODIGO
    public Produtos consultaPorCodigo(int id) {
        try {
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from BDVENDAS.tb_produtos as p "
                    + "inner join BDVENDAS.tb_fornecedores as f on (p.for_id = f.id) where p.id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.preco"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado" + erro);
            return null;
        }

    }

    //METODO QUE ATUALIZA O ESTOQUE
    public void atualizaEstoque(int id, int qtd_nova) {
        try {
            String sql = "update BDVENDAS.tb_produtos set qtd_estoque =? where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }

    //METODO QUE RETORNA O ESTOQUE ATUAL
    public int retornaEstoqueAtual(int id) {
        try {
            int qtd_estoque = 0;

            String sql = "SELECT qtd_estoque from BDVENDAS.tb_produtos where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                qtd_estoque = (rs.getInt("qtd_estoque"));

            }
            return qtd_estoque;

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }
    //METODO QUE ADICIONA AO ESTOQUE
    public void adicionarEstoque(int id, int qtd_nova) {
        try {
            String sql = "update BDVENDAS.tb_produtos set qtd_estoque =? where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
    }
}
