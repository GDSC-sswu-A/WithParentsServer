package com.sswugdsc4a.withparents.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SequenceGenerator(
        name="FAMILY_SEQ_GEN",
        sequenceName="FAMILY_SEQ",
        initialValue=1000,
        allocationSize=1
)
public class Family {

    @Id
    @Column(name = "family_id")
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="FAMILY_SEQ_GEN"
    )
    private Long id;

    @Column(nullable = false, length = 30)
    private String password;

    @CreatedDate
    private LocalDateTime createdDate;

    @OneToOne
    @JoinColumn(name = "creator_id")
    private User creator;

}