/**
 * @jsx React.DOM
 */
var ExampleApplication = React.createClass({

	getInitialState: function(){
		return {
			name: 'Duke'
		}
	},

	render: function () {

		return <div className="container" role="main">
			<input id="nameTextField" type="text" defaultValue={this.state.name} placeholder="placeholder"></input>
			<button id="greetButton" className="btn btn-primary">Greet</button>
			<h1>
				<span id="greetingLabel" className="label label-primary label-success">Hello {name}</span>
			</h1>

		</div>;
	}
});

React.renderComponent(
<ExampleApplication/>,
document.getElementById('container')
);
