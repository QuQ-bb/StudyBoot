console.log("javascript")

$("#btn").click(function(){
  console.log("응애애애애")  
});

$(".buttons").click(function(){
    console.log("buttons");
});

$("#test").on("click","#btn2",function(){});