package com.boleanuk.music.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "record_companies")
public class RecordCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordCompanyId;
    private String name;
    private String location;
    private String email;
}
