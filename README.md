# Gift-Giving-Application

The project has the following Classes:
• JSONUtil
• Product
• ProductDetails.
• ProductCalculate • FileDownload
• ZapposMain

Product:
The Product class has all the keys, which are present in the API file from the key given in the mail. The result is a list because it has all the data for each product like productId, productName etc. It has getters and setters method defined for each.

ProductDetails
In this class the information of all the products that is present in the result list could be fetched. It has getters and setters method defined for each.

ZappoMain
Main method is defined here, which accepts input from the user i.e. numberofproducts and the amount to be gifted. The user provided information is passed to getProductInfo() in the JSONUtil Class. Further processing continues from JSONUtil class

JSONUtil:
In this class the method we have two methods getProductInfo() and convertJSONToObject(). The getProductInfo() accepts two parameters i.e. numberofproducts and total sum of amount to be gifted. The method calls the convertJSONToObject() by passing the url i.e. key to access the API data which gives Product Information from Product Class. The information retrieved is productId and price from the Product table. The information fetched, numberofproducts and the target amount is passed to the findSubset() in ProductCalculate class.

ProductCalculate
The ProductCalculate class has all the calculation to provide us the data for Gifts.
The information provided from the user and the data from the api i.e. price specifically are sent to the findSubset(). This method firstly checks the price of each product in the data provided is less than the gifted price amount or not and it is not the end of the dataset it calls the find() method checks the index to be skipped if it has already been processed and calls the findSumOfList() which takes in all the prices which are lesser than the targeted price. The method calculates the sum of all of the prices and returns back to the find(). The value returned is checked if it is exact or in equal proximity to the target price and respectively stores it to provide the user with the specific data. The information is fetched recursively and sent to the display() method which displays information of all the combinations of product in proximity to the target price. This gives the user of choices available to gift.

FileDownload
This method is basically to download images for the products, which are listed in the combinations to the user. The url and the destination directory are passed to the
() method which retrieves the file name and passes it with the above two to the ￼ ) method. This method saves the file to the destination directory.
