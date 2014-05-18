"use strict";
/**
* Converts JSON
*
*
*/
var app = angular.module('achJsHTML',[]);
app.provider("formConstructor",function(){
	

	var _data = {};

	this.setData = function(data){
		_data = data;
	};

	

	function _generateTemplate(prefix,entry,tempCache){
		var templateName = prefix + "." + entry.type;
		var html = "";
		for(var x in entry.fields){

			var tmpHtml = tempCache.get("partial.ele." + entry.fields[x].type)
			.replace(/\$\{label\}/g,entry.fields[x].name)
			.replace(/\$\{model\.key\}/g,"entry." + entry.fields[x].key)
			.replace(/\$\{model\.min\}/g,""+ entry.fields[x].min)
			.replace(/\$\{model\.max\}/g,""+ entry.fields[x].max);

			if(entry.fields[x].values != null){
				var selectHtml = "";
				for(var y in entry.fields[x].values){
					selectHtml += "<option>" +  entry.fields[x].values[y] + "</option>\n";
				}
				tmpHtml = tmpHtml.replace(/\$\{values\}/g,selectHtml);
			}

			html += tmpHtml;
		}
		html+="<hr>";
		console.log("template created: " + templateName);
		tempCache.put(templateName,html);
		return [entry.type, entry.name];
	}


	this.$get = ["$templateCache",function($templateCache){
		var _triggerList = {};
		var _rewardList  = {};
		for(var x in _data.triggers){
			var _d = _generateTemplate("trigger",_data.triggers[x],$templateCache);
			_triggerList[_d[0]] = _d[1];
		}

		for(x in _data.rewards){
			var _d = _generateTemplate("reward",_data.rewards[x],$templateCache);
			_rewardList[_d[0]] = _d[1];
		}
		return {
			getTriggers: function(){return _triggerList;},
		    getRewards: function(){return _rewardList;}
		};
	}];
});