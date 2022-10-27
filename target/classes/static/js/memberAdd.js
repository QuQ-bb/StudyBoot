console.log("응애")

$("#all").click(function(){
    console.log("아 배고파진짜")
   let ch = $(this).prop("checked")
    $(".check").prop("checked",ch);
});

// $(".check").click(function(){
//     let flag =true;
//     $(".check").each(function(index,item){
//        let cc = $(item).prop("checked");
//        if(!cc){
//            flag = false;
//         }
//         console.log(cc);
//     });
//     //모두 checked 되었는가? or 하나이상이 check인가
//    $("#all").prop("checked",flag);
// });

$(".check").click(function(){
    $("#all").prop("checked",true);

    $(".check").each(function(index,item){
       let cc = $(item).prop("checked");
       if(!cc){
           $("#all").prop("checked",false);
           return false;
        }
        console.log(cc);
    });
});

//id,pw,pw2Equals,name,email
let results = [false,false,false,false,false];


//ID Check
$("#id").blur(function(){
    let id =$("#id").val();
    let result = nullCheck(id, "#check_id", "아이디")
    results[0]=result
    //단 id가 비어있지않을 때 실행
    if(id != ""){
        $.get("./idCheck?id="+id,function(data){
            console.log("Data : ",data);
            if(data === 0){
                $("#check_id").html("사용가능한 ID")
                results[0]=true;
            }else{
                $("#check_id").html("이미 사용중인 ID")
                results[0]=false;
            }
        });
    }
});



//pw Check
// $("#pw").blur(function(){
//     let result = nullCheck($("#pw").val(),"#check_pw","비밀번호")
//     results[1]=result
    // console.log("result pw: ", result)

    // if(result){
    //     $("#check_pw").html("");
    // }else{
    //     $("#check_pw").html("비밀번호를 입력해주세요.")
    // }
// });

$("#pw").on({
    blur : function(){
        let result = nullCheck($("#pw").val(),"#check_pw","비밀번호")
        results[1]=result
    },
    change :function(){
        let result = equals($("#pw").val(),$("#pw2").val());

    if(result){
        $("#check_pw2").html("");
    }else{
        if($("#pw2").val()==""){
            $("#check_pw2").html("");
        }else{
            $("#check_pw2").html("아니 비밀번호가 바꼈잖아요;.")
        }
    }
    results[2]=result;
    }
});

//pw Equal Check
$("#pw2").blur(function(){
    let result = equals($("#pw").val(),$("#pw2").val())
    console.log("result pw2: ", result)
    
    if(result){
        $("#check_pw2").html("");
    }else{
        $("#check_pw2").html("비밀번호가 틀립니다.")
    }
    results[2]=result
});

//name Check
$("#name").blur(function(){
    let result = nullCheck($("#name").val(),"#check_name","이름")
    results[3]=result
    // console.log("result name: ", result)

    // if(result){
    //     $("#check_name").html("");
    // }else{
    //     $("#check_name").html("이름을 입력해주세요.")
    // }
})

//email Check
$("#email").blur(function(){
    let result = nullCheck($("#email").val(),"#check_email","이메일")
    results[4]=result
    // console.log("result email: ", result)

    // if(result){
    //     $("#check_email").html("");
    // }else{
    //     $("#check_email").html("이름을 입력해주세요.")
    // }
})

$("#joinButton").click(function(){
    if(results.includes(false)){
        alert("필수 입력은 다 입력하시오")
    }else{
        alert("전송")
        // $("#joinForm").submit();
    }

    // let c = true;
    // $.each(results,function(index,item){
    //     if(!item){
    //         alert("필수사항 모두 입력해주세요");
    //         c =false;
    //         return c;
    //     }
    // });
    // if(c){
    //     $("#joinForm").submit();
    // }
});


// $("#joinButton").click(function(){

    // let idCheck = true;
    // let pwCheck = true;
    // let pw2Check = true;
    // let nameCheck = true;
    // let emailCheck = true;

    // if($("#id").val()=="") idCheck=false;
    // if($("#pw").val()=="") pwCheck=false;
    // if($("#pw2").val()=="" || $("#pw").val() != $("#pw2").val()) pw2Check=false;
    // if($("#name").val()=="") nameCheck=false;
    // if($("#email").val()=="") emailCheck=false;
    // if(idCheck && pwCheck && pw2Check && nameCheck && emailCheck){
    //     $("#joinForm").submit();
    // }


    // if($("#id").val()==""){
    //     $("#check_id").text("아이디를 입력해주세요.")
    // }else if($("#pw").val()==""){
    //     $("#check_pw").text("비밀번호를 입력해주세요.")
    // }else if($("#pw2").val()==""){
    //     $("#check_pw2").text("비밀번호를 재입력해주세요.")
    // }else if($("#pw2").val() != $("#pw").val()){
    //     $("#check_pw2").text("비밀번호를 다시 확인해주세요.")
    // }else if($("#name").val()==""){
    //     $("#check_name").text("이름을 입력해주세요.")
    // }else if($("#email").val()==""){
    //     $("#check_email").text("이메일을 입력해주세요.")
    // }else{
    //     //event 강제 실행
    //     $("#joinForm").submit();
    // }

// });












