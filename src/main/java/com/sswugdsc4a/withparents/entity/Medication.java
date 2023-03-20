package com.sswugdsc4a.withparents.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @Column(name = "medication_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 100)
    private String description;

    @Column(length = 7)
    private String dayOfTheWeekList;

    @ElementCollection
    @CollectionTable(name = "medication_dosing_time", joinColumns = @JoinColumn(name = "medicationId"))
    private List<MedicationDosingTime> dosingTimeList = new ArrayList<>();

    private Boolean notificationStatus;

}
