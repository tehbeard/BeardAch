/*

Achievement
{
    "slug": "old_timer",
    "name": "Old Timer",
    "descrip": "Been on here a while, ain\u0027t ya?",
    "triggers": [
      {
        "cat": "stats",
        "stat": "playedother",
        "threshold": 54000,
        "_type": "stat"
      }
    ],
    "rewards": [],
    "broadcast": "BROADCAST",
    "hidden": false
  }

Trigger / reward
{
  "type":"annotation-name",
  "name":"Human name for item",
  "fields":[
  {"key":"json.path.node","name":"","type":""},
  {"key":"","name":"","type":""},
  {"key":"","name":"","type":""},
  {"key":"","name":"","type":""},
  {"key":"","name":"","type":""},
  ]
}

{
  "type":"stat",
  "name":"BeardStat stat above",
  "fields":[
  {"key":"cat","name":"Category","type":"text"},
  {"key":"stat","name":"Statistic","type":"text"},
  {"key":"threshold","name":"Threshold","type":"text"},
  ]
}

*/
var data;
function initConfig(t){
  data = t;
}
function loadConfig(){
  var t;
  t = data;
  for(x in t.triggers){
    p = x / (t.triggers.length + t.rewards.length);
    bootProgressUpdate(p*100,"Installing trigger " + t.triggers[x].name);
    addTriggerToSelect(t.triggers[x]);
    makeElement(t.triggers[x],".trigger-container");
    

  }

  for(x in t.rewards){
    p = (x + t.triggers.length) / (t.triggers.length + t.rewards.length);
    bootProgressUpdate(p*100,"Installing reward " + t.rewards[x].name);
    addRewardToSelect(t.rewards[x]);
    makeElement(t.rewards[x],".reward-container");
  }
}

function makeElement(data,container){
  var html = '<div class="element" data-type="' + data.type + '">';
  html+='<input type="hidden" data-key="_type" value="' + data.type + '"/>';
  html+='<div class="input-prepend"><label class="add-on"><b>' + data.name + '</b></label><button class="btn btn-danger" onclick="deleteParent(this);"><i class="icon-remove icon-white"></i> Remove</button>';
  for(x in data.fields){
    html+=makeFormElement(data.fields[x]);
  }
  //html += '<button class="btn btn-danger" onclick="deleteParent(this);">Remove</button>';
  html += "</div>";
  $(html).appendTo(container);
}

function makeFormElement(data){
  if(data.type == "text"){
    return '<div class="input-prepend"><label class="add-on">' + data.name + '</label><input type="text" data-key="' + data.key + '"/></div>';
  }
  else if(data.type == "bool"){
    return '<div class="input-prepend"><label class="add-on">' + data.name + '</label><input type="checkbox" data-key="' + data.key + '"/></div>';
  }else if(data.type == "location"){
    var a = '<div class="input-prepend input-append"><label class="add-on">' + data.name + '</label>';

    a+= '<span class="add-on">World</span><input type="text" data-key="' + data.key + '.world"/>';
    a+= '<span class="add-on">X</span><input class="input-mini" type="text" data-key="' + data.key + '.x"/>';
    a+= '<span class="add-on">Y</span><input class="input-mini" type="text" data-key="' + data.key + '.y"/>';
    a+= '<span class="add-on">Z</span><input class="input-mini" type="text" data-key="' + data.key + '.z"/>';
    a+= "</div>";
    return a;
  }else if(data.type == "cuboid"){
    var a = '<div class="input-prepend input-append"><label class="add-on">' + data.name + '</label>';

    a+= '<span class="add-on">World</span><input type="text" data-key="' + data.key + '.world"/><br/>';
    a+= '<span class="add-on">x1</span><input class="input-mini" type="text" data-key="' + data.key + '.v1.x"/>';
    a+= '<span class="add-on">y1</span><input class="input-mini" type="text" data-key="' + data.key + '.v1.y"/>';
    a+= '<span class="add-on">z1</span><input class="input-mini" type="text" data-key="' + data.key + '.v1.z"/><br/>';
    a+= '<span class="add-on">x2</span><input class="input-mini" type="text" data-key="' + data.key + '.v2.x"/>';
    a+= '<span class="add-on">y2</span><input class="input-mini" type="text" data-key="' + data.key + '.v2.y"/>';
    a+= '<span class="add-on">z2</span><input class="input-mini" type="text" data-key="' + data.key + '.v2.z"/>';
    a+= "</div>";
    return a;
  }
}

function elementToJSON(element){
  var _j = {
    "_type":element.attr("data-type"),
  };

  $("input,select",element).each(function(i,e){
    t = $(e);
    objectSet(_j,t.data("key"),t.val());
  });

  return _j;
}


function objectGet(obj,path,create){
  var paths = path.split(".");
  var current = obj;
  paths.forEach(function(idx){
    if(typeof current[idx] == "undefined" && create){
      current[idx] = {};
    }
    current = current[idx];
  });
  return current;
}


function objectSet(obj,path,value){
  bucketPath = path.split(".").splice(0);
  name = bucketPath.pop();
  carrier = obj;
  if(bucketPath.length > 0){
    carrier = objectGet(obj,bucketPath.join("."),true );
  }
  if(typeof carrier == "object"){
    carrier[name] = value;
  }
}

function datakeyToObj(dom,deep){
  obj = {};
  var i;
  if(deep){
    i = $("[data-key]",dom);
  }
  else
  {
    i = dom.children("[data-key]");
  }
  
  i.each(function(i,e){
    e = $(e);
    if(e.is("[type=checkbox]")){
      objectSet(obj,e.attr("data-key"),e.is(':checked'));
    }
    else
    {
      objectSet(obj,e.attr("data-key"),e.val());
    }
  });
  return obj;
}

function saveAchievement(achDom){
  achObj = datakeyToObj(achDom,false);
  achObj.triggers = [];
  //triggers
  $("#triggers > .element",achDom).each(function(i,e){
    achObj.triggers.push(datakeyToObj($(e),true));
  });

  achObj.rewards = [];
  //rewards
  $("#rewards > .element",achDom).each(function(i,e){
    achObj.rewards.push(datakeyToObj($(e),true));
  });
  
   

  return achObj;
}


function jsonToDom(json,dom,deep){

  if(deep){
    i = $("[data-key]",dom);
  }
  else
  {
    i = dom.children("[data-key]");
  }
  i.each(function(i,e){
    e = $(e);
    if(e.is("[type=checkbox]")){
      
        e.attr('checked',objectGet(json,e.attr("data-key"),false));
    }
    else
    {
      e.val(objectGet(json,e.attr("data-key"),false));
    }
  });
}

function loadAchievement(json){
  achObj = $('.ach-UI > .achievement').clone(true);

  jsonToDom(json,achObj,false);

//triggers
  for(x in json.triggers){
    console.log("reading: " + json.triggers[x]._type);
    t = $(".trigger-container > .element[data-type='" + json.triggers[x]._type + "']").clone(true);

    jsonToDom(json.triggers[x],t,true);
    console.log(t[0]);
    $('#triggers',achObj).append(t);
  }
//rewards
for(x in json.rewards){
    console.log("reading: " + json.rewards[x]._type);
    t = $(".reward-container > .element[data-type='" + json.rewards[x]._type + "']").clone(true);

    jsonToDom(json.rewards[x],t,true);
    console.log(t[0]);
    $('#rewards',achObj).append(t);
  }


  achObj.appendTo('.achievements');
}