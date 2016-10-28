/**
 * Created by ozge on 19.08.2016.
 */

$(document).ready(function () {
    
    $('.ui.ten.minutes.button').on("click", function () {

       // $('#timeoutCalculation').val(10);

        var time = "00:10:00";
        var tt = time.split(":");
        var sec = tt[0]*3600 + tt[1]*60 + tt[2]*1;
        $('#timeoutCalculation').val(sec);
    });

    $('.ui.thirty.minutes.button').on("click", function () {

       // $('#timeoutCalculation').val(30);

        var time = "00:30:00";
        var tt = time.split(":");
        var sec = tt[0]*3600 + tt[1]*60 + tt[2]*1;
        $('#timeoutCalculation').val(sec);
    });

    $('.ui.noon.button').on("click", function () {
       // $('#timeoutCalculation').val(12);

        var time = "12:00:00";
        var tt = time.split(":");
        var sec = tt[0]*3600 + tt[1]*60 + tt[2]*1;
        $('#timeoutCalculation').val(sec);

    });

    $('.ui.today.button').on("click", function () {
       // $('#timeoutCalculation').val(00);

        var time = "00:00:00";
        var tt = time.split(":");
        var sec = tt[0]*3600 + tt[1]*60 + tt[2]*1;
        $('#timeoutCalculation').val(sec);

    });

    $('.remove-choice').on("click", removeChoiceEvent);

    var lastChoiceIndex = $('#choices .fields:last').data("index");
    $('.add-choice').on("click", function () {
        lastChoiceIndex++;
        var newChoice = createNewChoiceForm(lastChoiceIndex);
        $('#choices').append(newChoice);
        $('.remove-choice').on("click", removeChoiceEvent);
    });

    function createNewChoiceForm(index) {
        var fields = $("<div class='fields' data-index='" + index + "'></div>");
        var inputField = $("<div class='fourteen wide field'><input type='text' name='choices[" + index + "].text' placeholder='choice " + index + "' /></div>");
        var removeField = $("<div class='two wide field'><button class='ui remove-choice icon button' data-index='" + index + "' type='button'><i class='trash icon'></i></button></div>");
        fields.append(inputField).append(removeField);
        return fields;
    }

    function removeChoiceEvent() {
        var index = $(this).data("index");
        $('#choicesForm .fields[data-index="' + index + '"] ').remove();

        if($("#choicesForm .fields").length == 2) {
            $('.remove-choice').off("click");
        }
    }
    
    function questionResult(){
        var form = $(".answer.form");
        var questionid = $("[name='id']", form).val();

        $.ajax({
            url: "/questions/" + questionid + "/chart",
            method: "GET",
            success: function(data) {
                
                console.log(data);

                var data = {
                    series: [5, 3, 2]
                };

                var sum = function(a, b) { return a + b };

                new Chartist.Pie('.ct-chart', data, {
                    labelInterpolationFnc: function(value) {
                        return Math.round(value / data.series.reduce(sum) * 100) + '%';
                    }
                });
                
            }
        });
    }

    $('#seeResult').on("click", questionResult);

    //
    // var data = {
    //     customLabels:['Choice1', 'Choice2', 'Choice3'],
    //     series: [5, 3, 2]
    // };
    //
    // var sum = function(a, b) { return a + b };
    //
    // var globalIndex = 0;
    //
    // new Chartist.Pie('.ct-chart', data, {
    //     labelInterpolationFnc: function(value) {
    //         return data.customLabels[globalIndex++] + '(' + Math.round(value / data.series.reduce(sum) * 100) + '%' + value + 'votes)';
    //     }
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




