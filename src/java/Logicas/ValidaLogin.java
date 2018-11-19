package Logicas;

import beans.TbLoginUsuariosBeans;
import dao.TbInfoUsuariosDAO;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.TbLoginUsuarios;

/**
 *
 * @author elcior.carvalho
 */
@ManagedBean
@RequestScoped
public class ValidaLogin extends HttpServlet {
    String userName;
    String userPass;
    int userId;
    private TbLoginUsuariosBeans loginUsuariosBeans;

    public ValidaLogin() {
        this.loginUsuariosBeans = new TbLoginUsuariosBeans();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public TbLoginUsuariosBeans getLoginUsuarioBeans() {
        return loginUsuariosBeans;
    }

    public void setLoginUsuarioBeans(TbLoginUsuariosBeans loginUsuarioBeans) {
        this.loginUsuariosBeans = loginUsuarioBeans;
    }
    
//    public boolean validar(){
//        List<TbLoginUsuarios> listaLoginUsuarios = loginUsuariosBeans.listar();
//        for(TbLoginUsuarios loginUsuario : listaLoginUsuarios){
//            if(loginUsuario.getUserName().equals(this.userName) && loginUsuario.getUserPass().equals(this.userPass) ){
//                userId = loginUsuario.getIdLogin();
//                return true;
//            }
//        }
//        return false;
//    }
//    
//    public void init(HttpServletRequest req, HttpServletResponse resp){
//        if(this.validar()){
//            req.getSession().setAttribute("userName", this.userName);
//            req.getSession().setAttribute("userId", this.userId);
//            try {
//                resp.sendRedirect("index");
//            } catch (IOException ex) {
//                Logger.getLogger(ValidaLogin.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            try {
//                resp.sendRedirect("cadastro-de-usuario");
//            } catch (IOException ex) {
//                Logger.getLogger(ValidaLogin.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    
    
}
