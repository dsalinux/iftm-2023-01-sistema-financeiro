package br.edu.iftm.jsf.dao;

import br.edu.iftm.jsf.entity.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO implements Serializable {
    
    public void salvar(Usuario usuario) {
        try {
            String url = "jdbc:mysql://localhost/sf_sistema_financeiro";
            String user = "root";
            String pass = "Daniloif123@";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao =
                    DriverManager.getConnection(url, user, pass);
            conexao.setAutoCommit(true);
            String query = "insert into usuario (nome, email, senha) values (?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getSenha());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Usuario> listar(){
        try {
            String url = "jdbc:mysql://localhost/sf_sistema_financeiro";
            String user = "root";
            String pass = "Daniloif123@";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao =
                    DriverManager.getConnection(url, user, pass);
            String query = "select * from usuario";
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
