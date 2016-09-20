/**
 * Created by ozge on 19.08.2016.
 */

$(document).ready(function () {
    
    $('.ui.ten.minutes.button').on("click", function () {

         $('#timeoutCalculation').val(10);

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

    $('.ui.icon.button').on("click", function () {
       $(".fourteen.wide.field").remove();

    });

    
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




