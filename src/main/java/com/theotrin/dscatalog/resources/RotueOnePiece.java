package com.theotrin.dscatalog.resources;

import com.theotrin.dscatalog.entities.Mugiwaras;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/onepiece")
public class RotueOnePiece {

    @GetMapping
    public ResponseEntity<List<Mugiwaras>> onePiece(){
        List<Mugiwaras> tripulation = new ArrayList<>();

        tripulation.add(new Mugiwaras(1L, "Luffy"));
        tripulation.add(new Mugiwaras(2L, "Zoro"));
        tripulation.add(new Mugiwaras(3L, "Nami"));

        return ResponseEntity.ok().body(tripulation);

    };

}
