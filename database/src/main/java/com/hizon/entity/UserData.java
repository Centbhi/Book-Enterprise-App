package com.hizon.entity;
import lombok.Data;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "User_data")
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true, nullable = false)
    String name;

    @JsonIgnore
    @Column(nullable = false)
    String password;
    boolean admin;
    
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date lastUpdated;

}