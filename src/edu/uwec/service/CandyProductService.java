package edu.uwec.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.uwec.dao.CandyDAO;
import edu.uwec.model.CandyProduct;

@Path("/ProductService")
public class CandyProductService {
	CandyDAO candyDao = new CandyDAO();
	
	@GET
	@Path("/Products")
	//@Produces(MediaType.APPLICATION_XML)
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
}
