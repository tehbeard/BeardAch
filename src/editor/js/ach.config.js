/*
ach.config.js
=============

Generates ng-templates based off of json file creates by BeardAch

*/

function _generateTemplate(prefix,entry,tempCache){
  var templateName = prefix + "." + entry.type;
  var html = "<h2>" + entry.name + "</h2>";
  for(x in entry.fields){
    html += tempCache.get("partial.ele." + entry.fields[x].type)
    .replace(/\$\{label\}/g,entry.fields[x].name)
    .replace(/\$\{model\.key\}/g,"entry." + entry.fields[x].key);
  }
  html+="<hr>";
  tempCache.put(templateName,html);
}

initConfig = function(data){
  tempCache = angular.element("body").injector().get("$templateCache");
  for(x in data.triggers){
    _generateTemplate("trigger",data.triggers[x],tempCache);
  }

  for(x in data.rewards){
    _generateTemplate("reward",data.rewards[x],tempCache);
  }
}