/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.driver.sevice;

import com.driver.model.Hotel;
import org.springframework.stereotype.Service;
import com.driver.repository.Repositoryclass;

/**
 *
 * @author INDRAJITH
 */
@Service
public class Serviceclass {
    Repositoryclass repository=new Repositoryclass();
    
    public String addHotel(Hotel hotel)
    {
        boolean ans=repository.addHotel(hotel);
        if(ans){return "SUCCESS";}
        else {return "FAILURE";}
    }
    
}
