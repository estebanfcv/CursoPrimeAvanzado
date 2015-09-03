/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.CategoriaFacadeLocal;
import com.mitocode.model.Categoria;
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
public class CategoriaController implements Serializable {

    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    private Categoria categoria;

    @PostConstruct
    public void init() {
        categoria = new Categoria();
    }

    public void registrar() {
        try {
            categoriaEJB.create(categoria);
        } catch (Exception e) {

            // mensaje prime grwol
        }
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
