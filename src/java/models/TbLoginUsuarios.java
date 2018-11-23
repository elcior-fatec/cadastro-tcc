package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author elcior.carvalho
 */
@Entity
@Table(name = "tb_login_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbLoginUsuarios.findAll", query = "SELECT t FROM TbLoginUsuarios t")
    , @NamedQuery(name = "TbLoginUsuarios.findByIdLogin", query = "SELECT t FROM TbLoginUsuarios t WHERE t.idLogin = :idLogin")
    , @NamedQuery(name = "TbLoginUsuarios.findByUserName", query = "SELECT t FROM TbLoginUsuarios t WHERE t.userName = :userName")
    , @NamedQuery(name = "TbLoginUsuarios.findByUserPass", query = "SELECT t FROM TbLoginUsuarios t WHERE t.userPass = :userPass")
    , @NamedQuery(name = "TbLoginUsuarios.findByTipoUsuario", query = "SELECT t FROM TbLoginUsuarios t WHERE t.tipoUsuario = :tipoUsuario")
    , @NamedQuery(name = "TbLoginUsuarios.findByStatusConta", query = "SELECT t FROM TbLoginUsuarios t WHERE t.statusConta = :statusConta")})
public class TbLoginUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_login")
    private Integer idLogin;
    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;
    @Basic(optional = false)
    @Column(name = "user_pass")
    private String userPass;
    @Basic(optional = false)
    @Column(name = "tipo_usuario")
    private String tipoUsuario; // Estudante, Orientador, Administrador ou Super-Administrador
    @Column(name = "status_conta")
    private Short statusConta; // 0:inativo ou 1:ativo
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private TbInfoUsuarios idUser;

    public TbLoginUsuarios() {
    }

    public TbLoginUsuarios(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public TbLoginUsuarios(Integer idLogin, String userName, String userPass, String tipoUsuario) {
        this.idLogin = idLogin;
        this.userName = userName;
        this.userPass = userPass;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Short getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(Short statusConta) {
        this.statusConta = statusConta;
    }

    public TbInfoUsuarios getIdUser() {
        return idUser;
    }

    public void setIdUser(TbInfoUsuarios idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbLoginUsuarios)) {
            return false;
        }
        TbLoginUsuarios other = (TbLoginUsuarios) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbLoginUsuarios[ idLogin=" + idLogin + " ]";
    }    
}
