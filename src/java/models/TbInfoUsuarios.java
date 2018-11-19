package models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author elcior.carvalho
 */
@Entity
@Table(name = "tb_info_usuarios", catalog = "proj_tcc", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbInfoUsuarios.findAll", query = "SELECT t FROM TbInfoUsuarios t")
    , @NamedQuery(name = "TbInfoUsuarios.findByIdUser", query = "SELECT t FROM TbInfoUsuarios t WHERE t.idUser = :idUser")
    , @NamedQuery(name = "TbInfoUsuarios.findByNome", query = "SELECT t FROM TbInfoUsuarios t WHERE t.nome = :nome")
    , @NamedQuery(name = "TbInfoUsuarios.findBySobrenome", query = "SELECT t FROM TbInfoUsuarios t WHERE t.sobrenome = :sobrenome")
    , @NamedQuery(name = "TbInfoUsuarios.findByEmail", query = "SELECT t FROM TbInfoUsuarios t WHERE t.email = :email")
    , @NamedQuery(name = "TbInfoUsuarios.findByInstituicaoVinculada", query = "SELECT t FROM TbInfoUsuarios t WHERE t.instituicaoVinculada = :instituicaoVinculada")
    , @NamedQuery(name = "TbInfoUsuarios.findByDtCadastro", query = "SELECT t FROM TbInfoUsuarios t WHERE t.dtCadastro = :dtCadastro")
    , @NamedQuery(name = "TbInfoUsuarios.findByTitulacao", query = "SELECT t FROM TbInfoUsuarios t WHERE t.titulacao = :titulacao")})
public class TbInfoUsuarios implements Serializable {

    @OneToMany(mappedBy = "idUser")
    private Collection<TbLoginUsuarios> tbLoginUsuariosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "sobrenome")
    private String sobrenome;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "instituicao_vinculada")
    private String instituicaoVinculada;
    @Column(name = "dt_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dtCadastro;
    @Column(name = "titulacao")
    private String titulacao;

    public TbInfoUsuarios() {
    }

    public TbInfoUsuarios(Integer idUser) {
        this.idUser = idUser;
    }

    public TbInfoUsuarios(Integer idUser, String nome, String sobrenome, String email, String instituicaoVinculada) {
        this.idUser = idUser;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.instituicaoVinculada = instituicaoVinculada;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstituicaoVinculada() {
        return instituicaoVinculada;
    }

    public void setInstituicaoVinculada(String instituicaoVinculada) {
        this.instituicaoVinculada = instituicaoVinculada;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbInfoUsuarios)) {
            return false;
        }
        TbInfoUsuarios other = (TbInfoUsuarios) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.TbInfoUsuarios[ idUser=" + idUser + " ]";
    }

    @XmlTransient
    public Collection<TbLoginUsuarios> getTbLoginUsuariosCollection() {
        return tbLoginUsuariosCollection;
    }

    public void setTbLoginUsuariosCollection(Collection<TbLoginUsuarios> tbLoginUsuariosCollection) {
        this.tbLoginUsuariosCollection = tbLoginUsuariosCollection;
    }
    
}
