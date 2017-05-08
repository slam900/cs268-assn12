package edu.uwec.test;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import edu.uwec.model.CandyProduct;
	
public class CandyServiceTester {

	   private Client client;
	   private String REST_SERVICE_URL = "http://172.28.8.221/olsonsd-assignment12/ProductService/Products";
	   private static final String SUCCESS_RESULT="<result>success</result>";
	   private static final String PASS = "pass";
	   private static final String FAIL = "fail";

	   private void init() {
	      this.client = ClientBuilder.newClient();
	   }

	   public static void main(String[] args) {
		  CandyServiceTester tester = new CandyServiceTester();
	      //initialize the tester
	      tester.init();
	      //test get all products Web Service Method
	      tester.testGetAllProducts();
	      //test get product Web Service Method 
	      tester.testGetProduct();
	      //test add product Web Service Method
	      tester.testAddProduct();
	      //test update product Web Service Method
	      tester.testUpdateProduct();
	      //test delete product Web Service Method
	      tester.testDeleteProduct();
	   }
	   //Test: Get list of all products
	   //Test: Check if list is not empty
	   private void testGetAllProducts() {
	      GenericType<List<CandyProduct>> list = new GenericType<List<CandyProduct>>() {};
	      List<CandyProduct> products = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .get(list);
	      String result = PASS;
	      if (products.isEmpty())
	         result = FAIL;
	      
	      System.out.println("Test case name: testGetAllProducts, Result: " + result);
	   }
	   //Test: Get product of id 1
	   //Test: Check if product is same as sample product
	   private void testGetProduct() {
		  CandyProduct sampleProduct = new CandyProduct();
		  sampleProduct.setId(1);

	      CandyProduct product = client
	         .target(REST_SERVICE_URL)
	         .path("/{id}")
	         .resolveTemplate("id", 1)
	         .request(MediaType.APPLICATION_XML)
	         .get(CandyProduct.class);
	      String result = FAIL;
	      if (sampleProduct != null && sampleProduct.getId() == product.getId())
	         result = PASS;
	      
	      System.out.println("Test case name: testGetProduct, Result: " + result);
	   }
	   
	   //Test: Add product of id 7
	   //Test: Check if result is success XML.
	   private void testAddProduct() {
	      Form form = new Form();
	      form.param("id", "7");
	      form.param("description", "Boston Baked Beans");
	      form.param("price", "2.00");

	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .put(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	   
	      String result = PASS;
	      if (!SUCCESS_RESULT.equals(callResult))
	         result = FAIL;
	      

	      System.out.println("Test case name: testAddProduct, Result: " + result);
	   }
	   
	   //Test: Update product of id 7
	   //Test: Check if result is success XML.
	   private void testUpdateProduct() {
	      Form form = new Form();
	      form.param("id", "7");
	      form.param("description", "Boston Baked Beanz");
	      form.param("price", "2.50");

	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .request(MediaType.APPLICATION_XML)
	         .post(Entity.entity(form,
	            MediaType.APPLICATION_FORM_URLENCODED_TYPE),
	            String.class);
	      String result = PASS;
	      if (!SUCCESS_RESULT.equals(callResult))
	         result = FAIL;
	      

	      System.out.println("Test case name: testUpdateProduct, Result: " + result);
	   }
	  
	   //Test: Delete User of id 7
	   //Test: Check if result is success XML.
	   private void testDeleteProduct() {
	      String callResult = client
	         .target(REST_SERVICE_URL)
	         .path("/{id}")
	         .resolveTemplate("id", 7)
	         .request(MediaType.APPLICATION_XML)
	         .delete(String.class);

	      String result = PASS;
	      if (!SUCCESS_RESULT.equals(callResult))
	         result = FAIL;
	      

	      System.out.println("Test case name: testDeleteProduct, Result: " + result);
	   }
}
