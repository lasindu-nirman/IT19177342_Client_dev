$(document).ready(function()
{
    if ($("#alertSuccess").text().trim() == "")
    {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event)
{


    $.ajax(
        {
            url : "ProjProposalAPI",
            type : t,
            data : $("#formProjpro").serialize(),
            dataType : "text",
            complete : function(response, status)
            {
                onSaveComplete(response.responseText, status);
                console.log(response);
            }
        });
});

function onSaveComplete(response, status){
    if(status == "success")
    {
        console.log(response +  " "+status);
        var resultSet = JSON.parse(response);

        if(resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully Saved.");
            $("#alertSuccess").show();
            console.log("dataaaaaa");

            $("#ProjProposalGrid").html(resultSet.data);

        }else if(resultSet.status.trim() == "error"){
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    }else if(status == "error"){
        $("#alertError").text("Error While Saving.");
        $("#slertError").show();
    }else{
        $("#alertError").text("Unknown Error while Saving.");
        $("#alertError").show();
    }


    $("#proj_idSave").val("");
    $("#formFund")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event)
{
    $("#proj_idSave").val($(this).closest("tr").find('#proj_idUpdate').val());
    $("#researcher_name").val($(this).closest("tr").find('td:eq(1)').text());
    $("#researcher_email").val($(this).closest("tr").find('td:eq(2)').text());
    $("#proj_tittle").val($(this).closest("tr").find('td:eq(3)').text());
    $("#proj_description").val($(this).closest("tr").find('td:eq(4)').text());
    $("#requird_budget").val($(this).closest("tr").find('td:eq(5)').text());
    $("#expected_date").val($(this).closest("tr").find('td:eq(6)').text());


});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
    $.ajax(
        {
            url : "ProjProposalAPI",
            type : "DELETE",
            data : "proj_id=" + $(this).data("proj_id"),
            dataType : "text",
            complete : function(response, status)
            {
                onDeletedComplete(response.responseText, status);
            }
        });
});

function onDeletedComplete(response, status)
{
    if(status == "success")
    {
        console.log(response);
        var resultSet = JSON.parse(response);

        if(resultSet.status.trim() == "success")
        {
            $("#alertSuccess").text("Successfully Deleted.");
            $("#alertSuccess").show();

            $("#ProjProposalGrid").html(resultSet.data);

        }else if(resultSet.status.trim() == "error"){
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    }else if(status == "error"){
        $("#alertError").text("Error While Deleting.");
        $("#alertError").show();
    }else{
        $("#alertError").text("Unknown Error While Deleting.");
        $("#alertError").show();
    }
}
function validateProjProposalForm() {
    // researcher_name
    if ($("#researcher_name").val().trim() == "") {
        return "Insert researcher_name.";
    }
    // researcher_email
    if ($("#researcher_email").val().trim() == "") {
        return "Insert researcher_email.";
    }
    // proj_tittle-------------------------------
    if ($("#proj_tittle").val().trim() == "") {
        return "Insert proj_tittle.";
    }
    // proj_description-------------------------------
    if ($("#proj_description").val().trim() == "") {
        return "Insert proj_description.";
    }
    // requird_budget-------------------------------
    if ($("#requird_budget").val().trim() == "") {
        return "Insert requird_budget.";
        }
    // expected_date-------------------------------
    if ($("#expected_date").val().trim() == "") {
        return "Insert expected_date.";
    }
    return true;
}