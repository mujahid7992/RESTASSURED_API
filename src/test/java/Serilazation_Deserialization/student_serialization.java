package Serilazation_Deserialization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class student_serialization {

	String name;
	String location;
	String courses[];
	String id;
	String age;
	
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
	public void setId(String id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setage(String age) {
		this.age = id;
	}
	
	public student_serialization(String name,String location,String[] courses,String id)
	{
		this.name=name;
		this.location=location;
		this.courses=courses;
		this.id=id;
		this.age=age;
		
		
	}
	public student_serialization()
	{
		
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(location).append(" ").append(id).append(" ");
		if(courses!=null && courses.length>0)
		{
		for(String course:courses)
		{
			sb.append(course).append(" ");
		}
	}
		return sb.toString();
	}
}

