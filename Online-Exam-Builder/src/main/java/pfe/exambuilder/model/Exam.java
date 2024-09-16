package pfe.exambuilder.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpMethod;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Integer duration;
    private Integer totalMarks;

    // Constructors
    public Exam() {}

    public Exam(String title, String description, int duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }
    
    public Integer getTotalMarks() {
        return totalMarks;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

	public void setCreatedAt(LocalDateTime now) {
		// TODO Auto-generated method stub
		
	}

	 public void setTotalMarks(Integer totalMarks) {
	        this.totalMarks = totalMarks;
	    }

    
    
   

}
