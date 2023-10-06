package com.boleanuk.music.controller;

import com.boleanuk.music.model.Album;
import com.boleanuk.music.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albums;

    @GetMapping
    public List<Album> getAllAlbums() {
        return this.albums.findAll();
    }

    private Album getAlbum(int id) {
        return this.albums.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return new ResponseEntity<>(this.albums.save(album), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable int id, @RequestBody Album album) {
        Album albumToUpdate = this.getAlbum(id);
        if (albumToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with this id was not found.");
        }
        try {
            albumToUpdate.setTitle(album.getTitle());
            albumToUpdate.setYear(album.getYear());
            albumToUpdate.setRating(album.getRating());
            albumToUpdate.setArtist(album.getArtist());
            albumToUpdate.setRecordCompany(album.getRecordCompany());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update failed. Please check fields.");
        }
        return new ResponseEntity<>(this.albums.save(albumToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Album> deleteAlbum(@PathVariable int id) {
        Album albumToDelete = this.getAlbum(id);
        if (albumToDelete == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album with this id was not found.");
        }
        this.albums.delete(albumToDelete);
        return ResponseEntity.ok(albumToDelete);
    }
}
