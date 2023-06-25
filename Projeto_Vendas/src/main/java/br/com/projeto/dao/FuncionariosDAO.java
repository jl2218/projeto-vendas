/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionarios;
import br.com.projeto.model.WebServiceCep;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
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
public class FuncionariosDAO {

    //CONEXÃO
    private Connection con;

    public FuncionariosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //METODO CADASTRAR FUNCIONARIO
    public void cadastrarFuncionarios(Funcionarios obj) {
        try {

            //1º PASSO - CRIAR O COMANDO SQL
            String sql = "insert into BDVENDAS.tb_funcionarios (nome, rg, cpf, email, senha, cargo, nivel_acesso, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            //2º PASSO - CONECTAR O BANCO DE DADOS E ORGANIZAR O COMANDO SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivelAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            //3º PASSO - EXECUTAR O COMANDO SQL
            stmt.execute();
            stmt.close();;

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }
    }
    //METODO ALTERAR FUNCIONARIO

    public void alterarFuncionario(Funcionarios obj) {

        try {

            //1º PASSO - CRIAR O COMANDO SQL
            String sql = "update BDVENDAS.tb_funcionarios set nome=?, rg=?, cpf=?, email=?, senha=?, cargo=?, nivel_acesso=?, telefone=?, celular=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?,"
                    + "cidade=?, estado=? where id =?";

            //2º PASSO - CONECTAR O BANCO DE DADOS E ORGANIZAR O COMANDO SQL
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getSenha());
            stmt.setString(6, obj.getCargo());
            stmt.setString(7, obj.getNivelAcesso());
            stmt.setString(8, obj.getTelefone());
            stmt.setString(9, obj.getCelular());
            stmt.setString(10, obj.getCep());
            stmt.setString(11, obj.getEndereco());
            stmt.setInt(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());

            stmt.setInt(17, obj.getId());

            //3º PASSO - EXECUTAR O COMANDO SQL
            stmt.execute();
            stmt.close();;

            JOptionPane.showMessageDialog(null, "Alterado com sucesso");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);

        }
    }

    //METODO EXCLUIR FUNCIONARIO
    public void excluirFuncionario(Funcionarios obj) {

        try {

            //1º PASSO - CRIAR O COMANDO SQL
            String sql = "delete from BDVENDAS.tb_funcionarios where id = ?";

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

//METODO LISTAR TODOS OS FUNCIONARIOS
    public List<Funcionarios> listarFuncionarios() {
        try {
            //CRIAR A LISTA
            List<Funcionarios> lista = new ArrayList<>();

            //CRIAR O SQL, ORGANIZAR E EXECUTAR
            String sql = "select * from BDVENDAS.tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);

            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    //METODO CONSULTA FUNCIONARIO POR NOME 
    public Funcionarios consultaPorNome(String nome) {
        try {
            String sql = "select * from BDVENDAS.tb_funcionarios where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Funcionarios obj = new Funcionarios();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado" + erro);
            return null;
        }

    }

    //METODO BUSCAR FUNCIONARIO POR NOME
    public List<Funcionarios> buscaFuncionarioPorNome(String nome) {
        try {
            //CRIAR A LISTA
            List<Funcionarios> lista = new ArrayList<>();

            //CRIAR O SQL, ORGANIZAR E EXECUTAR
            String sql = "select * from BDVENDAS.tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setCargo(rs.getString("cargo"));
                obj.setNivelAcesso(rs.getString("nivel_acesso"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }
            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }
    //BUSCA CEP

    public Funcionarios buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Funcionarios obj = new Funcionarios();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setUf(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descricao do erro: " + webServiceCep.getResultText());
            return null;
        }

    }

    //METODO EFETUA LOGIN
    public void efetuaLogin(String email, String senha) {
        try {

            // 1º PASSO - SQL
            String sql = "select* from BDVENDAS.tb_funcionarios where email = ? and senha = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                //USUARIO LOGOU

                //CASO O USUARIO SEJA ADMIN
                if (rs.getString("nivel_acesso").equals("Admin")) {
                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    FrmMenu tela = new FrmMenu();
                    tela.usuarioLogado = rs.getString("nome");
                    tela.setVisible(true);

                } else if (rs.getString("nivel_acesso").equals("Usuário")) {
                    JOptionPane.showMessageDialog(null, "Seja bem vindo ao Sistema");
                    FrmMenu tela = new FrmMenu();
                    tela.usuarioLogado = rs.getString("nome");

                    //DESABILITAR OS MENUS
                    tela.menu_posicao.setEnabled(false);
                    tela.menu_controle.setEnabled(false);
                    tela.setVisible(true);

                }

            } else {
                //DADOS INCORRETOS
                JOptionPane.showMessageDialog(null, "Dados incorretos");
                new FrmLogin().setVisible(true);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }

    }
}
