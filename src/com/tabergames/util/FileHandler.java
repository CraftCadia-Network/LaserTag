package com.tabergames.util;

import java.io.File;

public class FileHandler {

String Path = "plugins/LaserTag";
	
	public void Setup() {	
		File MainDirectory = new File(Path);
		if(!MainDirectory.exists()) {
			MainDirectory.mkdir();	
		}
		File PlayerData = new File(Path + "/playerdata");
		if(!PlayerData.exists()) {
			
			PlayerData.mkdir();
			
		}
	}
	
}
