package br.edu.iftm.jsf.dao;

import br.edu.iftm.jsf.entity.Usuario;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
            Statement st = conexao.createStatement();
            String insert = "insert into usuario (nome, email, senha)";
            insert += " values ('"+usuario.getNome()
                    + "', '" + usuario.getEmail() + "', '"
                    + usuario.getSenha()+"')";
            st.execute(insert);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Usuario> listar(){
        return null;
    }
    
}
