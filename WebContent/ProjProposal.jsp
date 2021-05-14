<%@page import="com.ProjProposal"%>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
      pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
    <html>
      <head>
        <meta charset="ISO-8859-1">
        <title>Project Proposal Management
        </title>
        <link rel="stylesheet" href="Views/bootstrap.min.css">
        <script src="Components/jquery-3.2.1.min.js">
        </script>
        <script src="Components/ProjProposal.js">
        </script>
      </head>
      <body> 
        <div class="container">
          <div class="row">
            <div class="col-6"> 
              <h1>
              Project Proposal Management
              </h1>
              <form id="formProjpro" name="formProjpro" onsubmit="return validateProjProposalForm()">
                researcher_name: 
                <input id="researcher_name" name="researcher_name" type="text" 
                       class="form-control form-control-sm">
                       
                		<br> researcher_email: 
                <input id="researcher_email" name="researcher_email" type="text" 
                       class="form-control form-control-sm">
                       
                		<br> proj_tittle: 
                <input id="proj_tittle" name="proj_tittle" type="text" 
                       class="form-control form-control-sm">
                       
                		<br> proj_description: 
                <input id="proj_description" name="proj_description" type="text" 
                       class="form-control form-control-sm">
                       
                       <br> requird_budget: 
                <input id="requird_budget" name="requird_budget" type="text" 
                       class="form-control form-control-sm">
                       <br> expected_date: 
                <input id="expected_date" name="expected_date" type="text" 
                       class="form-control form-control-sm">
                <br>
                
                <input id="btnSave" name="btnSave" type="submit" value="Save" class="btn btn-primary">
                
                
                <input type="hidden" id="proj_idSave" name="proj_idSave" value="">
                
                 <input type="submit" value="Submit">
                
                
              </form>
              <div id="alertSuccess" class="alert alert-success">
              </div>
              <div id="alertError" class="alert alert-danger">
              </div>
              <br>
              <div id="ProjProposalGrid">
                <%
                   ProjProposal ProjProposalObj = new ProjProposal();
                   out.print(ProjProposalObj.viewProjProposal());
                   %>
                  </div>
              </div> 
            </div> 
          </div> 
          </body>
        </html>