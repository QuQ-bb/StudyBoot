let count =0;
$("#addbtn").click(function(){
    if(count >= 5){
        alert("파일 추가는 5개까지만 가능합니다.")
        return false;
    }
    
    let add= '<div class="mb-3">'
        add = add + '<input type="file" class="form-control" name="files" id="file'+count+'" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">'
        add = add + '<button type="button" class="del">Delete</button>';
        add = add + '</div>'
    $("#add").append(add);
    count++;
});

//현재 .del은 동적태그로 기존에 있지않은 태그이기때문에 
//일반적인 click를 줄수없다.
$("#add").on("click",".del",function(){
    console.log("Delete");
    $(this).parent().remove();
    count--;
});

//글 수정시 첨부파일 삭제
$(".deleteFile").click(function(){
    //DB,HDD에 파일 삭제
    console.log("Before Result THIS: " , $(this));
    let check = confirm("삭제됩니다 복구되지않습니다.");
    let fileNum = $(this).attr("data-fileNum");
    if(check){
        //post
        // /qna/fileDelete
        //parameter fileNum
        const btn = $(this);
        $.ajax({
            type:"POST",
            url:"fileDelete",
            data:{
                fileNum:fileNum
            },
            success:function(result){
                console.log("Result : ",result)
                console.log("After Result THIS: " , $(this));
                $(btn).parent().remove();
            },
            error:function(xhr,status){
                console.log("xhr : ",xhr)
            }
        })
    }
});

