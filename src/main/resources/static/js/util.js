// dest - check결과값 내어주는 div값
// kind - check해야할 종류(ex) ID,PW,NAME...
function nullCheck(data, dest, kind){
    if(data == null || data ==''){
        $(dest).html(kind+" 필수입니다.");
        return false;
    }else{
        $(dest).html("정상");
        return true;
    }
}

function equals(data,checkData){
    if(data == checkData){
        return true;
    }else{
        return false;
    }
}