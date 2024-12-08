$(document).ready(function() {
    const date = new Date();
    date.setDate(date.getDate() + 1);
    $("#due").val(date.toISOString().slice(0, 10));
    $("#valtask").hide();
    $("#valdue").hide();
    let taskErr = true;
    let dueErr = true;

    // Validate task name on input
    $("#tname").on("input", function() {
        isvalidTask();
    });

    function isvalidTask() {
        let tname = $("#tname").val().trim();
        let pattern = /[!@#$%^&*()\-+={}[\]:;"'<>,.?\/|\\]/;
        
        if (tname === "") {
            $("#valtask").show();
            taskErr = false;
        } else if (pattern.test(tname)) {
            $("#valtask").show();
            taskErr = false;
        } else {
            $("#valtask").hide();
            taskErr = true;
        }
    }

    // Validate due date on change
    $("#due").on("change", function() {
        isvalidDue();
    });

    function isvalidDue() {
        const curdate = new Date();
        curdate.setHours(0, 0, 0, 0);  
        const entdate = new Date($("#due").val());

        if (entdate.getFullYear() >= 3000 || entdate < curdate) {
            $("#valdue").show();
            dueErr = false;
        } else {
            $("#valdue").hide();
            dueErr = true;
        }
    }

    // Submit task form
    $("#task").submit(function(event) {
        event.preventDefault();
        isvalidTask();
        isvalidDue();
        if (taskErr && dueErr) {
           	this.submit();
        }
		else{
			event.preventDefault();
		}
    });
});



/*$.ajax({
               type: "POST", 
               url: '/To-Do/add',
              	dataType:'html',
               data: $("#task").serialize(),
               success: function(response) {
                   console.log("Response:", response);
				
				//fetchTaskList();
				window.location.href='/To-Do/Res.jsp';
                   // Optionally update the page with a success message or refresh the task list
               },
               error: function(error) {
                   console.log("Error Occurred:", error);
               }
           });*/
