package daos;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Usuario;

public class usuarioDAO {
    
    public void save(Usuario usuario){
        String sql = "INSERT INTO USUARIO(username, nome, senha, funcao, status_user) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,usuario.getUsername());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3,usuario.getSenha());
            pstm.setString(4, usuario.getCargo());
            pstm.setString(5,usuario.getStatus_user());
            
            pstm.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
     

    public void delete(Usuario usuario){
        String sql = "DELETE FROM USUARIOS "+"WHERE username=?";
        Connection conn = null;
        PreparedStatement pstm = null; 
        
        try{
            conn = Conexao.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,usuario.getUsername());
            
            pstm.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        
    }
    
        public Usuario recuperarUsuario(Usuario usuario){
        String sql = "SELECT * FROM 2rp.usuario WHERE username = ? and senha = ?";
        usuario = new Usuario();
        Connection conn = null;
        PreparedStatement pstm = null; 
        ResultSet rset = null;
        try {
            conn = Conexao.createConnectionToMySQL();	
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1,usuario.getUsername());
            pstm.setString(2,usuario.getSenha());
            rset = pstm.executeQuery();
            if (rset.next()) {
            
            usuario.setUsername(rset.getString("username"));
            usuario.setSenha(rset.getString("senha"));
            // outros atributos
        }
            }catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}
					
					if(pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
                        System.out.println(usuario);
			return usuario;
	
        }

        
    public List<Usuario> getUsuarios(){
		
		String sql = "SELECT * FROM 2rp.usuario";
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT****
		ResultSet rset = null;
		
		try {
			conn = Conexao.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Usuario usuario = new Usuario();
				
				
				usuario.setUsername(rset.getString("username"));
				usuario.setNome(rset.getString("nome"));
                                usuario.setSenha(rset.getString("senha"));
				usuario.setCargo(rset.getString("funcao"));
				usuario.setStatus(rset.getString("status_user"));
				
				usuarios.add(usuario);
				
			}
		}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}
					
					if(pstm!=null) {
						pstm.close();
					}
					
					if(conn!=null) {
						conn.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
                        System.out.println(usuarios);
			return usuarios;
	}
}