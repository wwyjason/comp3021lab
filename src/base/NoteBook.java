package base;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook() {
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}
	
	public boolean createTextNote(String folderName, String title, String content) {
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}
	
	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}
	
	public ArrayList<Folder> getFolders() {
		return this.folders;
	}
	
	public boolean insertNote(String folderName, Note note) {
		Folder target = null;
		for (Folder f: folders) {
			if (f.getName().equals(folderName)) {
				target = f;
			}
		}
		if (target == null) {
			Folder temp = new Folder(folderName);
			folders.add(temp);
			target = temp;
		}
		
		for (Note n: target.getNotes()) {
			if (note.equals(n)) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}
		target.addNote(note);
		return true;
	}
	
	public void sortFolders() {
		for (Folder f: folders) {
			f.sortNotes();
		}
		Collections.sort(folders);
	}
	
	public List<Note> searchNotes(String keywords) {
		ArrayList<Note> result = new ArrayList<Note>();
		for (Folder f:folders) {
			result.addAll(f.searchNotes(keywords));
		}
		return result;
	}
}