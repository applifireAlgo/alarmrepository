Ext.define('Test8.view.reportui.ReportView', {
	extend : 'Ext.panel.Panel',
	requires : [ 'Test8.view.reportui.querycriteria.QueryCriteriaView',
			'Test8.view.reportui.datachart.DataChartViewTab',
			'Test8.view.reportui.datachart.DataChartViewPanel',
			'Test8.view.reportui.ReportViewController' ,
			'Test8.view.fw.MainDataPointPanel',
			'Test8.view.googlemaps.map.MapPanel'
			],
	xtype : 'reportview',
	controller : 'reportviewController',
	layout : 'border',
	isCustomReport:false,
	reportWidgets :["1","2","3","4"],
	//autoScroll : true,
	//margin : '3 0 5 0',
	height:500,
	width:"100%",
	listeners : {
		scope : 'controller',
		afterrender : 'renderReport'
	}
});
