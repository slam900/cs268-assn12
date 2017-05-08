package edu.uwec.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.uwec.model.CandyProduct;

public class CandyServiceClient {
	private static String REST_SERVICE_URL = "http://172.28.8.221/olsonsd-assignment12/ProductService/Products";
	
	public static void main(String[] args) {
		try {
			URL url = new URL(REST_SERVICE_URL);
			InputStream is = url.openStream();
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new InputStreamReader(is));
			JSONArray array = (JSONArray)obj;
			
			ArrayList<CandyProduct> products = new ArrayList<CandyProduct>();
			
			for (int i = 0; i < array.size(); ++i) {
				JSONObject jsonObject = (JSONObject)array.get(i);
				int id = (int)(long)jsonObject.get("id");
				String description = (String) jsonObject.get("description");
				double price = (double)jsonObject.get("price");
				products.add(new CandyProduct(id, description, price));
			}
			
			for (CandyProduct cp : products)
				System.out.println(cp);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
