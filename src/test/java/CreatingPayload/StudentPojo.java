package CreatingPayload;

public class StudentPojo {
	
	String name;
	String location;
	String courses[];
	String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] courses) {
		this.courses = courses;
	}
	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
	

}
