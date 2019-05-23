/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stfc.entity;

import org.stfc.dto.Objects;
import org.stfc.dto.Users;

/**
 *
 * @author dongdv
 */
public class AuthenResponse {

    private Users user;
    private Objects menu;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Objects getMenu() {
        return menu;
    }

    public void setMenu(Objects menu) {
        this.menu = menu;
    }

}
