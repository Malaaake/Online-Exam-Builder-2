package pfe.exambuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String content;
    private Boolean isCorrect;
    
    // Getters
    public String getContent() {
        return content;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }
}
