package com.example.socksmarket.controller;

import com.example.socksmarket.dto.SockRequest;
import com.example.socksmarket.exception.InSufficientSockQuantityException;
import com.example.socksmarket.exception.InvalidSockRequestException;
import com.example.socksmarket.model.Color;
import com.example.socksmarket.model.Size;
import com.example.socksmarket.model.Sock;
import com.example.socksmarket.service.SockService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "Добавить товар на склад",
            description = "Добавляем товар в формате json"
    )
    @PostMapping
    public void addSocks(@RequestBody SockRequest sockRequest) {

        sockService.addSock(sockRequest);
    }

    @Operation(
            summary = "Регистрирует отпуск товара со склада "
    )
    @PutMapping
    public void issueSocks(@RequestBody SockRequest sockRequest) {
        sockService.issueSock(sockRequest);
    }

    @Operation(
            summary = "Получить данные о товаре",
            description = "Можно использовать не все параметры запроса"
    )
    @GetMapping
    public int getSocksCount(@RequestParam(required = false, name = "color") Color color,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return sockService.getSockQuantity(color, size, cottonMin, cottonMax);
    }

    @Operation(
            summary = "Списать бракованный товар на со склада"
    )
    @DeleteMapping
    public void removeDefectiveSocks(@RequestBody SockRequest sockRequest) {
        sockService.removeDefectiveSocks(sockRequest);
    }
}
