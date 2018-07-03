
 function doSubmit(){
    var userPassword = $('#userPassword').val();
    var newPassword = $('#newPassword').val();
    var repeatPassword = $('#repeatPassword').val();
    
    if($.trim(userPassword) == ''){
        $('#errorMessageDiv').html('原密码不能为空');
        return ;
    }
    if($.trim(userPassword).length < 6){
        $('#errorMessageDiv').html('原密码至少6位');
        return ;
    }
    
    if($.trim(newPassword) == ''){
        $('#errorMessageDiv').html('新密码不能为空');
        return ;
    }
    if($.trim(newPassword).length < 6){
        $('#errorMessageDiv').html('新密码至少6位');
        return ;
    }
    
    if($.trim(repeatPassword) == ''){
        $('#errorMessageDiv').html('确认密码不能为空');
        return ;
    }
    if(repeatPassword != newPassword){
        $('#errorMessageDiv').html('新密码与确认密码不一致');
        return ;
    }
    
    $('#form_update').submit();
}