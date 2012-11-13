package com.example.myproject.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


import com.example.myproject.client.GreetingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String findItem(String input)throws IllegalArgumentException {
		
		System.out.println("ID: " + input);
		
		String pictures=null;
		String price=null;
		String sold_quantity=null;
		String title=null;
		String currency=null;
		String pic=null;
		
		URL url;
		try {
			url = new URL("https://api.mercadolibre.com/items/"+input);
			InputStream response = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response));
			String result="";

			for (String line; (line = reader.readLine()) != null;) {
			    result =result+line;
			}
						
			JSONObject json		= (JSONObject) JSONSerializer.toJSON(result);

			
		
			price = json.getString( "price" );
			sold_quantity = json.getString( "sold_quantity" );
			title=json.getString( "title");
			currency=json.getString("currency_id");		
			pictures = json.getString("thumbnail");			
			System.out.println("id pic: " + pictures);
			
			pic = pictures.replace("_v_I_f", "_v_T_f");
			reader.close();
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Item id :" + input + "!<br>"+title+"<br>Precio: " + price + " "+currency/*serverInfo*/
				+ ".<br><br>Cantidad: " + sold_quantity +"<br>"+ "<img src="+pic+">";
	}
}
