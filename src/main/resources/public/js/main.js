/**
 * Created by ozge on 19.08.2016.
 */

$(document).ready(function () {
    
    $('.ui.ten.minutes.button').on("click", function () {

        $('#timeoutCalculation').val(10);

        // var d = new Date("00:10:00");
        // $('#timeoutCalculation').val(d);

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
            success: function(response) {
                console.log(response);
                
            }
        });
    }

    $('#seeResult').on("click", questionResult);


    // var data = {
    //     series: [5, 3, 2]
    // };
    //
    // var sum = function(a, b) { return a + b };
    //
    // new Chartist.Pie('.ct-chart', data, {
    //     labelInterpolationFnc: function(value) {
    //         return Math.round(value / data.series.reduce(sum) * 100) + '%';
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




