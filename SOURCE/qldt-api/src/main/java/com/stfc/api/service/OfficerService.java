/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stfc.api.service;

import com.stfc.api.dao.OfficerDAO;
import com.stfc.entity.Officers;
import com.stfc.utils.Constants;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dong.dv
 */
@Service
@Transactional(transactionManager = Constants.CONFIG.TRANSACTION)
public class OfficerService {

    @Autowired
    private OfficerDAO officerDAO;
    
    public Officers findOfficerByUsername(String username){
        return officerDAO.findOfficerByUsername(username);
    }
    public List<Officers> findOfficers(Officers officers){
        return officerDAO.findOfficers(officers);
    }

}
