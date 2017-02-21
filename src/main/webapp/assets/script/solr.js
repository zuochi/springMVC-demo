


var Solr = {
	
	init : function() {
		Common.ajax("/solr/getSource", {}, function(data){
			$.each(data, function(){
				$("#source").append("<option value='"+this+"'>"+this+"</option>");
			})
		},false);
	},
	
	fullimport : function() {
		var source=$("#source").val();
		var maxId=$("#maxId").val();
		var data = {};
		data.source = source;
		data.maxId = maxId;
		Common.ajax("/solr/fullImport", data, function(){
			
		}, true);
	}	
};

$(function(){
	Solr.init();
});