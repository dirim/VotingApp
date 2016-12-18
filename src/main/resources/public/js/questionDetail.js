// /**
//  * Created by ozge on 11.12.2016.
//  */
//
// function time () {
//
//     var timeout = [[${remainingTime}]];
//     var questionAnswered = [[${answered}]];
//
//     if (questionAnswered) {
//         jQuery(function ($) {
//             $("#vote").prop("disabled", true);
//
//             $('input[name="choiceId"]').attr('disabled', 'disabled');
//
//         });
//     }
//
//     function disableTimeout(){
// //        if (questionAnswered || timeout <= 0) {
//         jQuery(function ($) {
//             $("#vote").prop("disabled", true);
//
//             $('input[name="choiceId"]').attr('disabled', 'disabled');
//
//         });
// //        }
//     }
//
//     function startTimer(duration, display) {
//         var timer = duration;
//         var hours;
//         var minutes;
//         var seconds;
//
//         setInterval(function () {
//             hours = parseInt(timer / 3600) % 24;
//             minutes = parseInt((timer % 3600) / 60);
//             seconds = parseInt(timer % 3600 ) % 60;
//
//             hours = hours < 10 ? "0" + hours : hours;
//             minutes = minutes < 10 ? "0" + minutes : minutes;
//             seconds = seconds < 10 ? "0" + seconds : seconds;
//
//             display.text(hours + ":" + minutes + ":" + seconds);
//
//             if (--timer < 0) {
//                 clearInterval(timer);
//                 display.text("Time is up");
//                 disableTimeout();
//                 $(document).trigger("getQuestionResult");
//             }
//         }, 1000);
//     }
//
//     jQuery(function ($) {
//         var durationInSeconds = timeout / 1000;
//         var display = $('#timer');
//         startTimer(durationInSeconds, display);
//     });
//
// }