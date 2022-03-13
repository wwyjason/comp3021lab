package base;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>, java.io.Serializable{
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;

	
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Note> getNotes() {
		return this.notes;
	}
	
	public void sortNotes() {
		Collections.sort(notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		List<Note> result = new ArrayList<Note>();
		String temp = keywords.toLowerCase();
		String[] keyList = temp.split(" ");
		ArrayList<ArrayList<String>> keywordList = new ArrayList<ArrayList<String>>();

		int i = 0;
		while(i < keyList.length) {
			if (keyList[i].toLowerCase().equals("or")) {
				i += 1;
				keywordList.get(keywordList.size() - 1).add(keyList[i]);
			} 
			else {
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(keyList[i].toLowerCase());
				keywordList.add(tempList);
			}
			i += 1;
		}

		for(Note n : notes) {
			String searchWord = n.getTitle().toLowerCase();
			if(n instanceof TextNote) {
				searchWord +=  ((TextNote)n).getContent().toLowerCase();
			}

			boolean haveResult = true;
			for(ArrayList<String> pair : keywordList) {
				boolean matched = false;
				for(String key : pair) {
					if(searchWord.contains(key)) {
						matched = true;
						break;
					}
				}
				if(matched == false) {
					haveResult = false;
					break;
				}
			}
			if(haveResult == true) {
				result.add(n);
			}
		}

		return result;
	}
	
	@Override
	public int compareTo(Folder f) {
		return name.compareTo(f.getName());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for (Note n: notes) {
			if (n instanceof TextNote) {
				nText += 1;
			}
			if (n instanceof ImageNote) {
				nImage += 1;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
}
