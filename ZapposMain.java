import java.util.Scanner;

public class ZapposMain {
	/*
	 * Main method asking for user input to process the information for the
	 * gifts to be suggested
	 */
	public static void main(String[] args) {
		int numberofproducts;
		double targetedSum;

		System.out.println("Please enter the number of Products to be gifted");
		Scanner cin = new Scanner(System.in);
		String number = cin.next();
		System.out
				.println("Please enter the estimated cost for the  products to be gifted ");

		String price = cin.next();
		numberofproducts = Integer.parseInt(number);
		targetedSum = Double.parseDouble(price);

		JSONUtil.getProductInfo(numberofproducts, targetedSum);

		/*
		 * Passing url of images to the FileDownload Class to save files to
		 * current working directory Commenting as 403 error Access denied to
		 * access the images
		 */
		// final String dir = System.getProperty("user.dir") +"/images/";
		// System.out.println(path);
		// FileDownload.fileDownload(ProductCalculate.imageurl,dir);
		// dir);//path from the hashmap needs to be sent
	}

}
