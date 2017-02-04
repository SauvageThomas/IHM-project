package model;

import java.io.IOException;

public class Functions {
	
	public static void launchKeyboard(){
		try {
			Runtime.getRuntime().exec("cmd /c C:\\Windows\\System32\\osk.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
