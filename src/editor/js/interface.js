x = 0;
r = 5;
$(function(){

  $("#bootModal").modal({keyboard:false});
  loadConfig();
//Deep Clone and append to achievements list
  $('#newach').click(function(){
   $('.ach-UI > .achievement').clone(true).appendTo('.achievements');
 });

//Save to JSON, stick in output modal
  $('#saveach').click(function(){
    out = $('#output');
    a = [];
    $('.achievements > .achievement').each(function(i,e){
      a.push(saveAchievement($(e)));
    });
      out.val(JSON.stringify(a,null,"  "));
      window.saveAs(new Blob([JSON.stringify(a,null,"  ")]),"ach.json");
  });

// On Trigger selected, add to achievement
$('.add-trigger-btn').click(function(e){

  type = $('select.add-trigger',$(this).parent()).val();
  console.log("changed to " + type);
  cBody = $(".trigger-container > .element[data-type='" + type + "']").clone(true);
  

  $(this).parent().children("#triggers").append(cBody);
});


// On Reward selected, add to achievement
$('.add-reward-btn').click(function(e){

  type = $('select.add-reward',$(this).parent()).val();
  console.log("changed to " + type);
  cBody = $(".reward-container > .element[data-type='" + type + "']").clone(true);
  

  $(this).parent().children("#rewards").append(cBody);
});

//load file into program
$('#loadfileBtn').click(function(){
  loadFile($('#loadFile')[0]);
});

$('#search-ach').keyup(function(){
  txt = $(this).val();
  console.log("filtering:" + txt);
  $(".achievements > .achievement").removeClass("hide");
  if(txt.length > 0)
  {
    console.log("looping filter");
    $(".achievements > .achievement").filter(function(i){

      return !($("input[data-key=slug]",this).val().indexOf(txt) !== -1 || $("input[data-key=name]",this).val().indexOf(txt) !== -1);

    }).addClass("hide");
  }
});
});

function bootProgressUpdate(percent,status){
  $("#boot-progress").css("width","" + percent+ "%");
  $("#status").text(status);
  if(percent>=100){
    $("#boot-progress").css("width","100%");
    $("#bootModal").modal('hide')
  }
}

function deleteElement(t){
  console.log("delete!");
  $(t).parent().remove();
}


function collapseBody(t){
  $('.accordion > .accordion-body').collapse();
  $('.accordion-body',$(t).parent().parent()).collapse('toggle');
}

function addTriggerToSelect(trig){
  console.log("adding " + trig.type + " : " + trig.name);
  
  $('.add-trigger').append($("<option/>", {
    value: trig.type,
    text: trig.name
  }));
}

function addRewardToSelect(reward){
  console.log("adding " + reward.type + " : " + reward.name);
  
  $('.add-reward').append($("<option/>", {
    value: reward.type,
    text: reward.name
  }));
}


function loadFile(t){
  fr = new FileReader();
  
  fr.onload = function(){
    achievements = JSON.parse(fr.result);
    for(x in achievements){
      loadAchievement(achievements[x]);
      $('#loadModal').modal('hide');
    }
  }

  fr.readAsText(t.files[0]);
}