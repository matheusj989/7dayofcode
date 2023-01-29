package ProjetoIMDB;

public class Movie extends ParseINI {

	String title;
	String url;
	String year;
	String rating;
	public Movie(String title, String url, String year, String rating) {
		this.title=title;
		this.url=url;
		this.year=year;
		this.rating=rating;
	}
	public String toString() {
		return "Titulo: " + this.title + "  |Ano: " + this.year + "  |Nota: " + this.rating + "  |URL: " + this.url;
	}
}
