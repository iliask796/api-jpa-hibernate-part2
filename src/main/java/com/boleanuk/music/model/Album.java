package com.boleanuk.music.model;

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
    private Integer artistId;
    private Integer recordCompanyId;
}