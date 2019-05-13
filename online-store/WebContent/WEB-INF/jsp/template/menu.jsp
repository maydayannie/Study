<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<style>
.nav-list {
  padding-right: 15px;
  padding-left: 15px;
  margin-bottom: 0;
}
.scrollable-menu {
    height: auto;
    max-height: 500px;
    overflow-x: hidden;
}
</style>
</head>
<body>
<div id="menu"></div>
<script type="text/babel">

var Menu = React.createClass({
	getInitialState: function() {
    	return { menuData: null };
  	},
	componentDidMount: function() {
    	$.get('../rs/getJson').done(function(data) {
      		this.setState({menuData: data});
    	}.bind(this));
  	},
	render: function() {
    	if (this.state.menuData) {
      		return <MenuData data={this.state.menuData} />;
    	}
    	return <div>Loading...</div>;
  	}
});

var MenuData = React.createClass({

  	render: function() {
		var content = [];
		this.props.data.forEach((value,index) => {
					content.push (<li key={index}>{value.engName}</li>);
				});
      	return ( 
				<ul className="scrollable-menu" role="menu">
					<li>
							{content}
					</li>
				</ul>
			  );
  	}
});



ReactDOM.render(
	<Menu />,
    document.getElementById('menu')
);
</script>
</body>
</html>