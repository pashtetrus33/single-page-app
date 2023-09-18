$(document).ready(function () {
    //check teh browser console
    console.log("ready")

    //hide the entry form by default
    $("#entry-form").hide();

    //click handler for teh search button
    $("#search-button").click(function () {
        $("#entry-form").hide(500);
        //get the search term from the input test box
        var searchTerm = $("#searchTerm").val();

        console.log("You searched for " + searchTerm);

        $.ajax(
            {
                //REST controller url
                url: "http://127.0.0.1:8080/api/v1/persons/search/" + searchTerm,

                //callback function runs after the server responds.
                success: function (result) {
                    console.log(result);

                    //concatenate elements to display.
                    var outputHTML = "";
                    for (var x = 0; x < result.length; x++) {
                        var person = result[x];
                        outputHTML += "<div class='single-person'><ul>";
                        outputHTML += "<li>Id: " + person.id + "</li>";
                        outputHTML += "<li>Name: " + person.name + "</li>";
                        outputHTML += "<li>Age: " + person.age + "</li>";
                        outputHTML += "<li>Height: " + person.height + "</li>";
                        outputHTML += "<li>Weight: " + person.weight + "</li>";
                        outputHTML += "</ul>";
                        outputHTML += "<button class = 'edit-button btn btn-secondary' value = '" + person.id + "'>Edit </button>";
                        outputHTML += "<button class = 'delete-button btn btn-primary' value = '" + person.id + "'>Delete </button'>";
                        outputHTML += "</div>";
                    }

                    //console.log(outputHTML);

                    //replace teh contents of teh <div results-box> with the generated results
                    $("#results-box").html(outputHTML);
                }
            })
    });

    //click handler for the edit buttons.
    $(document).on("click", ".edit-button", function () {
        //get the item number tha was clicked
        const editIdNumber = $(this).val();
        console.log(editIdNumber)

        $.ajax(
            {
                //REST controller url
                url: "http://localhost:8080/api/v1/persons/" + editIdNumber,
                method: 'GET',
                //callback function runs after the server responds.
                success: function (result) {
                    console.log(result);

                    //fill the data entry form with results from the fake database.
                    $("#personID").val(editIdNumber);
                    $("#personName").val(result.name);
                    $("#personAge").val(result.age);
                    $("#personHeight").val(result.height)
                    $("#personWeight").val(result.weight);

                    //make the form visible. animate 500ms.
                    $("#entry-form").show(500);
                }
            })
    });

    //click handler for the entry form submit button.
    $("#form-ok").click(function () {
        //generate a json object from the data entry form.
        var obj = new Object();
        obj.name = $("#personName").val();
        obj.age = $("#personAge").val();
        obj.height = $("#personHeight").val();
        obj.weight = $("#personWeight").val();

        var jsonString = JSON.stringify(obj);
        console.log("jsonstring", jsonString);


        $.ajax(
            {
                method: 'PUT',
                url: "http://127.0.0.1:8080/api/v1/persons/update/" + $("#personID").val(),

                //configure the request to expect a json request body.
                contentType: 'application/json',
                data: jsonString,
                dataType: 'json',

                //callback function runs after the server responds.
                success: function (data) {
                    console.log(data);
                },
                error: function (e) {
                    console.log(e);
                },
            });

        $("#entry-form").hide(500);

        //clear the search results since information io out of date.
        $("#results-box").html("");
    });

    //delete button click listener
    $(document).on("click", ".delete-button", function () {
        const deleteIdNumber = $(this).val();
        console.log("delete id: ", deleteIdNumber);
        $.ajax({

            method: 'DELETE',
            url: "http://127.0.0.1:8080/api/v1/persons/delete/" + deleteIdNumber,
            success: function (data) {
                console.log(data);
            },
            error: function (e) {
                console.log(e);
            },

        });

        //clear the search results box since information is now out of date.
        $("#results-box").html("");
    });
});