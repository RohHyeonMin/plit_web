package action;

public class ActionForward 
{
	// redirect : URL�� ����Ǹ� ��û�� �ٲ�� �ȴ�. ���� request ������ ������ �Ӽ� ���� ���� x
	private boolean isRedirect = false;
	private String path = null;
	
	public boolean isRedirect(){
		return isRedirect;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setRedirect(boolean b){
		isRedirect = b;
	}
	
	public void setPath(String string){
		path = string;
	}
}