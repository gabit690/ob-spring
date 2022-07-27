package com.gabit.obpractice.controllers;

import com.gabit.obpractice.entities.VideogameEntity;
import com.gabit.obpractice.repositories.VideogameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/videogames")
public class VideogameController {

    private final Logger log = LoggerFactory.getLogger(VideogameController.class);

    @Autowired
    private VideogameRepository _repository;

    @PostMapping
    public ResponseEntity<VideogameEntity> create(@RequestBody VideogameEntity videogame) {

        if(videogame.getId() != null){

            log.warn("Try to CREATE a videogame with an Id");

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(this._repository.save(videogame));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideogameEntity> findOneById(@PathVariable Long id) {

        Optional<VideogameEntity> videogameOpt = this._repository.findById(id);

        if(videogameOpt.isPresent())
            return ResponseEntity.ok(videogameOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<VideogameEntity> findAll() {
        return this._repository.findAll();
    }

    @PutMapping
    public ResponseEntity<VideogameEntity> update(@RequestBody VideogameEntity videogame) {

        if(videogame.getId() == null){

            log.warn("Try to UPDATE a videogame with a non existent Id");

            return ResponseEntity.badRequest().build();
        }

        if(!this._repository.existsById(videogame.getId())){

            log.warn("Try to UPDATE a videogame with a non existent Id");

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(this._repository.save(videogame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VideogameEntity> delete(@PathVariable Long id) {

        if(!this._repository.existsById(id)){

            log.warn("Try to DELETE a videogame with a non existent Id");

            return ResponseEntity.notFound().build();
        }

        this._repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<VideogameEntity> deleteAll() {

        log.info("REST Request for DELETE all VIDEOGAMES");

        this._repository.deleteAll();

        return ResponseEntity.noContent().build();
    }
}
