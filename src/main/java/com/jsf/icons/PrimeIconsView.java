package com.jsf.icons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

@ManagedBean
@ApplicationScoped
public class PrimeIconsView implements Serializable {

	private static final long serialVersionUID = -5177590981202934591L;

	private List<Icon> icons;

	@PostConstruct
	public void init() {
		icons = new ArrayList<>();

		String url = "https://raw.githubusercontent.com/primefaces/primeicons/99d7fd02312a0386df891062775f0b479b7c8d13/selection.json";
		try {
			JSONObject json = readJsonFromUrl(url);
			JSONArray iconsArray = json.getJSONArray("icons");
			for (int i = 0; i < iconsArray.length(); i++) {
				JSONObject properties = iconsArray.optJSONObject(i).getJSONObject("properties");
				icons.add(new Icon(properties.getString("name"), properties.getInt("code")));
			}
		} catch (IOException | JSONException ex) {
			Logger.getLogger(PrimeIconsView.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public List<Icon> getIcons() {
		return icons;
	}

	public void setIcons(List<Icon> icons) {
		this.icons = icons;
	}

	public class Icon {

		private String name;
		private int key;

		public Icon(String name, int key) {
			this.name = name;
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}
	}

}
