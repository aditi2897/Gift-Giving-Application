import java.util.List;
/* Fetches all data for the keys in the API data to access any information  from  */
public class Product {

	private String originalTerm;
	private String currentResultCount;
	private String totalResultCount;
	private String limit;
	private String term;
	private List<ProductDetails> results;
	private String statusCode;

	public String getOriginalTerm() {
		return originalTerm;
	}

	public void setOriginalTerm(String originalTerm) {
		this.originalTerm = originalTerm;
	}

	public String getCurrentResultCount() {
		return currentResultCount;
	}

	public void setCurrentResultCount(String currentResultCount) {
		this.currentResultCount = currentResultCount;
	}

	public String getTotalResultCount() {
		return totalResultCount;
	}

	public void setTotalResultCount(String totalResultCount) {
		this.totalResultCount = totalResultCount;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<ProductDetails> getResults() {
		return results;
	}

	public void setResults(List<ProductDetails> results) {
		this.results = results;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
