/**
 * @jsx React.DOM
 */
var ExampleApplication = React.createClass({
  render: function() {
    var elapsed = Math.round(this.props.elapsed  / 100);
    var seconds = elapsed / 10 + (elapsed % 10 ? '' : '.0' );
    var message =
      'React has been successfully running for ' + seconds + ' seconds.';

    return <div className="container" role="main">
		  <input id="nameTextField" type="text" value=""></input>
			  <button id="greetButton" className="btn btn-primary">Greet</button>
			  <h1><span id="greetingLabel" className="label label-primary label-success">unchanged</span></h1>

		  </div>;
  }
});

var start = new Date().getTime();

setInterval(function() {
  React.renderComponent(
    <ExampleApplication elapsed={new Date().getTime() - start} />,
    document.getElementById('container')
  );
}, 50);
