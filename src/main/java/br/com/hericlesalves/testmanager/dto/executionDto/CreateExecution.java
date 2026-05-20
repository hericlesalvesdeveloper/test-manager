package br.com.hericlesalves.testmanager.dto.executionDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateExecution {

   @NotNull
   @NotBlank
   private Long testCaseId;

   @NotNull
   @NotBlank
   private Long changeId;

}
