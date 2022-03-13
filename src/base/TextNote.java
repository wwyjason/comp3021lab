package base;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;


public class TextNote extends Note {
	String content;
	private static final long serialVersionUID = 1L;
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	public TextNote(String title) {
		super(title);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		try {
			 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath)));
			 result = br.readLine();
			 br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void exportTextToFile(String pathFolder) {
        if (pathFolder == "") {
        	pathFolder = ".";
        }
		File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") + ".txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(this.content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getContent() {
		return this.content;
	}
}
