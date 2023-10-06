package com.boleanuk.music.controller;

import com.boleanuk.music.model.Artist;
import com.boleanuk.music.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    private ArtistRepository artists;

    @GetMapping
    public List<Artist> getAllArtists() {
        return this.artists.findAll();
    }

    private Artist getArtist(int id) {
        return this.artists.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Artist> createAlbum(@RequestBody Artist album) {
        return new ResponseEntity<>(this.artists.save(album), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable int id, @RequestBody Artist artist) {
        Artist artistToUpdate = this.getArtist(id);
        if (artistToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist with this id was not found.");
        }
        try {
           artistToUpdate.setName(artist.getName());
           artistToUpdate.setMembers(artist.getMembers());
           artistToUpdate.setStillPerforming(artist.getStillPerforming());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update failed. Please check fields.");
        }
        return new ResponseEntity<>(this.artists.save(artistToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Artist> deleteArtist(@PathVariable int id) {
        Artist artistToDelete = this.getArtist(id);
        if (artistToDelete == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist with this id was not found.");
        }
        this.artists.delete(artistToDelete);
        return ResponseEntity.ok(artistToDelete);
    }
}
