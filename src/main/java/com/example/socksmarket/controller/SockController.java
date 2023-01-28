package com.example.socksmarket.controller;

import com.example.socksmarket.dto.SockRequest;
import com.example.socksmarket.exception.InSufficientSockQuantityException;
import com.example.socksmarket.exception.InvalidSockRequestException;
import com.example.socksmarket.model.Color;
import com.example.socksmarket.model.Size;
import com.example.socksmarket.model.Sock;
import com.example.socksmarket.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SockController {

    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }


    @ExceptionHandler(InvalidSockRequestException.class)
    public ResponseEntity<String> handleInvalidException(InvalidSockRequestException invalidSockRequestException) {
        return ResponseEntity.badRequest().body(invalidSockRequestException.getMessage());
    }

    @ExceptionHandler(InSufficientSockQuantityException.class)
    public ResponseEntity<String> handleInvalidException(InSufficientSockQuantityException inSufficientSockQuantityException) {
        return ResponseEntity.badRequest().body(inSufficientSockQuantityException.getMessage());
    }


    @PostMapping
    public void addSocks(@RequestBody SockRequest sockRequest) {

        sockService.addSock(sockRequest);
    }

    @PutMapping
    public void issueSocks(@RequestBody SockRequest sockRequest) {
        sockService.issueSock(sockRequest);
    }

    @GetMapping
    public int getSocksCount(@RequestParam(required = false, name = "color") Color color,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void removeDefectiveSocks(@RequestBody SockRequest sockRequest) {
        sockService.removeDefectiveSocks(sockRequest);
    }

    //data transfer object DTO -
    //создаем объект для запроса, отличается от модели
}
