/**
 * @jsx React.DOM
 */
var GreetApplication = React.createClass({

	getInitialState: function() {
		return {
			name: this.props.initialName
		}
	},

	onChange: function(e) {
		this.setState({name: e.target.value}); // first update component state...
		this.props.onChange(e); // ... then notify listener
	},

	render: function () {

		return <div className="container" role="main">
			<input id="nameTextField" type="text" value={this.state.name} placeholder="placeholder" onChange={this.onChange}></input>
			<button id="greetButton" className="btn btn-primary">Greet</button>
			<h1>
				<span id="greetingLabel" className="label label-primary label-success">Hello {this.state.name}</span>
			</h1>

		</div>;
	}
});

var globalName = 'Duke';

var render = function() {
	React.renderComponent(
		new GreetApplication({
			initialName: globalName,
			onChange: function(e) {
				globalName = e.target.value;
				console.log(globalName);
				render();
			}
		}),
		document.getElementById('container')
	);
};

render();

