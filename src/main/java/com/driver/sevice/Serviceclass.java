/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.driver.sevice;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;
import com.driver.repository.Repositoryclass;
import java.util.HashMap;
import java.util.List;

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
    public Integer addUser(User user)
    {
        return repository.addUser(user);
    }
    public String getHotelWithMostFacilities(){
    HashMap<String,Hotel> hotelDb= repository.getHotelWithMostFacilities();
    int facilities= 0;

        String hotelName = "";

        for(Hotel hotel:hotelDb.values()){

            if(hotel.getFacilities().size()>facilities){
                facilities = hotel.getFacilities().size();
                hotelName = hotel.getHotelName();
            }
            else if(hotel.getFacilities().size()==facilities){
                if(hotel.getHotelName().compareTo(hotelName)<0){
                    hotelName = hotel.getHotelName();
                }
            }
        }
        return hotelName;
    }
    
    public int bookARoom(Booking booking){
        return repository.bookARoom(booking);
    }
    public int getBookings(Integer aadharCard)
    {
        return repository.getBookings(aadharCard);
    }
    public Hotel updateFacilities(List<Facility> newFacilities,String hotelName)
    {
        return repository.updateFacilities(newFacilities, hotelName);
    }
    
    
    

    
}
