package com.app.course.controllers;

import com.app.course.models.TakeWallet;
import com.app.course.repository.Response;
import com.app.course.service.TakeWallet.TakeWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/takeWallet")
public class TakeWalletController {
    @Autowired
    private TakeWalletService takeWalletService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(value = "is_get",required = false) Optional<Boolean> isGet) {

        if(isGet.isPresent())
            return Response.resultOk(takeWalletService.getTakeWalletByIsGet(isGet.get()));

        return Response.resultOk(takeWalletService.getAll());
    }



    @PostMapping("/take")
    public ResponseEntity<?> insert(@RequestParam(value = "user_id") long userId, @RequestBody TakeWallet takeWallet) {
        var response = takeWalletService.insertTakeWallet(userId, takeWallet);
        return Response.resultOk(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update( @RequestBody TakeWallet takeWallet) {
        var response = takeWalletService.updateTakeWallet(takeWallet);
        return Response.resultOk(response);
    }
}
