package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


/**
 * Servlet implementation class ProjProposalAPI
 */
@WebServlet("/ProjProposalAPI")
public class ProjProposalAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProjProposal ProjProposalObj = new ProjProposal();

    /**
     * Default constructor. 
     */
    public ProjProposalAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got insert");

		String output = ProjProposalObj.createProjProposal(
				request.getParameter("researcher_name"), 
				request.getParameter("researcher_email"), 
				request.getParameter("proj_tittle"),
				request.getParameter("proj_description"),
				request.getParameter("requird_budget"),
				request.getParameter("expected_date")); 
				response.getWriter().write(output);	
				String jspName = request.getParameter("proj_idSave");
				RequestDispatcher rd = request.getRequestDispatcher(jspName);

				rd.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got here");
		Map paras = getParasMap(request); 
		
		 String output = ProjProposalObj.updateProjProposal( 
				 Integer.parseInt((String)paras.get("proj_id")), 
		 paras.get("researcher_name").toString(), 
		 paras.get("researcher_email").toString(), 
		 paras.get("proj_tittle").toString(),
		 paras.get("proj_description").toString(),
		 paras.get("requird_budget").toString(),
		 paras.get("requird_budget").toString()); 
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = ProjProposalObj.deleteProjProposal(
				 Integer.parseInt((String)paras.get("proj_id"))); 
		response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	String[] p = param.split("=");
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}


}
