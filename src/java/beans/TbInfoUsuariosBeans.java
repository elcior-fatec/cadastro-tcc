package beans;

import dao.TbInfoUsuariosDAO;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.TbInfoUsuarios;

/**
 *
 * @author elcior.carvalho
 */
@ManagedBean
@RequestScoped
public class TbInfoUsuariosBeans extends Exception { 
    
    private TbInfoUsuarios infoUsuario;
    private List<TbInfoUsuarios> infoUsuarios;
    private TbInfoUsuariosDAO dao;
    
    public TbInfoUsuariosBeans() {        
        infoUsuario = new TbInfoUsuarios();        
        dao = new TbInfoUsuariosDAO(javax.persistence.Persistence.createEntityManagerFactory("cadastro-tccPU"));
    }
    
    public TbInfoUsuarios getInfoUsuario() {
        return infoUsuario;
    }
    
    public void setInfoUsuario(TbInfoUsuarios infoUsuario) {
        this.infoUsuario = infoUsuario;
    }
    
    public List<TbInfoUsuarios> getInfoUsuarios() {
       this.infoUsuarios = dao.findTbInfoUsuariosEntities();
       return infoUsuarios;
    }
    
    public String inserir() {
        Calendar hoje = Calendar.getInstance();
        infoUsuario.setDtCadastro(hoje.getTime());
        dao.create(infoUsuario);
        return "cadastro-de-acesso";
    }
    
    public void consultar(int id) {
        this.infoUsuario = dao.findTbInfoUsuarios(id);
    }
    
    /*Elcior - 16/11/18*/
    public TbInfoUsuarios retornarconsulta(int id) {
        this.consultar(id);
        return this.infoUsuario;
    }
    
    public void alterar() {
        try {
            dao.edit(infoUsuario);
        } catch (Exception ex) {
            Logger.getLogger(TbInfoUsuariosBeans.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TbInfoUsuarios> listar() {
        return dao.findTbInfoUsuariosEntities();
    }
}
