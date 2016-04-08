/**
 * The main application class. An instance of this class is created by app.js
 * when it calls Ext.application(). This is the ideal place to handle
 * application launch and initialization details.
 */
Ext.define('Test8.Application', {
	extend : 'Ext.app.Application',

	name : 'Test8',

	requires : [ "Ext.ux.BoxReorderer", "Ext.ux.CellDragDrop",
			"Ext.ux.DataTip", "Ext.ux.Explorer", "Ext.ux.FieldReplicator",
			"Ext.ux.GroupTabPanel", "Ext.ux.GroupTabRenderer", "Ext.ux.IFrame",
			"Ext.ux.LiveSearchGridPanel", "Ext.ux.PreviewPlugin",
			"Ext.ux.ProgressBarPager", "Ext.ux.RowExpander",
			"Ext.ux.SlidingPager", "Ext.ux.Spotlight", "Ext.ux.TabCloseMenu",
			"Ext.ux.TabReorderer", "Ext.ux.TabScrollerMenu",
			"Ext.ux.ToolbarDroppable", "Ext.ux.TreePicker", "Ext.ux.ajax.*",
			"Ext.ux.data.*", "Ext.ux.DataView.*", "Ext.ux.dd.*",
			"Ext.ux.event.*", "Ext.ux.form.*", "Ext.ux.grid.*",
			"Ext.ux.statusbar.*", "Ext.button.*", "Ext.container.*",
			"Ext.dashboard.*", "Ext.dd.*", "Ext.dom.*", "Ext.event.*",
			"Ext.flash.*", "Ext.form.*", "Ext.fx.*", "Ext.grid.*",
			"Ext.layout.*", "Ext.menu.*", "Ext.panel.*", "Ext.picker.*",
			"Ext.plugin.*", "Ext.resizer.*", "Ext.rtl.*", "Ext.selection.*",
			"Ext.slider.*", "Ext.sparkline.*", "Ext.state.*", "Ext.tab.*",
			"Ext.tip.*", "Ext.toolbar.*", "Ext.tree.*", "Ext.util.*",
			"Ext.view.*", "Ext.window.*", "Ext.Action", "Ext.Component",
			"Ext.ComponentLoader", "Ext.ElementLoader", "Ext.EventManager",
			"Ext.FocusManager", "Ext.Img", "Ext.LoadMask", "Ext.ProgressBar",
			"Ext.ProgressBarWidget", "Ext.ZIndexManager","Test8.view.databrowsercalendar.DBCalendar" ],

	views : [

	],

	controllers : [ 'Root' ],

	stores : [ 'Test8.store.QueryCriteriaWidgetsStore' ],

	launch : function() {
	}
});

var sessionTimeOutFlag = false;

/** isMultiForm* */
var isMultiTab = true;

Ext.Ajax.on('beforerequest', function(connection, options, eOpts) {

	if (options.maskEnable) {
		if (options.maskEle != null) {
			options.maskEle.mask('Request processing...');
		} else {
			Ext.getBody().mask('Request processing...');
		}
	}
});

Ext.Ajax.on('requestcomplete', function(conn, response, options, eOpts) {
	try {
		var responseData = Ext.JSON.decode(response.responseText);
		if (responseData.response) {
			if (responseData.response.exceptionId == 4005 && !sessionTimeOutFlag) {
				
				sessionTimeOutFlag = true;
				var loginWindow = Ext.create('Test8.view.login.SessionLogin').center();
				loginWindow.currentRequest = options;
				loginWindow.show();
			}
		}
	} catch(e){ }
	
	try {
		if (options.maskEnable) {
			if (options.maskEle != null) {
				options.maskEle.unmask();
			} else {
				Ext.getBody().unmask();
			}
		}
	} catch(e) {}
});

Ext.Ajax.on('requestexception', function(conn, response, options, eOpts) {
	try{
		if (options.maskEnable) {
			if (options.maskEle != null) {
				options.maskEle.unmask();
			} else {
				Ext.getBody().unmask();
			}
		}
	} catch(e) {}
});
