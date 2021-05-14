package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjProposal {
	
	// -----------------------Database connection-----------------
	
	private Connection connect() {
		Connection con = null;
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget_db","root","root");
			System.out.println("-------------Connection succeed----------");
		}catch(Exception e){
			System.out.println("-------------Error connectiong to database---------/n " + e.fillInStackTrace());
		}
		
		return con;
	}
	

	public String viewProjProposal()
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for reading."; 
	 } 
	 
	 
	 
	 // Prepare the HTML table to be displayed
	 
	 output = "<table border='1'>"
	 		+ "<tr>"
			 
	 		+ "<th>proj_id</th>" 
	 		
	 		+ "<th>researcher_name</th>"
	 		
	 		+ "<th>researcher_email</th>" 
	 		
	 		+ "<th>proj_tittle</th>"
	 		
	 		+ "<th>proj_description</th>"
	 		
	 		+ "<th>requird_budget</th>"

			 + "<th>expected_date</th>"
	 		
	 		+ "<th>Update</th>"
	 		
	 		+ "<th>Remove</th>"
	 		
	 		+ "</tr>"; 
		
	 
	 String query = "select * from projproposal"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 
	 while (rs.next()) 
	 { 
	 String proj_id = Integer.toString(rs.getInt("proj_id")); 
	 String researcher_name = rs.getString("researcher_name"); 
	 String researcher_email = rs.getString("researcher_email"); 
	 String proj_tittle = rs.getString("proj_tittle"); 
	 String proj_description = rs.getString("proj_description"); 
	 String requird_budget = rs.getString("requird_budget");
	 String expected_date = rs.getString("expected_date");
	 
	// Add into the html table
	 
	 output += "<tr><td>" + proj_id + "</td>"; 
	 output += "<td>" + researcher_name + "</td>"; 
	 output += "<td>" + researcher_email + "</td>"; 
	 output += "<td>" + proj_tittle + "</td>";
	 output += "<td>" + proj_description + "</td>";
	 output += "<td>" + requird_budget + "</td>";
	 output += "<td>" + expected_date + "</td>";
	 
	// buttons
	 
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-itemid='" + proj_id + "'></td>"
	
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-itemid='" + proj_id + "'></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the table data."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

	//Insert
	public String createProjProposal(String researcher_name,String researcher_email,String proj_tittle,String proj_description,String requird_budget,String expected_date) {
		String result = null;
		
		try {
			
			Connection con = connect();
			PreparedStatement ps = null;
			
			if(con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "INSERT INTO ProjProposal VALUES (NULL,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,researcher_name);
            ps.setString(2,researcher_email);
            ps.setString(3,proj_tittle);
            ps.setString(4,proj_description);
            ps.setString(5,requird_budget);
            ps.setString(6,expected_date);
            
            ps.execute();
			 con.close(); 
			 
			 String newProjProposal = viewProjProposal(); 
			 result = "{\"status\":\"success\", \"data\": \"" + 
			 newProjProposal + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
				 result = "{\"status\":\"error\", \"data\": \"Error while inserting the ProjProposal.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return result; 
			 }
	
	//Update
	public String updateProjProposal(int proj_id,String researcher_name,String researcher_email,String proj_tittle,String proj_description,String requird_budget,String expected_date) {
		
		String result = null;
		
		try {

			Connection con = connect();
			PreparedStatement ps = null;
			
			if(con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "UPDATE ProjProposal SET proj_id=?, researcher_name=?, researcher_email=?, proj_tittle=?, proj_description=?, requird_budget=?, expected_date=?";
			
			ps = con.prepareStatement(sql);
			
			ps.setString(1,researcher_name);
            ps.setString(2,researcher_email);
            ps.setString(2,proj_tittle);
            ps.setString(4,proj_description);
            ps.setString(5,requird_budget);
            ps.setString(6,expected_date);
			
			ps.execute();
			
			
			
		}catch(Exception e) {
			
			System.err.println("Error occurred while Updating ProjProposal!\n" + e.getMessage());
			return "Error occurred while Updating ProjProposal!";
		}
		
		return "	ProjProposal updated Successfully!";
		
	}
	
	//Delete 
	public String deleteProjProposal(int proj_id) {
		
		try {
			
			Connection con = connect();
			PreparedStatement ps = null;
			
			if (con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "DELETE FROM ProjProposal WHERE proj_id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, proj_id);
			
			ps.execute();
			
			
		}catch(Exception e) {
			
			System.err.println("Error Deleting ProjProposal ! \n" + e.getMessage());
			return "Error Deleting ProjProposal ! ";
		}
		
		return "ProjProposal Deleted Successfully !";
	}




}
