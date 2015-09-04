/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitocode.controller;

import com.mitocode.ejb.MenuFacadeLocal;
import com.mitocode.model.Menu;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author estebanfcv
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    @EJB
    private MenuFacadeLocal EJBMenu;
    private List<Menu> lista;
    private MenuModel model;

    @PostConstruct
    public void init() {
        listarMenus();
        model = new DefaultMenuModel();
        establecerPermisos();
    }

    public void listarMenus() {
        try {
            lista = EJBMenu.findAll();
        } catch (Exception e) {
            // mensaje jsf
        }
    }

    public void establecerPermisos() {
        Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        for (Menu m : lista) {
            if (m.getTipo().equals("S") && m.getTipoUsuario().equals(u.getTipo())) {
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                for (Menu i : lista) {
                    Menu subMenu = i.getSubmenu();
                    if (subMenu != null) {
                        if (subMenu.getCodigo() == m.getCodigo()) {
                            DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
                            item.setUrl(i.getUrl());
                            firstSubmenu.addElement(item);
                        }
                    }
                }
                model.addElement(firstSubmenu);
            } else {
                if (m.getSubmenu() == null && m.getTipoUsuario().equals(u.getTipo())) {
                    DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                    item.setUrl(m.getUrl());
                    model.addElement(item);
                }
            }
        }
    }
    
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public MenuModel getModel() {
        return model;
    }

}
