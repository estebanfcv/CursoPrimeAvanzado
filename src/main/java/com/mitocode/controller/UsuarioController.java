/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.model.Persona;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ejb.EJB;

/**
 *
 * @author estebanfcv
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable{
    
    private Usuario usuario;
    private Persona persona;
    
    @PostConstruct
    public void init(){
    usuario= new Usuario();
    persona = new Persona();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Persona getPersona() {
        return persona;
    }
    
    
    
}
