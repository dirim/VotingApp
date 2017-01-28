/**
 * Created by ozge on 19.08.2016.
 */

$(document).ready(function () {

    // add default 2 choices
    $('#choices').append(createNewChoiceForm(0));
    $('#choices').append(createNewChoiceForm(1));

    $('.ui.ten.minutes.button').on("click", function () {

        var time = "00:10:00";
        var tt = time.split(":");
        var sec = tt[0]*3600 + tt[1]*60 + tt[2]*1;
        $('#timeoutCalculation').val(sec);
        $('#timeout').val(sec);
    });

    $('.ui.thirty.minutes.button').on("click", function () {

        var time = "00:30:00";
        var tt = time.split(":");
        var sec = tt[0]*3600 + tt[1]*60 + tt[2]*1;
        $('#timeoutCalculation').val(sec);
        $('#timeout').val(sec);
    });

    $('.ui.noon.button').on("click", function () {

        var noon = 120000;
        $('#timeoutCalculation').val("noon");
        $('*#timeout').val(noon);

    });

    $('.ui.today.button').on("click", function () {

        var midnight = 000000;
        $('#timeoutCalculation').val("midnight");
        $('#timeout').val(midnight);

    });

    $("#timeoutCalculation").prop("disabled", true);

    $('.ui.radio.checkbox').checkbox();
    
    $('.remove-choice').on("click", removeChoiceEvent);


    $('.add-choice').on("click", function () {
        var lastChoiceIndex = $('#choices .fields:last').data("index");
        lastChoiceIndex++;
        var newChoice = createNewChoiceForm(lastChoiceIndex);
        $('#choices').append(newChoice);
        $('.remove-choice').on("click", removeChoiceEvent);
    });

    function createNewChoiceForm(index) {
        var fields = $("<div class='fields' data-index='" + index + "'></div>");
        var inputField = $("<div class='fourteen wide field'><input type='text' class='choice-input-validation' name='choices[" + index + "].text' placeholder='choice " + index + "' /></div>");
        var removeField = $("<div class='two wide field'><button class='ui remove-choice icon button' data-index='" + index + "' type='button'><i class='trash icon'></i></button></div>");
        fields.append(inputField).append(removeField);
        return fields;
    }

    function removeChoiceEvent() {
        if($("#choicesForm .fields").length > 2) {
            var index = $(this).data("index");
            $('#choicesForm .fields[data-index="' + index + '"] ').remove();
        }
    }

    $(document).on("getQuestionResult", function(e) {
        e.preventDefault();
        var form = $(".answer.form");
        var questionid = $("[name='id']", form).val();

        $.ajax({
            url: "/questions/" + questionid + "/chart",
            method: "GET",
            success: function(response) {
                var data = {
                    customLabels: [],
                    series: []
                };

                $.each(response, function (key, value) {
                    data.customLabels.push(key);
                    data.series.push(value);
                });

                var sum = function(a, b) { return a + b };

                var globalIndex = 0;

                new Chartist.Pie('.ct-chart', data, {
                    labelInterpolationFnc: function(value) {
                        return data.customLabels[globalIndex++] + '(' + Math.round(value / data.series.reduce(sum) * 100) + '%' + value + 'votes)';
                    }
                });

            }
        });
    });
    
    function questionResult(e){}

    console.log($('.choice-input-validation').length);

    $.fn.form.settings.rules.myChoicesVal = function () {

        // at least 2 choice
        if($('.choice-input-validation').length < 2){
           return true;
        }
         
        // choice value cannot be null
        $('.choice-input-validation').each(function () {
            if($(this).val() === ""){
                return true;
            }
        });
    };

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
            },
            choices: {
                identifier: 'choices[0].text',
                rules: [
                    {
                        type   : 'myChoicesVal',
                        prompt : 'Please enter at least two choices'
                    }
                ]
            }
        }
    });

});




