package base;
import java.util.Date;
import java.util.Objects;

public class Note implements Comparable<Note>, java.io.Serializable{
	private Date date;
	private String title;
	private static final long serialVersionUID = 1L;

	
	public Note(String title) {
		this.title = title;
		this.date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle() {
		return this.title;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}
	
	@Override
	public int compareTo(Note o) {
		return date.compareTo(o.date);
	}
	
	@Override
	public String toString() {
		return date.toString() + "\t" + title;
	}
}