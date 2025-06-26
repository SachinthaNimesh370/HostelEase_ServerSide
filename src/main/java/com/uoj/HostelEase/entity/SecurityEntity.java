package com.uoj.HostelEase.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SecurityEntity {
    @Id
    private String security_id;
    @OneToOne
    @JoinColumn(name = "security_id", referencedColumnName = "regNo")
    private UserEntity user;

    @OneToMany(mappedBy = "security")
    @JsonManagedReference
    private List<VisitorEntity> visitor;


}
