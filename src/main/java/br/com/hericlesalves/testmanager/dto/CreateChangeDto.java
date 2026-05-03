package br.com.hericlesalves.testmanager.dto;

import br.com.hericlesalves.testmanager.model.enums.ChangePriority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChangeDto
{
    private String name;
    private String description;
    private String client;
    private ChangePriority priority;
}
