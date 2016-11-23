package test.model;

public class Student {
	String id;
	String name;
	String roomCode;
	int grade;

	public Student(String id, String name, String roomCode) {
		this.id = id;
		this.name = name;
		this.roomCode = roomCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
}
