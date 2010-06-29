package show;

public class SexyGirl {
	
	private String songName = null;
	
	
	
	public String getSongName() {
		return songName;
	}



	public void setSongName(String songName) {
		this.songName = songName;
	}



	public String sing(){
		return "hey!hey!hey!!! i'm sing " + songName;
	}

}
