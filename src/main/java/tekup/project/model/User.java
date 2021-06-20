package tekup.project.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer user_id;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String classe_grp;
	
	private String password;
	private boolean enabled;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> roles;

	@OneToMany(targetEntity = Certification.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "st_cerFK", referencedColumnName = "user_id")
	private List<Certification> certifications;

	@OneToMany(targetEntity = WhiteTest.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "st_whT_FK", referencedColumnName = "user_id" )
	private List<WhiteTest> whitetests;
}
