/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.driver.repository;

import com.driver.model.Hotel;
import java.util.HashMap;
import org.springframework.stereotype.Repository;

/**
 *
 * @author INDRAJITH
 */
@Repository
public class Repositoryclass {
    HashMap<String,Hotel> hotelDB=new HashMap<>();
    
    public boolean addHotel(Hotel hotel)
    {
        hotelDB.put(hotel.getHotelName(),hotel);
        return true;
    }
    
}
