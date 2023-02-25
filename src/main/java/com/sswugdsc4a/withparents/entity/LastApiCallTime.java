package com.sswugdsc4a.withparents.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LastApiCallTime {

    @Id
    private Long userId;

    private LocalDateTime lastApiCallTime;

}
