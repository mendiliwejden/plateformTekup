package tekup.project.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Examen {
	
	@Id
	@GeneratedValue
	private Integer examen_id;
	
	private Integer userId;
	private Integer certificationId;
	private LocalDate exam_date;
	

}
