/**
 * Created by ozge on 19.08.2016.
 */


$(document).ready(function () {
    
    $('.ui.ten.minutes.button').on("click", function () {

         // var d = new Date("00:10:00");
         // $('#timeoutCalculation').val(d);

         $('#timeoutCalculation').val(10);

        // var timeout =  $('#timeoutCalculation').val(10);
    });

    $('.ui.thirty.minutes.button').on("click", function () {
        $('#timeoutCalculation').val(30);

    });

    $('.ui.noon.button').on("click", function () {
        $('#timeoutCalculation').val(12);

    });

    $('.ui.today.button').on("click", function () {
        $('#timeoutCalculation').val(00);

    });


    // function startTimer(duration, display) {
    //     var timer = duration, minutes, seconds;
    //     setInterval(function () {
    //         minutes = parseInt(timer / 60, 10);
    //         seconds = parseInt(timer % 60, 10);
    //
    //         minutes = minutes < 10 ? "0" + minutes : minutes;
    //         seconds = seconds < 10 ? "0" + seconds : seconds;
    //
    //         display.text(minutes + ":" + seconds);
    //
    //         if (--timer < 0) {
    //             timer = duration;
    //             //clearInterval(timer);
    //         }
    //     }, 1000);
    // }
    //
    // jQuery(function ($) {
    //     var minutes = 60 * 1,
    //         display = $('#clockdiv');
    //     startTimer(minutes, display);
    // });

    
    $('.ui.question.form').form({
        inline : true,
        on     : 'blur',
        fields: {
            name: {
                identifier: 'name',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please enter a name'
                    }
                ]
            },
            description: {
                identifier: 'description',
                rules: [
                    {
                        type   : 'empty',
                        prompt : 'Please enter a description '
                    }
                ]
            }
        }
    });
    
});




