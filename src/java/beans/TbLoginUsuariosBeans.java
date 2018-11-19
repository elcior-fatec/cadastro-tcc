package beans;

import dao.TbLoginUsuariosDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.TbInfoUsuarios;
import models.TbLoginUsuarios;

/**
 *
 * @author elcior.carvalho
 */
@ManagedBean
@RequestScoped
public class TbLoginUsuariosBeans extends Exception {
    private TbLoginUsuarios loginUsuario;
    private List<TbLoginUsuarios> loginUsuarios;
    private TbLoginUsuariosDAO dao;
    
    public TbLoginUsuariosBeans() {
        loginUsuario = new TbLoginUsuarios();
        dao = new TbLoginUsuariosDAO(javax.persistence.Persistence.createEntityManagerFactory("cadastro-tccPU"));
    }
    
    public TbLoginUsuarios getLoginUsuario() {
        return loginUsuario;
    }
    
    public void setLoginUsuario(TbLoginUsuarios infoUsuario) {
        this.loginUsuario = infoUsuario;
    }
    
    public List<TbLoginUsuarios> getLoginUsuarios() {
       this.loginUsuarios = dao.findTbLoginUsuariosEntities();
       return loginUsuarios;
    }
    
    public String inserir() {
        loginUsuario.setIdUser(this.getUltimoInfoUsuario());
        loginUsuario.setTipoUsuario("Estudante"); //Inicia como Estudante
        loginUsuario.setStatusConta((short)1); //Inicia ativo
        dao.create(loginUsuario);
        return "login";
    }
    
    public void consultar(int id) {
        this.loginUsuario = dao.findTbLoginUsuarios(id);
    }
    
    /*Elcior - 16/11/18*/
    public TbLoginUsuarios retornarconsulta(int id) {
        this.consultar(id);
        return this.loginUsuario;
    }
    
    public void alterar() {
        try {
            dao.edit(loginUsuario);
        } catch (Exception ex) {
            Logger.getLogger(TbInfoUsuariosBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TbLoginUsuarios> listar() {
        return dao.findTbLoginUsuariosEntities();
    }
    
    public TbInfoUsuarios getUltimoInfoUsuario(){
        TbInfoUsuarios usuario = null;
        List<TbInfoUsuarios> infoUsuariosBean = new TbInfoUsuariosBeans().listar();
        for(TbInfoUsuarios infoUsuario : infoUsuariosBean){
            usuario = infoUsuario;
        }        
        return usuario;
    }
}

