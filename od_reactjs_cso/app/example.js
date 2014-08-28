/**
 * @jsx React.DOM
 */
var GreetApplication = React.createClass({

	onChange: function(e) {
		this.props.onChange(e); // notify listener
	},

	render: function () {

		return <div className="container" role="main">
			<input id="nameTextField" type="text" value={this.props.name} placeholder="placeholder" onChange={this.onChange}></input>
			<button id="greetButton" className="btn btn-primary">Greet</button>
			<p id="name_echo" >Entered name: {this.props.name}</p>
			<h1>
				<span id="greetingLabel" className="label label-primary label-success">Greeting</span>
			</h1>

		</div>;
	}
});

var globalName = 'Duke';

var render = function() {
	React.renderComponent(
		new GreetApplication({
			name: globalName,
			onChange: function(e) {
				globalName = e.target.value;
				render();
			}
		}),
		document.getElementById('container')
	);
};

render();

