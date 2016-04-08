Ext.define('Test8.view.databrowsercalendar.DBCalendar', {
	extend : 'Test8.view.databrowsercalendar.DBCalendarView',
	requires : [ 'Test8.view.databrowsercalendar.DBCalendarController',
	             'Test8.view.databrowsercalendar.DBCalendarView','Ext.layout.container.Card',
	             'Ext.calendar.view.Day', 'Ext.calendar.view.Week',
	             'Ext.calendar.view.Month',
	             'Ext.calendar.form.EventDetails',
	             'Ext.calendar.data.EventMappings'],
	controller : 'databrowsercalendar',
	items : [],
	listeners : {
		afterrender : 'loadData',
		scope : "controller"
	}
});
