package com.shpakovskiy.roomsandlamps.controllers;

import com.shpakovskiy.roomsandlamps.entity.Room;
import com.shpakovskiy.roomsandlamps.service.RoomsService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EntranceController {

    private final RoomsService roomsService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public EntranceController(RoomsService roomsService,
                              SimpMessagingTemplate simpMessagingTemplate) {
        this.roomsService = roomsService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RequestMapping("/enter/{roomId}")
    private ModelAndView enterRoom(@PathVariable int roomId, HttpServletRequest request) {
        System.out.println();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("room");
        modelAndView.addObject("room", roomsService.getRoomById(roomId));
        return modelAndView;
    }

    @MessageMapping("/switch")
    @SendTo("/room/switch-state")
    public void switchLampState(Room room) {
        room = roomsService.getRoomById(room.getId());
        roomsService.switchLampState(room);
        simpMessagingTemplate.convertAndSend("/room/switch-state/" + room.getId(), room);
    }
}
