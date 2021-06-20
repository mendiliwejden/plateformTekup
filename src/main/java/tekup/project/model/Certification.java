package tekup.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Certification {

	@Id
	@GeneratedValue
	private Integer certification_id;
	private String name;
	private String description;
	private String home;
	private Integer nombreWhitTest;
	private float noteMinWhiteTest;
	

}
