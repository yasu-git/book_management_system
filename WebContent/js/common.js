
let destroy = document.getElementById('destroy');

destroy.addEventListener( 'click' , ()=> {
    if(confirm("本当に削除してよろしいですか?")){
        document.forms[1].submit();
    }
},false);

let isbn = document.getElementById('input_isbn');
let isbn_form = document.getElementById('isbn_form');

isbn.addEventListener('change',()=> {
    isbn_form.submit();
})