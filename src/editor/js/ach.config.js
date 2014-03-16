/*
ach.config.js
=============

Generates ng-templates based off of json file creates by BeardAch

*/

function _generateTemplate(prefix,entry,tempCache){
    var templateName = prefix + "." + entry.type;

    $scope = angular.element("body").scope();

    if($scope["opt" + prefix] == undefined){
      $scope["opt" + prefix] = {};
    }
    $scope["opt" + prefix][entry.type] = entry.name;
    $scope.$apply();

    var html = "";
    for(x in entry.fields){
      console.log(entry.fields[x].type);
      tmpHtml = tempCache.get("partial.ele." + entry.fields[x].type)
      .replace(/\$\{label\}/g,entry.fields[x].name)
      .replace(/\$\{model\.key\}/g,"entry." + entry.fields[x].key)
      .replace(/\$\{model\.min\}/g,""+ entry.fields[x].min)
      .replace(/\$\{model\.max\}/g,""+ entry.fields[x].max);

      if(entry.fields[x].values != null){
        selectHtml = "";
        for(y in entry.fields[x].values){
          selectHtml += "<option>" +  entry.fields[x].values[y] + "</option>\n";
        }
        tmpHtml = tmpHtml.replace(/\$\{values\}/g,selectHtml);
      }

      html += tmpHtml;
    }
    html+="<hr>";
    console.log("template created: " + templateName);
    tempCache.put(templateName,html);
  }

  function peekCache(type){
    console.log(angular.element("body").injector().get("$templateCache").get(type));
  }

  initConfig = function(data){
    tempCache = angular.element("body").injector().get("$templateCache");
    for(x in data.triggers){
      _generateTemplate("trigger",data.triggers[x],tempCache);
    }

    for(x in data.rewards){
      _generateTemplate("reward",data.rewards[x],tempCache);
    }
    var achHelpDatabase = angular.element("body").injector().get("achHelpDB");
    achHelpDatabase.rewardHelp = data.rewardHelp;
    achHelpDatabase.triggerHelp = data.triggerHelp;
    achHelpDatabase.initial();

  }