package edu.uwec.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uwec.dao.CandyDAO;
import edu.uwec.model.CandyProduct;

@Path("/ProductService")
public class CandyProductService {
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";
	
	CandyDAO candyDao = new CandyDAO();
	
	@GET
	@Path("/Products")
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public List<CandyProduct> getProducts() {
		List<CandyProduct> products;
		
		try {
			products = candyDao.getProducts();
		} catch (SQLException e) {
			products = new ArrayList<CandyProduct>();
			e.printStackTrace();
		}
		return products;
	}
	
	@GET
	@Path("/Products/{id}")
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public CandyProduct getProduct(@PathParam("id") int id) {
		try {
			return candyDao.getProduct(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@POST
	@Path("/Products")
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateProduct(
			@FormParam("id") int id
			, @FormParam("description") String description
			, @FormParam("price") double price
		) {
		CandyProduct product = new CandyProduct(id, description, price);
		
		try {
			if (candyDao.updateProduct(product))
				return SUCCESS_RESULT;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return FAILURE_RESULT;
	}
	
	@PUT
	@Path("/Products")
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addProduct(
			@FormParam("id") int id
			, @FormParam("description") String description
			, @FormParam("price") double price
		) {
		CandyProduct product = new CandyProduct(id, description, price);
		
		try {
			if (candyDao.addProduct(product))
				return SUCCESS_RESULT;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return FAILURE_RESULT;
	}
	
	@DELETE
	@Path("/Products/{id}")
//	@Produces(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(@PathParam("id") int id) {
		try {
			if(candyDao.deleteProduct(id))
				return SUCCESS_RESULT;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return FAILURE_RESULT;
	}
}
