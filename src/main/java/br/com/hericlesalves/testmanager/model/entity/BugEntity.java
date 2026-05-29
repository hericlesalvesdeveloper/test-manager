package br.com.hericlesalves.testmanager.model.entity;

import br.com.hericlesalves.testmanager.model.enums.BugStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_bug")
@NoArgsConstructor
@Getter
public class BugEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    private BugStatus status;

    @ManyToOne
    @JoinColumn(name = "change_id", nullable = false)
    private ChangeEntity change;

    public BugEntity(String description, ChangeEntity change) {
        this.description = description;
        this.status = BugStatus.OPEN;
    }

    public void close() {
        if(status == BugStatus.CLOSED) {
            throw new IllegalArgumentException("This bug is already close!");
        }
        this.status = BugStatus.CLOSED;
    }
}
