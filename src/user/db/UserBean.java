package user.db;

public class UserBean {

	// ���̵�
	// ��й�ȣ
	// ������� ( yyyymmdd )
	private String id;
	private String pw;
    private String birth;
     
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
}
