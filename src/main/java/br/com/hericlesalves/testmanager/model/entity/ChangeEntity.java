package br.com.hericlesalves.testmanager.model.entity;

import br.com.hericlesalves.testmanager.model.enums.ChangePriority;
import br.com.hericlesalves.testmanager.model.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tb_change")
public class ChangeEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull
    private Integer numberChange;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length =  250)
    private String client;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ChangePriority priority;

    @Enumerated(EnumType.STRING)
    private ChangeStatus status;

    @Column(nullable = true)
    private LocalDateTime deletedAt;

    public ChangeEntity(Integer numberChange, String name, String client, String description, ChangePriority priority) {
        this.numberChange = numberChange;
        this.name = name;
        this.client = client;
        this.description = description;
        this.priority = priority;
    }

    public void delete()
    {
        if(this.deletedAt != null || this.status == ChangeStatus.DONE) {
            throw new IllegalArgumentException("Change already deleted or change already finished");
        }
        this.deletedAt = LocalDateTime.now();
    }

    private void ensureNotDeleted()
    {
        if(this.deletedAt != null) {
            throw new IllegalStateException("change is deleted");
        }
    }

    public void changeCreated() {
        ensureNotDeleted();
        this.status = ChangeStatus.OPEN;
    }

    public void startChange() {
        ensureNotDeleted();
        if(this.status != ChangeStatus.OPEN ) {
            throw new IllegalStateException("This change needs to be open in order to initialize it");
        }

        this.status = ChangeStatus.IN_PROGRESS;
    }

    public void pauseChange() {
        ensureNotDeleted();
        if(this.status != ChangeStatus.IN_PROGRESS) {
            throw new IllegalStateException("The change is not in progress");
        }

        this.status = ChangeStatus.PAUSED;
    }

    public void doneChange() {
        ensureNotDeleted();
        if(this.status != ChangeStatus.IN_PROGRESS) {
            throw new IllegalStateException("The change needs to be in progress before it can be finalized.");
        }

        this.status = ChangeStatus.DONE;
    }

    public void closeChange() {
        ensureNotDeleted();
        if(this.status != ChangeStatus.DONE && this.status != ChangeStatus.OPEN) {
            throw new IllegalStateException("To close a change, its status must be open or done.");
        }

        this.status = ChangeStatus.CLOSED;
    }
}
