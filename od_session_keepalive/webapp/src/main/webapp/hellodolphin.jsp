<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="webjars/bootstrap/3.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.1/css/bootstrap-theme.min.css">

    <title>Hello Dolphin</title>
    <!-- see build.gradle on how js artifacts are downloaded -->
    <script type="text/javascript" src="js/lib/opendolphin.js"></script>
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
                greetingLabel.innerHTML =  event.newValue;
            });

            att_name.onValueChange(function (event) {
                if (event.newValue !== undefined) {
                    console.log("name changed to: ", event.newValue);
                    nameTextField.value = event.newValue;
                }
            });
        }


        // Get PMs and attributes:
        var errorHandler = function (evt) {
            alert("Could not fetch " + evt.url + ", httpStatus: " + evt.httpStatus);
        };

        var dolphin = opendolphin.makeDolphin()
            .url(odConfig.DOLPHIN_URL)
            .reset(true)
            .errorHandler(errorHandler)
            .build();

        dolphin.send(ODAPI.COMMAND_INIT, {
            onFinished: function(pms) {
                setupBinding(dolphin);
            }
        });

        var sessionPingEnabled = true;
        if (sessionPingEnabled) {
            setInterval(function() {
                console.log("** pingsession", new Date());
                var http = new XMLHttpRequest();
                http.open('GET', "/appContext/pingsession", true);
                http.send();
            }, 1000 * 10); // every 10 seconds
        }

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
