package com.boleanuk.music.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer artistId;
    private String name;
    private Integer members;
    private Boolean stillPerforming;
    @OneToMany(mappedBy = "artist")
    @JsonIgnoreProperties({"artist", "recordCompany"})
    private List<Album> albums;
}
