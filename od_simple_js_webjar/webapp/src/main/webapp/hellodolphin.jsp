<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="bootstrap/bootstrap-3.1.1-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="bootstrap/bootstrap-3.1.1-dist/css/bootstrap-theme.min.css">

    <title>Hello Dolphin</title>

    <!-- Reference to opendolphin.js contained in webjar (see dependency in build.gradle: compile 'org.open-dolphin:client-javascript:0.12.0B2') -->
    <script type="text/javascript" src="webjars/org/opendolphin/0.12.0B2/opendolphin.js"></script>

    <script type="text/javascript" src="js/app/api.js.jsp"></script>

    
    <script>
        var odConfig = readDolphinConfig();
        var ODAPI = odConfig.ODAPI

        var nameTextField;
        var greetingLabel;
        var greetButton;

        function setupBinding(dolphin) {
            // Get PMs and attributes:
            var pm = dolphin.getAt(ODAPI.PM_ID);
            var att_name = pm.getAt(ODAPI.ATT_NAME);
            var att_greeting = pm.getAt(ODAPI.ATT_GREETING);

            // Get hold to widgets:
            nameTextField = document.getElementById("nameTextField");
            greetingLabel = document.getElementById("greetingLabel");
            greetButton = document.getElementById("greetButton");

            greetButton.onclick = function () {
                dolphin.send(ODAPI.COMMAND_GREET);
            };

            // Bindings:
            // nameTextField -> att_name
            nameTextField.addEventListener("input", function () {
                console.log("name: ", nameTextField.value);
                att_name.setValue(nameTextField.value);
            });

            // att_greeting -> greetingLabel
            att_greeting.onValueChange(function (event) {
                console.log("greeting changed");
                greetingLabel.innerHTML = event.newValue;
            });

            att_name.onValueChange(function (event) {
                if (event.newValue !== undefined) {
                    console.log("name changed to: ", event.newValue);
                    nameTextField.value = event.newValue;
                }
            });
        }


        // Get PMs and attributes:
        var dolphin = opendolphin.dolphin(odConfig.DOLPHIN_URL, true);

        dolphin.send(ODAPI.COMMAND_INIT, {
            onFinished: function(pms) {
                setupBinding(dolphin);
            }
        });

    </script>


</head>
<body>
<p></p>
<div class="container" role="main">


<input id="nameTextField" type="text" value="">
<button id="greetButton" class="btn btn-primary">Greet</button>
<h1><span id="greetingLabel" class="label label-primary label-success">unchanged</span></h1>

</div>

</body>
</html>
