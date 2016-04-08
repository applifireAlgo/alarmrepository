Ext.define('Test8.view.googlemaps.map.MapPanel', {
	extend : 'Ext.panel.Panel',
	xtype : 'mapPanel',
	requires : [ 'Test8.view.googlemaps.map.MapPanelController' ],
	controller : 'MapPanelController',
	title : 'Map',
	split : true,
	closable : false,
	bodyPadding : '1 0 0 0',
	/*layout : {
		type : 'vbox',
		padding : 0,
		align : 'stretch'
	},*/
	height:500,
	mapData2 : [ {
		l1 : 19.75148,
		l2 : 75.713888,
		displayValue : "Maharashtra"
	}, {
		l1 : 33.778175,
		l2 : 76.576171,
		displayValue : "J & K"
	} ],
	/*bbar : [ {
		xtype : 'button',
		text : 'Search',
		itemId:"btnSearch",
		icon : 'resources/css/images/search.png',
		listeners:{
    		 click:'showMarker'
    	 }
	} ],*/
	
	listeners : {
		scope : 'controller',
		afterrender : 'loadGoogleMap'
	}
});