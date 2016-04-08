Ext.define('Test8.view.reportui.datachart.DataChartViewTab', {
	extend : 'Ext.tab.Panel',
	requires : [ 'Test8.view.reportui.datachart.DataChartTController',
	             'Test8.view.reportui.datachart.datagrid.DataGridView',
	             'Test8.view.reportui.datachart.chart.ChartTabView',
	             'Test8.view.reportui.datachart.ChartPointView' ],
	controller : 'datacharttController',
	xtype : 'datachart-tabpanel',
	tabPosition : 'bottom',
	bodyStyle : 'background:#D8D8D8',
	listeners : {
		scope : "controller",
		tabchange : 'tabchange',
		afterrender:'afterTabPanelRender'
	}
});