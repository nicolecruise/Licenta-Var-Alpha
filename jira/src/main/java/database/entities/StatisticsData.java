package database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = StatisticsData.GET_ALL_Data, query = "SELECT data from StatisticsData data")
public class StatisticsData {

	public final static String GET_ALL_Data = "GET_ALL_Data";

	private Long id;
	private String project;
	private String issueType;
	private Integer sprint;
	private Integer value;

	public StatisticsData() {
	}

	public StatisticsData(Long id, String project, String issueType, Integer sprint, Integer value) {

		this.id = id;
		this.project = project;
		this.issueType = issueType;
		this.sprint = sprint;
		this.value = value;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSprint() {
		return sprint;
	}

	public void setSprint(Integer sprint) {
		this.sprint = sprint;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		StatisticsData data = (StatisticsData) o;

		boolean result = id.equals(data.id) && issueType.equalsIgnoreCase(data.issueType)
				&& project.equalsIgnoreCase(data.project) && sprint.equals(data.sprint) && value.equals(data.value);

		return result;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (issueType != null ? issueType.hashCode() : 0);
		result = 31 * result + (project != null ? project.hashCode() : 0);
		result = 31 * result + (sprint != null ? sprint.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Data{");
		sb.append("id=").append(id);
		sb.append(", issueType='").append(issueType).append('\'');
		sb.append(", project='").append(project).append('\'');
		sb.append(", sprint=").append(sprint);
		sb.append(", value=").append(value);
		sb.append('}');
		return sb.toString();
	}
}
