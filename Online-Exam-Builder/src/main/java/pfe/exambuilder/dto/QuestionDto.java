package pfe.exambuilder.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pfe.exambuilder.model.QuestionType;





public class QuestionDto {
	  private Long id;
	    private String text;
	    private QuestionType type;
	    private List<AnswerDto> answers;
	  public List<AnswerDto> getAnswers() {
	    return answers;
	  }
	  public void setAnswers(List<AnswerDto> answers) {
	    this.answers = answers;
	  }
	  public QuestionType getType() {
	    return type;
	  }
	  public void setType(QuestionType type) {
	    this.type = type;
	  }
	  public String getText() {
	    return text;
	  }
	  public void setText(String text) {
	    this.text = text;
	  }
	  public Long getId() {
	    return id;
	  }
	  public void setId(Long id) {
	    this.id = id;
	  }
	  
	}



/*

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private String text;
    private String questionType;
    private List<AnswerDto> answers;
    
    // Getters
    public String getText() {
        return text;
    }

    public String getQuestionType() {
        return questionType;
    }
}

*/