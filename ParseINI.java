
package ProjetoIMDB;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ParseINI {
	public static void main(String[] args) throws IOException, InterruptedException {

		String apiKey = "k_j5m9t68x";
		URI apiIMDB = URI.create("https://imdb-api.com/en/API/Top250TVs/" + apiKey);
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(apiIMDB).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		String json = response.body();

		String json2 = json.substring(json.indexOf("[")+1, json.lastIndexOf("]"));
		
		List<Movie> filmes = ParseJSON_COMPLETE(json2);
		filmes.forEach(System.out::println);
		
		
	}
	private static List<String> ParseJSON_Title(String json) {
		
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("\\{([^\\{\\}]+?)\\}").matcher(json);
		while(m.find()) {
			String captar = m.group(1);
			String resultado = captar.substring(captar.indexOf("e\":\"")+4, captar.lastIndexOf("\",\"f"));
			list.add(resultado);
		}
		return list;
		
	}
	private static List<String> ParseJSON_URL(String json) {
		
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("\\{([^\\{\\}]+?)\\}").matcher(json);
		while(m.find()) {
			String captar = m.group(1);
			String resultado = captar.substring(captar.indexOf("ge\":\"")+5, captar.lastIndexOf("\",\"c"));
			list.add(resultado);
		}
		return list;
		
	}
	private static List<String> ParseJSON_Year(String json) {
		
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("\\{([^\\{\\}]+?)\\}").matcher(json);
		while(m.find()) {
			String captar = m.group(1);
			String resultado = captar.substring(captar.indexOf("ar\":\"")+5, captar.lastIndexOf("\",\"image"));
			list.add(resultado);
		}
		return list;
		
	}
	private static List<String> ParseJSON_Rating(String json) {
		
		List<String> list = new ArrayList<String>();
		Matcher m = Pattern.compile("\\{([^\\{\\}]+?)\\}").matcher(json);
		while(m.find()) {
			String captar = m.group(1);
			String resultado = captar.substring(captar.indexOf("ng\":\"")+5, captar.lastIndexOf("\",\"imDb"));
			list.add(resultado);
		}
		return list;
		
	}
	private static List<Movie> ParseJSON_COMPLETE(String json){
		List<Movie> list = new ArrayList<Movie>();
		Matcher m = Pattern.compile("\\{([^\\{\\}]+?)\\}").matcher(json);
		while(m.find()) {
			String captar = m.group(1);
			String r_titulo = captar.substring(captar.indexOf("e\":\"")+4, captar.lastIndexOf("\",\"f"));
			String r_url = captar.substring(captar.indexOf("ge\":\"")+5, captar.lastIndexOf("\",\"c"));
			String r_ano = captar.substring(captar.indexOf("ar\":\"")+5, captar.lastIndexOf("\",\"image"));
			String r_nota = captar.substring(captar.indexOf("ng\":\"")+5, captar.lastIndexOf("\",\"imDb"));
			list.add(new Movie(r_titulo, r_url, r_ano, r_nota));
		}
		return list;
	}
}

