//interaction js code
// javascript modularization(package.class.method)

var seckill = {

    // ajax related url
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    //validate phone number
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;//whether is null,null is undefine false; isNaN not number return true
        } else {
            return false;
        }
    },

    //Detail page logic
    detail: {
        //initialization
        init: function (params) {
            //phone number sign in
            //
            //check phone number in cookie
            var userPhone = $.cookie('userPhone');
            //validate phone number
            if (!seckill.validatePhone(userPhone)) {
                //bind in phone number
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,//pop up
                    backdrop: 'static',
                    keyboard: false//turn down keyboard
                });

                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log("inputPhone: " + inputPhone);
                    if (seckill.validatePhone(inputPhone)) {
                        //write in cookie(expire in 7 days)
                        $.cookie('userPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //validation pass　　refresh page
                        window.location.reload();
                    } else {
                        //todo wrong message write in dictionary
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }

            //have signed in 
            //time interaction
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                   
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result: ' + result);
                    alert('result: ' + result);
                }
            });
        }
    },

    handlerSeckill: function (seckillId, node) {
        //get seckill url,control monitor,execute seckill
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">Start Seckill</button>');

        $.get(seckill.URL.exposer(seckillId), {}, function (result) {
        
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    //launch seckill
                    //get scekill url
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log("killUrl: " + killUrl);
                    //click event
                    $('#killBtn').one('click', function () {
                        
                        //1.disable button
                        $(this).addClass('disabled');//,<-$(this)===('#killBtn')->
                        //2.send require
                        $.post(killUrl, {}, function (result) {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                var stateInfo = killResult['stateInfo'];
                                //show seckill result
                                node.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                            else
                            {
                                var killResult = result['data'];
                                var state = killResult['state'];
                                 var stateInfo = killResult['stateInfo'];
                                 //show seckill result
                                 node.html('<span class="label label-danger">' + stateInfo + '</span>');
 
                            }
               
                        });
                    });
                    node.show();
                } else {
                    //show time
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    seckill.countDown(seckillId, now, start, end);
                }
            } else {
                console.log('result: ' + result);
            }
        });

    },

    countDown: function (seckillId, nowTime, startTime, endTime) {
        console.log(seckillId + '_' + nowTime + '_' + startTime + '_' + endTime);
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            //seckill over
            seckillBox.html('Seckill Over!');
        } else if (nowTime < startTime) {
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                //时间格式
                var format = event.strftime('Seckill Count Down: %DDay(s) %HHour(s) %MMinute(s) %SSecond(s) ');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                //return event
                console.log('______fininsh.countdown');
                seckill.handlerSeckill(seckillId, seckillBox);
            });
        } else {
            //seckill Start!
            seckill.handlerSeckill(seckillId, seckillBox);
        }
    }

}