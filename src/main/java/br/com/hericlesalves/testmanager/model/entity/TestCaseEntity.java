package br.com.hericlesalves.testmanager.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_test")
public class TestCaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 300, unique = true)
    private String title;

    @Column(nullable = false, length = 1000)
    public String steps;

    @Column(nullable = false, length = 500)
    public String expectedResult;

    @Column(nullable = true)
    public LocalDateTime deletedAt;

    public TestCaseEntity(String title, String steps, String expectedResult) {
        this.title = title;
        this.steps = steps;
        this.expectedResult = expectedResult;
    }
}
