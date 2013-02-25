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

	@Override
	public String generarDestacados(String input)throws IllegalArgumentException {

		String pictures=null;
		String price=null;
		String sold_quantity=null;
		String title=null;
		String currency=null;
		String pic=null;
		String permalink=null;
		String subtitle= null;
		
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
			currency=currencies(currency);
			pictures = json.getString("thumbnail");	
			permalink = json.getString("permalink");
			subtitle= json.getString("subtitle");

			System.out.println("id pic: " + pictures);
			
			pic = pictures.replace("_v_I_f", "_v_T_f");
			reader.close();
		
			if(subtitle.equals("null")){
				subtitle= "";
			}

			
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		String html_destacado=" HTML DESTACADOS GENERADO = <div><a href=\"#arriba\"><p><input type=\"image\" src="+pic + " align=\"left\" class=\"img-rounded\"><div><br>"+title+"<br>" +subtitle+".<br>"+price+" "+currency+"<br></p><br></div><div id=\"gwtContainer\"><p align=\"right\"></p></div></div></a></div>";
		
		//ublic static String DESTACADO_CUATRO= "<div><a href=\"#arriba\"><p><input type=\"image\" src=http://img2.mlstatic.com/s_MLU_v_T_f_29809057_7311.jpg align=\"left\" class=\"img-rounded\"><div><br>Camara Digital Kodak Z990 12mp 30x Optico Easyshare Full Hd.<br>Zoom Optico 30x! Ultra Zoom Panorama.<br>349.9 U$S. Estado: Nuevo<br><b>Oferta Valida: </b>2013-01-04T17:19:46.000Z</p><br></div><div id=\"gwtContainer\"><p align=\"right\"></p></div></div></a></div>";
		//public static String DESTACADO_PRODUCTO4="MLU405309429";

		
		return html_destacado;
		
	}
	

	public String findItem(String input)throws IllegalArgumentException {
		
		System.out.println("ID: " + input);
		
		String pictures=null;
		String price=null;
		String sold_quantity=null;
		String title=null;
		String currency=null;
		String pic=null;
		String permalink=null;
		String subtitle= null;
		
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
			currency=currencies(currency);
			pictures = json.getString("thumbnail");	
			permalink = json.getString("permalink");
			subtitle= json.getString("subtitle");

			System.out.println("id pic: " + pictures);
			
			pic = pictures.replace("_v_I_f", "_v_T_f");
			reader.close();
		
			if(subtitle.equals("null")){
				subtitle= "";
			}
			
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		return "Item id : " + input + "<br>Titulo: "+title+"<br>Subtitulo: "+subtitle+"<br>Precio: " + price + " "+currency/*serverInfo*/
				+ ".<br><br>Cantidad: " + sold_quantity +"<br>"+ "<p> <input type= \"image\" src= " + pic + " "+ " +  align=\"left\" onclick=\"location.href='"+ permalink + "' \"/> </p>" + "<br><br><br>";

	}
	
	
	/**
	 * @description get the symbol money in the api currencies.
	 * */
	public String currencies(String defaultCurrencyId){
		String symbolCurrency=null;

		if(defaultCurrencyId.equals("UYU"))
			symbolCurrency="$";
		else if (defaultCurrencyId.equals("USD")) {
			symbolCurrency="U$S";		
		}else if (defaultCurrencyId.equals("EUR")) {
			symbolCurrency="€";	
		}

		return symbolCurrency;
	}
	
	
	
}
