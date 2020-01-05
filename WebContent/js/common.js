
let destroy = document.getElementById('destroy');

destroy.addEventListener( 'click' , ()=> {
    if(confirm("本当に削除してよろしいですか?")){
        document.forms[1].submit();
    }
},false);