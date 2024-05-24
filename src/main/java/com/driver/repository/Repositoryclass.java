/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Repository;

/**
 *
 * @author INDRAJITH
 */
@Repository
public class Repositoryclass {
     private HashMap<String,Hotel> hotelDb = new HashMap<>();

    private HashMap<Integer, User> userDb = new HashMap<>();

    private HashMap<String,Booking> bookingDb = new HashMap<>();

    private HashMap<Integer,Integer> countOfBookings = new HashMap<>();
    
    public boolean addHotel(Hotel hotel)
    {
         if(hotelDb.containsKey(hotel.getHotelName())){
            return false;
        }
        hotelDb.put(hotel.getHotelName(),hotel);
        return true;
    }
    
    public Integer addUser(User user)
    {
        userDb.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
        
    }
    public HashMap<String,Hotel> getHotelWithMostFacilities(){

        //Out of all the hotels we have added so far, we need to find the hotelName with most no of facilities
        //Incase there is a tie return the lexicographically smaller hotelName
        //Incase there is not even a single hotel with atleast 1 facility return "" (empty string)
        
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
        return hotelDb;
    }
    public int bookARoom(Booking booking){
         String key = UUID.randomUUID().toString();

        booking.setBookingId(key);

        String hotelName = booking.getHotelName();

        Hotel hotel = hotelDb.get(hotelName);

        int availableRooms = hotel.getAvailableRooms();

        if(availableRooms<booking.getNoOfRooms()){
            return -1;
        }

        int amountToBePaid = hotel.getPricePerNight()*booking.getNoOfRooms();
        booking.setAmountToBePaid(amountToBePaid);

        //Make sure we check this part of code as well
        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());

        bookingDb.put(key,booking);

        hotelDb.put(hotelName,hotel);

        int aadharCard = booking.getBookingAadharCard();
        Integer currentBookings = countOfBookings.get(aadharCard);
        countOfBookings.put(aadharCard, Objects.nonNull(currentBookings)?1+currentBookings:1);
        return amountToBePaid;
    }
    
    public int getBookings(Integer aadharCard)
    {
        return countOfBookings.get(aadharCard);
    }
    public Hotel updateFacilities(List<Facility> newFacilities,String hotelName)
    {
        List<Facility> oldFacilities = hotelDb.get(hotelName).getFacilities();

        for(Facility facility: newFacilities){

            if(oldFacilities.contains(facility)){
                continue;
            }else{
                oldFacilities.add(facility);
            }
        }

        Hotel hotel = hotelDb.get(hotelName);
        hotel.setFacilities(oldFacilities);

        hotelDb.put(hotelName,hotel);

        return hotel;
    }
    
}
