package com.queries.ecommerce;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Sample {
	public static void main(String[] args) {
		try {
            DatabaseConnection db = new DatabaseConnection();
            System.out.println("1. Write a query to fetch all the product details which are colour blue and size L ?");
			ResultSet r= db.getConnection("SELECT product.*\r\n"
					+ "FROM product product\r\n"
		+ "JOIN variants variant ON (variant.isdeleted=0 and productid=product.id)\r\n"
					+ "JOIN color color ON (color.isdeleted = 0 AND variant.colorid=color.id)\r\n"
					+ "JOIN size size ON (size.isdeleted=0 AND sizeid=size.id)\r\n"
					+ "WHERE color.colorname='blue'and size.size='l';");
			System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s  ","id","name","productnumber","subtypeid","materialid","categoriesid","storeid","washcareid","designerid"));
			while(r.next()) {
			System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s  ",r.getLong(1),r.getDate(2),r.getInt(3),r.getDate(4),r.getString(5),r.getString(7),r.getLong(8),r.getLong(9),r.getLong(10),r.getLong(11),r.getLong(12),r.getLong(13)));
			}
			db.closeConnection();
		System.out.println();
		 DatabaseConnection data =new DatabaseConnection();
		 System.out.println("2. Write a Query to get all order Details of customer for designer?");
		 ResultSet res= data.getConnection("SELECT    details.* ,orde.ordernumber,products.productnumber,products.id AS productid ,stat.name AS status, customers.*,(case when stat.name = 'delivered' then orde.id else null end) as orderid\r\n"
					+ "FROM orderdetails details \r\n"
					+ "JOIN shippment ship ON ( ship.isdeleted = 0 AND ship.id = ship.orderdetailsid)\r\n"
					+ "JOIN status stat ON ( stat.isdeleted = 0 AND stat.id = ship.statusid )\r\n"
					+ "JOIN orders orde ON (orde.isdeleted=0 AND  details.ordersid = orde.id )\r\n"
					+ "JOIN customeraddress address ON (address.isdeleted=0 AND orde.customeraddressid = address.id )\r\n"
					+ "JOIN customer customers ON ( customers.isdeleted =0 AND address.customerid = customers.id )\r\n"
					+ "JOIN variants variants ON (variants.isdeleted = 0 AND details.variantsid = variants.id )\r\n"
					+ "JOIN product products ON (products.isdeleted =0 AND variants.productid = products.id )\r\n"
					+ "JOIN brand brands ON (brands.isdeleted =0 AND products.brandid = brands.id )\r\n"
					+ "JOIN designer designers ON (designers.isdeleted = 0 AND brands.designerid = designers.id ) \r\n"
					+ "WHERE designers.id=2 AND  stat.name='delivered' AND details.isdeleted=0;\r\n");
			
			
		 System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s ","id","quantity","price","expecteddeliverydate","totalprice","ordersid","variantsid","storeid","ordernumber","productnumber","productid","status","id","name","email","password","mobilenumber","orderid"));
		  while(res.next()) {
				System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30S  ",
						 res.getLong(1),res.getString(5),res.getBigDecimal(6),res.getDate(7),res.getBigDecimal(8),res.getLong(9),res.getLong(10),res.getLong(11),res.getString(12),
						 res.getString(13),res.getLong(14),res.getString(15),res.getLong(16),res.getString(20),res.getString(21),res.getString(22),res.getString(23),res.getLong(24)));
			}
			data.closeConnection();
			System.out.println();
	DatabaseConnection database=new DatabaseConnection();
	System.out.println("3. Write a query to find order details that order details contains customername, orderid, orderdetailsid, totalprice, variant details, quantity,\r\n"
			+ " address and payment details based on designerid?");
ResultSet result= database.getConnection("SELECT  details.* ,payment.mode,customer.name,db.name,address.*,color.colorname,size.size,care.type,TYPE.name\r\n"
			+ " from orderdetails details\r\n"
			+ " JOIN orders ORDERs ON (orders.isdeleted=0 and orders.id=details.ordersid)\r\n"
			+ " JOIN variants variant ON (variant.isdeleted=0 and variant.id=details.variantsid)\r\n"
			+ " JOIN customeraddress address ON (address.isdeleted=0 and address.id=ORDERs.customeraddressid)\r\n"
			+ " JOIN modeofpayment payment ON (payment.isdeleted=0 and payment.id=ORDERs.modeofpaymentid)\r\n"
			+ " JOIN storebrand  store ON (store.isdeleted=0 AND store.id=details.storeid)\r\n"
			+ " JOIN brand brand ON (brand .isdeleted=0 AND brand.id=brand.designerid)\r\n"
			+ " JOIN customer customer ON (customer.isdeleted=0 AND customer.id=address.id)\r\n"
			+ " JOIN designer db ON (db.isdeleted=0 AND db.id=brand.designerid)\r\n"
			+ " JOIN color color ON (color.isdeleted=0 AND color.id=variant.colorid)\r\n"
			+ " JOIN size size ON (size.isdeleted=0 AND size.id=variant.sizeid)\r\n"
			+ " JOIN weighttype TYPE ON (TYPE.isdeleted=0 AND variant.weighttypeid=TYPE.id)\r\n"
			+ " JOIN product product ON (product.isdeleted=0 AND variant.productid=product.id)\r\n"
			+ " JOIN material mat ON (mat.isdeletd=0 AND mat.id=product.materialid)\r\n"
			+ " JOIN washcare care ON (care.isdeleted=0 AND care.id=product.washcareid)\r\n"
			+ " where details.isdeleted=0;");
	System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-60s %-40s %-15s %-15s %-20s %-15s %-18s %-15s %-15s %-10s ","id","quantity","price","expecteddeliverydate","totalprice","ordersid","variantsid","storeid","mode","name","name","id","addressline1","addressline2","city","country","pincode","customerid","colorname","size","type","name"));
	while(result.next()) {
		System.out.println(String.format("%-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-30s %-20s %-60s %-50s %-18s %-15s %-18s %-20S %-15S %-10s %-15s %-35s ",
				 result.getLong(1),result.getString(5),result.getBigDecimal(6),result.getDate(7),result.getBigDecimal(8),result.getLong(9),result.getLong(10),result.getLong(11),result.getString(12),
				 result.getString(13),result.getString(14),result.getLong(15),result.getString(19),result.getString(20),result.getString(21),result.getString(22),result.getString(23),result.getLong(25),
				 result.getString(26),result.getString(27),result.getString(28),result.getString(29)));
	}
	database.closeConnection();
}
			catch(Exception e) {
		}
		}
}




