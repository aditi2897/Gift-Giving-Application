import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONUtil {
	/* API key provided */
	private static final String urlpath = "http://api.zappos.com/Search?key=5b8384087156eb88dce1a1d321c945564f4d858e";

	/*
	 * getProductInfo() gets all the data provided from the API key to be
	 * processed specifically price and productId which uniquely identifies each
	 * product.
	 */
	public static void getProductInfo(int numberofproducts, double targetedSum) {
		URL url = null;
		try {
			String newurl=urlpath+"&term=&limit=50";
			url = new URL(newurl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("Identiying the different products available for the price and quantity......");
		System.out.println();
		Product product = (Product) convertJSONToObject(url);
		Map<String, Double> productHashMap = new HashMap<String, Double>();
		for (int index = 0; index < product.getResults().size(); index++) {
			productHashMap.put(
					product.getResults().get(index).getProductId(),
					Double.valueOf(product.getResults().get(index).getPrice()
							.substring(1)));
		}

		ProductCalculate.findSubset(productHashMap, targetedSum, product,
				numberofproducts);
	}

	/*
	 * the api key is passed to the convertJSONToObject method from the
	 * productInfo() to get the product details. It converts teh data into the
	 * Product object which has all they key information of the data in the URL
	 * path provided
	 */
	public static Object convertJSONToObject(URL url) {
		Product product = new Product();
		ObjectMapper mapper = new ObjectMapper();
		try {
			product = mapper.readValue(url, Product.class);

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

}
