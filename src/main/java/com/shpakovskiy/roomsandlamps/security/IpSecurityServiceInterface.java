package com.shpakovskiy.roomsandlamps.security;

import com.shpakovskiy.roomsandlamps.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IpSecurityServiceInterface {

    boolean checkEntrancePossibility(Room room, HttpServletRequest request, HttpServletResponse response);
}
