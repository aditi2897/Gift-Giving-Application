import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ProductCalculate {

	public static Set<Map<String, Double>> set = new HashSet<Map<String, Double>>();
	public static Set<Map<String, Double>> set1 = new HashSet<Map<String, Double>>();
	public static ArrayList<String> imageurl = new ArrayList<String>();

	/*
	 * findSubset() takes input from JSONUtil class which provides information
	 * of the prices of products and their ID's respectively. It checks the
	 * value of prices for each product if it is lesser than the target price
	 * provided by user and sends the information to the find() method. After
	 * processing the find() it calls display() which displays information of
	 * all products for each combination of prices.
	 */
	public static void findSubset(Map<String, Double> productHashMap,
			double targetedSum, Product product, int numberofproducts) {
		int index = 0;
		Set<Map.Entry<String, Double>> entrySet = productHashMap.entrySet();
		Iterator<Entry<String, Double>> entrySetIterator = entrySet.iterator();
		while (entrySetIterator.hasNext()) {
			index++;
			Map<String, Double> subset = new HashMap<String, Double>();
			@SuppressWarnings("rawtypes")
			Entry entry = entrySetIterator.next();
			if ((Double) entry.getValue() > targetedSum)
				continue;
			else
				subset.put(String.valueOf(entry.getKey()),
						(Double) entry.getValue());
			if (index + 1 < entrySet.size())
				find(subset, index, productHashMap, targetedSum, index,
						String.valueOf(entry.getKey()), numberofproducts);
		}

		display(set1, product);
	}

	/*
	 * find() method has all the information from the findSubset. The prices
	 * from this method are sent to the findSumOfList() which returns the sum of
	 * values and checks if it approximately equal to target price or not. This
	 * function continues recursively for all combination of unique prices. the
	 * set of combinations are stored in s1
	 */
	public static List<Integer> find(Map<String, Double> subset,
			int startIndex, final Map<String, Double> productHashMap,
			final double targetedSum, final int skipIndex, String productId,
			int numberofproducts) {
		if (skipIndex == startIndex) {
			find(subset, startIndex + 1, productHashMap, targetedSum,
					skipIndex, productId, numberofproducts);
			return null;
		}

		if (set1.size() < 5) {
			double subsetSum = findSumOfList(subset);

			double remainedSum = targetedSum - subsetSum;
			int i = startIndex;

			if (remainedSum == Math.rint(0)) {

				if (subset.size() == numberofproducts) {
					if (set1.size() < 5) {
						set1.add(subset);
					}
				}
				return null;
			}

			if ((startIndex < productHashMap.size())
					&& (productHashMap.get(productId) == remainedSum)) {
				Map<String, Double> temp = new HashMap<String, Double>(subset);
				temp.put(productId, productHashMap.get(productId));
				set.add(temp);
			} else if ((startIndex < productHashMap.size())
					&& (productHashMap.get(productId) < remainedSum)) {
				Iterator<Map.Entry<String, Double>> iterator = productHashMap
						.entrySet().iterator();
				while (iterator.hasNext()) {
					Map.Entry<String, Double> productEntry = iterator.next();
					Map<String, Double> temp = new HashMap<String, Double>(
							subset);
					if (i != skipIndex) {
						temp.put(productEntry.getKey(), productEntry.getValue());
						find(temp, ++i, productHashMap, targetedSum, skipIndex,
								productEntry.getKey(), numberofproducts);
					} else {
						i = i + 1;
					}
				}
			} else if ((startIndex < productHashMap.size())
					&& (productHashMap.get(productId) > remainedSum)) {
				find(subset, ++i, productHashMap, targetedSum, skipIndex,
						productId, numberofproducts);
			}
		}
		return null;

	}

	public static double findSumOfList(Map<String, Double> map) {
		double i = 0;
		for (String productId : map.keySet()) {
			i = i + map.get(productId);
		}
		return i;
	}

	/* Provides information of each product within the combination */

	public static void display(Set<Map<String, Double>> hashset, Product product) {
		int counter = 0;
		if (hashset.isEmpty()) {
			System.out
					.println(" Sorry! There are no products available for the quantity and price  mentioned");
			return;
		}
		for (Iterator<Map<String, Double>> it = hashset.iterator(); it
				.hasNext();) {
			Map<String, Double> productMap = it.next();
			counter++;
			Iterator<Map.Entry<String, Double>> iterator = productMap
					.entrySet().iterator();
			System.out
					.println(counter
							+ ") "
							+ "Suggested Combination of Products for the given range of cost");
			double Sum = 0;
			while (iterator.hasNext()) {

				Map.Entry<String, Double> productEntry = iterator.next();
				for (int i = 0; i < product.getResults().size(); i++) {
					if (String.valueOf(productEntry.getKey()).equals(
							product.getResults().get(i).getProductId())) {
						Sum = Sum
								+ Double.valueOf(product.getResults().get(i)
										.getPrice().substring(1));
						System.out.println("ProductID: "
								+ product.getResults().get(i).getProductId()
								+ "  " + "Product Name: "
								+ product.getResults().get(i).getProductName()
								+ " " + " Price: "
								+ product.getResults().get(i).getPrice() + " "
								+ " BrandName: "
								+ product.getResults().get(i).getBrandName()
								+ "");
						imageurl.add(product.getResults().get(i)
								.getThumbnailImageUrl());
						break;

					}
				}

			}
			System.out.println("Total cost is " + "$" + Sum);

			System.out.println();
		}
	}
}