<%@ page import="org.opendolphin.odwebjee.makeitlonger.dolphin.Constants" %>
<%@ page import="org.opendolphin.odwebjee.makeitlonger.dolphin.MakeItLongerCommandHandler" %>
<%@ page import="org.opendolphin.odwebjee.blog.dolphin.CreateBlog" %>
<%@ page import="org.opendolphin.odwebjee.blog.dolphin.BlogPM" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="../bootstrap/bootstrap-3.1.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="../bootstrap/bootstrap-3.1.1-dist/css/bootstrap-theme.min.css">

	<title></title>
	<!-- refer to OpenDolphin, see also http://open-dolphin.org/dolphin_website/Download.html -->
	<script data-main="../js/dolphin/" src="../libs/require.js"></script>


	<script>
		require([ 'opendolphin' ], function (dol) {
			// Set up the dolphin for regular remote mode with the appropriate URL
			// forcing a new session on page reload (see comments in 'JEEOpenDolphinInvalidateServlet.java'):

			const dolphin = dol.dolphin("<%=application.getContextPath()%>/dolphin", true);

            // Make an attribute with name, no qualifier, and an empty String as initial value:
            const attribute = dolphin.attribute("<%=BlogPM.ATT_TITLE%>", undefined, "");

            // ... and put it into a presentation model with id 'myPM' and no type
            var pm = dolphin.presentationModel("<%=BlogPM.PM_ID%>", undefined, attribute);


            // Bind value of attribute to value of myLabel:
            const titleLabel = document.getElementById("titleLabel");

            // bind value of attribute to value of myLabel
            attribute.onValueChange(function (event) {
                titleLabel.innerHTML = event.newValue;
            });

            // On button click send the 'makeItLonger' command to the server.
            // The server will then change the value of the attribhute (see MakeItLongerCommandHandler.java), which causes the bound 'myLabel' to change.
            // Note that the client does not need to send parameters. This is not necessary because open-dolphin always synchronizes the PMs between
            // client and server in both directions.
            // Note also that it is not necessary to implement nor call custom HTTP/REST services to provide the 'makeItLonger' functionality:
            const myButton = document.getElementById("myButton");
            myButton.onclick = function () {
                dolphin.send("<%=CreateBlog.CMD_ID%>");
            };

        });
    </script>


</head>
<body>
<p></p>
<div class="container" role="main">

	<p><button class="btn btn-link"><a href="../index.jsp">Back to all samples</a></button></p>

	<div class="jumbotron">
		<h1>Blog Example</h1>
		<p>
			TBD
		</p>
	</div>


<p>
Title: <span id="titleLabel">unchanged</span>
</p>
<button id="myButton" class="btn btn-primary">Save</button>


</div>

</body>
</html>