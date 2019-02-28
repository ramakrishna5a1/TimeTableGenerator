package scheduler;

public class StudentGroup {
	public int id;
	public String name;
	public String[] subject;
	int noSubject;
	int teacherId[];
	int[] hours;
	
	StudentGroup() {
		subject=new String[10];
		hours=new int[10];
		teacherId =new int[10];
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getSubject() {
		return subject;
	}
	public void setSubject(String[] subject) {
		this.subject = subject;
	}
	public int getNoSubject() {
		return noSubject;
	}
	public void setNoSubject(String snosubject) {
		this.noSubject = Integer.parseInt(snosubject);
	}
	public int[] getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int[] teacherId) {
		this.teacherId = teacherId;
	}
	public int[] getHours() {
		return hours;
	}
	public void setHours(int[] hours) {
		this.hours = hours;
	}
	
}
