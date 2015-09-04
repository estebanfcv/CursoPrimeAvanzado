package com.mitocode.controller;

import com.mitocode.ejb.UsuarioFacadeLocal;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author estebanfcv
 */
@Named
@ViewScoped
public class IndexController implements Serializable{

    @EJB
    private UsuarioFacadeLocal EJBUsuario;

    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public String iniciarSesion() {
        Usuario us;
        String redireccion = null;
        try {
           us= EJBUsuario.iniciarSesion(usuario);
            if(us!=null){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
               redireccion = "/protegido/principal?faces-redirect=true";  
//               redireccion = "/protegido/principal.xhtml";  
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Usuario incorrecto"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
        }
        return redireccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
