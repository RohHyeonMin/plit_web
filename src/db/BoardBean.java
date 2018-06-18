package db;

public class BoardBean {

	private int boardNum;
	private String boardContent;
	private String dateBoard;
	private int good;
	private int hits;
	private String boardTag;
	private double latitude; // 위도
	private double longitude;// 경도
	private String id;
	
	private String userIcon;
	
	public int getBoardNum()
	{
		return boardNum;
	}
	public void setBoardNum(int boardNum)
	{
		this.boardNum = boardNum;
	}
	
	public String getBoardContent()
	{
		return boardContent;
	}
	public void setBoardContent(String boardContent)
	{
		this.boardContent = boardContent;
	}
	
	public String getDateBoard()
	{
		return dateBoard;
	}
	public void setDateBoard( String dateBoard )
	{
		this.dateBoard = dateBoard;
	}
	public int getGood()
	{
		return good;
	}
	public void setGood(int good)
	{
		this.good = good;
	}
	
	public int getHits()
	{
		return hits;
	}
	public void setHits(int hits)
	{
		this.hits = hits;
	}
	
	public String getBoardTag()
	{
		return boardTag;
	}
	public void setBoardTag(String boardTag)
	{
		this.boardTag = boardTag;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	public void setLatitude( double latitude )
	{
		this.latitude = latitude;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	public void setLongitude( double longitude )
	{
		this.longitude = longitude;
	}
	
	public String getId()
	{
		return id;
	}
	public void setId( String id )
	{
		this.id = id;
	}
	
	public String getUserId()
	{
		return userIcon;
	}
	public void setUserId( String icon )
	{
		this.userIcon = icon;
	}
}
