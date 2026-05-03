package br.com.hericlesalves.testmanager.dto;

import br.com.hericlesalves.testmanager.model.enums.ChangePriority;
import br.com.hericlesalves.testmanager.model.enums.ChangeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseChangeDto {
    private String name;
    private String description;
    private String client;
    private ChangePriority priority;
    private ChangeStatus changeStatus;

    public ResponseChangeDto(String name, String description, String client, ChangePriority priority, ChangeStatus changeStatus) {
        this.name = name;
        this.description = description;
        this.client = client;
        this.priority = priority;
        this.changeStatus = changeStatus;
    }
}
