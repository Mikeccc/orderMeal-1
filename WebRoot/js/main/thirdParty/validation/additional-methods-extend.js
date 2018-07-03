/**
 * jQuery validation 1.5.5 验证扩展
 * @author tipx
 * @last 2009-09-22
 * @update 分离验证提示信息, 以及使用$.extend扩展验证方法
 */
;(function($){

    //为validator类添加validArea方法
    $.extend($.validator.prototype, {
        /*
         * 验证区域
         * @param selector 待验证区域的选择器
         */
        validArea : function(selector) {
            var flag=true, validator=this;
            //遍历表单中所有的待验证元素
            validator.elements().each(function() {
                var $el = $(this);
                //匹配在验证区域内的待验证元素
                if($el.closest(selector).length == 1){
                    //验证条件为false=>不验证时, validator.element($el)返回undefined
                    if(false === validator.element($el)){flag=false;}
                }
            })
            return flag;
        }
    });

    //添加验证的默认提示信息
    $.extend(jQuery.validator.messages, {
    	validator: $.validator.format("Please enter a valid text."),
    	minLength2: $.validator.format("Please enter at least {0} characters."),
    	maxLength2: $.validator.format("Please enter no more than {0} characters."),
    	rangeLength2: $.validator.format("Please enter a value between {0} and {1} characters long."),
        ip: "Please enter a valid ip.",
        iprange: $.validator.format("Please enter a ip between {0} and {1}."),
        password: $.validator.format("The password's level must be large than {0}."),
        zip: "Please enter a valid zip.",
        mobile: "Please enter a valid mobile number.",
        mobileYD: "Please enter a valid chinamobile's number.",
        enwords: "Numbers only please.",
        enwords: "Letters only please.",
        cnwords: "Chinese words only please.",
        idtype: "Letters, numbers or underscores only please.",
        alphanumeric: "Letters(first), numbers or underscores only please.",
        percent: "Please enter a valid percent(eg: 99.5%)",
        regexp: $.validator.format("Please enter a valid string for the rule {0}"),
        remote: "Please fix this field.",
        datetime: $.validator.format("The datetime's format must be like '{0}'"),
        idcard: "Please enter a valid idcard number."
    });

    //添加验证信息 - 由于扩展的方法数量较多, 故直接用$.extend, 而不使用addMethod
    $.extend(jQuery.validator.methods, {
        //validator, 使用自定义函数验证, param为验证函数的返回值，返回true则验证通过
        "validator": function(value, element, param) {
        	return this.optional(element) || true === param;
        },

        //验证字符长度：中文=2字节
        "minLength2": function(value, element, param) {
            return this.optional(element) || $.trim(value).replace(/[^\x00-\xff]/g,"**").length >= param;
        },
        "maxLength2": function(value, element, param) {
            return this.optional(element) || $.trim(value).replace(/[^\x00-\xff]/g,"**").length <= param;
        },
        "rangeLength2": function(value, element, params) {
            var length = $.trim(value).replace(/[^\x00-\xff]/g,"**").length;
            return this.optional(element) || ( length >= params[0] && length <= params[1] );
        },

        //ip
        "ip": function(value, element) {
            return this.optional(element) || /^([1-9]|[1-9]\d|1\d{2}|2[0-1]\d|22[0-3])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}$/.test(value);
        },

        /*
         * ip范围
         * params:['192.168.0.1', '192.168.255.255']
         */
        "iprange": function(value, element, params) {
            var pattern=/([^\d])(\d{1,2})(?=\.|\b)/g;
            var min=params[0].replace(pattern, '$100$2'); //不足3位补0
            var max=params[1].replace(pattern, '$100$2');
            var v=value.replace(pattern, '$100$2');

            return this.optional(element) || (v && min && max) && (v>=min && v<=max);
        },

        /*
         * 密码等级
         * param:2 注：[1-4]对应[弱-极佳]
         */
        "password": function(value, element, param) {

            //获取密码等级
            function getPasswordLevel(value) {
                var level=0, len=value.length;
                if(len<6){
                    level+= len>0 ? 1:0;
                }else{
                    level+= (value.search(/[a-zA-Z]/)!=-1) ? 1:0;
                    level+= (value.search(/[\d]/)!=-1) ? 1:0;
                    level+= (value.search(/[^\w]/)!=-1) ? 1:0;
                    level+= len>10 ? 1:0;
                }
                return level;
            }
            if(!element.onkeyup){
                $(element).keyup(function(){
                    //'passwordLevel_'为密码等级图片显示的固定容器ID
                    $('#passwordLevel_').css('background-position', 'left '+(getPasswordLevel(this.value)*25)+'%');
                });
            }
            var level=getPasswordLevel(value);
            $('#passwordLevel_').css('background-position', 'left '+(level*25)+'%');
            return this.optional(element) || level>=param;
        },

        //邮编
        "zip": function(value, element) {
            return this.optional(element) || /^[1-9]\d{5}$/.test(value);
        },

        //验证手机
        "mobile": function(value, element) {
            return this.optional(element) || /^((\(\d{2,3}\))|(\d{3}\-))?((13\d{9})|(15[389]\d{8}))$/.test(value);
        },

        //验证中国移动手机
        "mobileYD": function(value, element) {
            return this.optional(element) || /^((((13[5-9]{1})|(15[0,1,2,7,8,9]{1})|(147{1})|(18[2,3,7,8])){1}\d{1})|((134[0-8]{1}){1})){1}\d{7}$/.test(value);
        },

        //只允许数字
        "number": function(value, element) {
            return this.optional(element) || /^\d+$/i.test(value);
        },

        //只允许E文字符
        "enwords": function(value, element) {
            return this.optional(element) || /^[a-z]+$/i.test(value);
        },

        //只允许中文字符
        "cnwords": function(value, element) {
            return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);
        },

        //编号类型：字母、数字、下划线、减号
        "idtype": function(value, element) {
            return this.optional(element) || /^(\w|\-)*$/i.test(value);
        },

        //字母开头,只含有字母、数字、下划线
        "alphanumeric": function(value, element) {
            return this.optional(element) || /^[a-z]\w*$/i.test(value);
        },

        //百分比 - 允许带小数 如:99.5%
        "percent": function(value, element, param) {
            return this.optional(element) || /^\d+(\.\d+)?%$/.test($.trim(value));
        },

        //根据自定义的正则表达式验证
        "regexp": function(value, element, param) {
            var p = (param.constructor == RegExp) && param;
            return this.optional(element) || (p && p.test($.trim(value)));
        },


        /**
         * Ajax验证 - 修改并覆盖官方的remote方法
         *
         * param 有string与json两种格式
         *       string : 验证请求的url //服务器返回true或false确定验证结果
         *       json   : {url:'',
         *                 callback:function(json){}, //验证的回调函数，若存在该函数，则该函数必须返回的true/false作为验证结果
         *                 datas:{key: value}, //附加参数, 需要使用某DOM元素的值, 则在开头处加上#, 只适用于通过id取值
         *                 //注: 核心的success方法已作防覆盖处理
         *                }
         */
        "remote": function(value, element, param) {
            if ( this.optional(element) )
                return "dependency-mismatch";

            var previous = this.previousValue(element);

            if (!this.settings.messages[element.name] )
                this.settings.messages[element.name] = {};
            this.settings.messages[element.name].remote = typeof previous.message == "function" ? previous.message(value) : previous.message;

            //--------参数处理--------
            param = typeof param == "string" && {url:param} || param;

            //如果存在success属性,则将其删除,防止覆盖$.ajax的success方法
            if(param.success){delete param.success;}

            //用param存储data,后面$.ajax提交时extend
            var tmp = param.data = {};
            var datas = param.datas;
            if(datas){
                for(var prop in datas){
                    if(/^#/.test(datas[prop])){
                        tmp[prop] = $(datas[prop]).val();
                    }else{
                        tmp[prop] = datas[prop];
                    }
                }
            }
            //--------参数处理--------

            if ( previous.old !== value ) {
                previous.old = value;
                var validator = this;
                this.startRequest(element);
                var data = {};
                data[element.name] = value;
                $.ajax($.extend(true, {
                    url: param,
                    mode: "abort",
                    port: "validate" + element.name,
                    dataType: "json",
                    data: data,
                    //----添加callback----
                    //外部可通过param重写callback,用于重写判断正确的条件及其它处理
                    callback: function(response) {
                        return response === true;
                    },
                    //----添加callback----end
                    success: function(response) {
                        //修改---开始
                    //	var valid = response === true; //old
                        var valid = this.callback(response);//new: 使用callback返回值判断正确或错误
                        //修改---结束
                        if ( valid ) {
                            var submitted = validator.formSubmitted;
                            validator.prepareElement(element);
                            validator.formSubmitted = submitted;
                            validator.successList.push(element);
                            validator.showErrors();
                        } else {
                            var errors = {};
                            //修改---开始
                            //old: response不为true时，则为错误提示信息
                        //	errors[element.name] = previous.message = response || validator.defaultMessage( element, "remote" );
                            //new: 服务器返回的response不作提示信息考虑!
                        //  var errMsgs = response.constructor === String ? validator.defaultMessage( element, "remote" ) : response;
                            errors[element.name] = previous.message = validator.defaultMessage( element, "remote" );
                            //修改---结束
                            validator.showErrors(errors);
                        }
                        previous.valid = valid;
                        validator.stopRequest(element, valid);
                    }
                }, param));
                return "pending";
            } else if( this.pending[element.name] ) {
                return "pending";
            }
            return previous.valid;
        },

        /**
         * 日期验证
         *
         * 根据参数的格式确定值的格式
         * param: 'yyyy' - Year, 'MM' - Month, 'dd' - Day
                  'hh' - Hour, 'mm' - Minutes, 'ss' - Seconds
         *
         * eg: datetime:'yyyy-MM-dd'
         *
         */
        'datetime': function(value, element, param){

            //非必填
            if(this.optional(element)) return true;

            var reObj=false; // 这个参数为true时，传入的value与format格式一致则返回日期
            format = param || 'yyyy-MM-dd';
            var o = {}, d = new Date();
            var f1 = format.split(/[^a-z]+/gi), f2 = value.split(/\D+/g), f3 = format.split(/[a-z]+/gi), f4 = value.split(/\d+/g);
            var len = f1.length, len1 = f3.length;
            if(len != f2.length || len1 != f4.length) return false;
            for(var i = 0; i < len1; i ++)if(f3[i] != f4[i]) return false;
            for(var i = 0; i < len; i ++) o[f1[i]] = f2[i];
            o.yyyy = s(o.yyyy, o.yy, d.getFullYear(), 9999, 4);
            o.MM = s(o.MM, o.M, d.getMonth() + 1, 12);
            o.dd = s(o.dd, o.d, d.getDate(), 31);
            o.hh = s(o.hh, o.h, d.getHours(), 24);
            o.mm = s(o.mm, o.m, d.getMinutes());
            o.ss = s(o.ss, o.s, d.getSeconds());
            o.ms = s(o.ms, o.ms, d.getMilliseconds(), 999, 3);
            if(o.yyyy + o.MM + o.dd + o.hh + o.mm + o.ss + o.ms < 0){ return false;}
            if(o.yyyy < 100){ o.yyyy += (o.yyyy > 30 ? 1900 : 2000)};
            d = new Date(o.yyyy, o.MM - 1, o.dd, o.hh, o.mm, o.ss, o.ms);
            var reVal = d.getFullYear() == o.yyyy && d.getMonth() + 1 == o.MM && d.getDate() == o.dd && d.getHours() == o.hh && d.getMinutes() == o.mm && d.getSeconds() == o.ss && d.getMilliseconds() == o.ms;
            return reVal && reObj ? d : reVal;
            function s(s1, s2, s3, s4, s5){
                s4 = s4 || 60, s5 = s5 || 2;
                var reVal = s3;
                if(s1 != undefined && s1 != '' || !isNaN(s1)) reVal = s1 * 1;
                if(s2 != undefined && s2 != '' && !isNaN(s2)) reVal = s2 * 1;
                return (reVal == s1 && s1.length != s5 || reVal > s4) ? -10000 : reVal;
            }
        },

        /**
         * 身份证验证
         * param:为true时，验证18与15位，值为18时，只验证18位
         * eg: idcard:true 或者 idcard:18
         */
        'idcard': function(value, element, param){

            //非必填
            if(this.optional(element)) return true;

            if(18==param && 18 != value.length) return false;
            var number = value.toLowerCase();
            var d, sum = 0, v = '10x98765432', w = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2], a = '11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91';
            var re = number.match(/^(\d{2})\d{4}(((\d{2})(\d{2})(\d{2})(\d{3}))|((\d{4})(\d{2})(\d{2})(\d{3}[x\d])))$/);
            if(re == null || a.indexOf(re[1]) < 0) return false;
            if(re[2].length == 9){
                number = number.substr(0, 6) + '19' + number.substr(6);
                d = ['19' + re[4], re[5], re[6]].join('-');
            } else d = [re[9], re[10], re[11]].join('-');
            // yyyy-MM-dd 支持对润年等的验证
            var dataP = /^((((((0[48])|([13579][26])|([2468][048]))00)|([0-9][0-9]((0[48])|([13579][26])|([2468][048]))))-02-29)|(((000[1-9])|(00[1-9][0-9])|(0[1-9][0-9][0-9])|([1-9][0-9][0-9][0-9]))-((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30))|(((0[1-9])|(1[0-2]))-((0[1-9])|(1[0-9])|(2[0-8]))))))$/i;
            if(!dataP.test(d)) return false;
            //下面一行为调用自身插件datetime方法验证身份证中的日期
        //    if(!$.validator.methods.datetime.call(this, $.trim(d), element, 'yyyy-MM-dd')) return false;
            for(var i = 0;i < 17; i++) sum += number.charAt(i) * w[i];
            return re[2].length == 9 || number.charAt(17) == v.charAt(sum % 11);
        }
    });
})(jQuery);