package com.shpakovskiy.roomsandlamps.controllers;

import com.shpakovskiy.roomsandlamps.entity.Room;
import com.shpakovskiy.roomsandlamps.security.IpSecurityService;
import com.shpakovskiy.roomsandlamps.service.RoomsService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class EntranceController {

    private final RoomsService roomsService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IpSecurityService ipSecurityService;

    public EntranceController(RoomsService roomsService,
                              SimpMessagingTemplate simpMessagingTemplate, IpSecurityService ipSecurityService) {
        this.roomsService = roomsService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.ipSecurityService = ipSecurityService;
    }

    @RequestMapping("/enter/{roomId}")
    private ModelAndView enterRoom(@PathVariable int roomId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        ipSecurityService.checkEntrancePossibility(roomsService.getRoomById(roomId), request, response);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("room");
        modelAndView.addObject("room", roomsService.getRoomById(roomId));
        return modelAndView;
    }

    @RequestMapping("/enter/access-denied")
    private ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("access-denied");
        return modelAndView;
    }

    @MessageMapping("/switch")
    //@SendTo("/room/switch-state")
    public void switchLampState(Room room) {
        room = roomsService.getRoomById(room.getId());
        roomsService.switchLampState(room);
        simpMessagingTemplate.convertAndSend("/room/switch-state/" + room.getId(), room);
    }
}
