package se.alex.super_heroes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.alex.super_heroes.model.Hero;
import se.alex.super_heroes.repository.HeroRepository;

@CrossOrigin(origins = "http://localhost:3333")
@RestController
@RequestMapping("/api")
public class HeroController {

    @Autowired
    HeroRepository heroRepository;

    @GetMapping("/heroes")
    public ResponseEntity<List<Hero>> getAllHeroes() {
        try {
            List<Hero> heroes = heroRepository.findAll();
            if (heroes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(heroes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/heroes/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable("id") long id) {
        Optional<Hero> heroData = heroRepository.findById(id);
        if (heroData.isPresent()) {
            return new ResponseEntity<>(heroData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/heroes")
    public ResponseEntity<Hero> createHero(@RequestBody Hero hero) {
        try {
            Hero response = heroRepository.save(hero);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/heroes/{id}")
    public ResponseEntity<Hero> updateHero(@PathVariable("id") long id, @RequestBody Hero hero) {
        Optional<Hero> heroData = heroRepository.findById(id);
        if (heroData.isPresent()) {
            Hero updatedHero = heroData.get();
            updatedHero.setName(hero.getName());
            updatedHero.setDesc(hero.getDesc());
            updatedHero.setPowers(hero.getPowers());
            updatedHero.setStatus(hero.getStatus());
            return new ResponseEntity<>(heroRepository.save(updatedHero), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/heroes/{id}")
    public ResponseEntity<HttpStatus> deleteHero(@PathVariable("id") long id) {
        try {
            heroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}