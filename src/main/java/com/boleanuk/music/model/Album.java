package com.boleanuk.music.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "albums")
public class Album{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumId;
    private String title;
    private Integer year;
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "artist_id")
    @JsonIgnoreProperties({"albums"})
    private Artist artist;
    @ManyToOne
    @JoinColumn(name = "record_company_id")
    private RecordCompany recordCompany;
}