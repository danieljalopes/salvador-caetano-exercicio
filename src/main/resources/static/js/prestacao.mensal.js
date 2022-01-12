var PrestacaoMensal = function(){
    var handleValidation = function(){
        var form = $('#CalcularPrestacaoForm');

        form.validate({
            rules: {
                tipoFinanciamento:{
                    required: true,

                },
                numPrestacoes:{
                    required: true,
                    digits: true
                },
                valorViatura:{
                    required: true,
                    number: true,
                }
            },
            messages:{
                tipoFinanciamento:{
                    required: "Obrigatorio",

                },
                numPrestacoes:{
                    required: "Obrigatorio",
                    digits: "Numeros Inteiros"
                },
                valorViatura:{
                    required: "Obrigatorio",
                    number: "Numeors decimais",
                }
            },

        })
    };

    var CalcularPrestacaoFormSent = false;
    var CalcularPrestacaoForm = function (){

        $(document).on('submit', '#CalcularPrestacaoForm', function (e) {
            console.log("xxx");
            e.preventDefault();

            var form = $(this);
            var _url = 'http://localhost:8080/api/prestacao-mensal/calcular'

            var _tipoFinanciamento = $( "#tipoFinanciamento option:selected" ).text();
            var _numPrestacoes = parseInt($("#numPrestacoes").val());
            var _valorViatura = parseFloat($("#valorViatura").val());

            if(!CalcularPrestacaoFormSent){
                $("#success").empty();
                $("#error").empty();
                CalcularPrestacaoFormSent = true;
                $.ajax({
                    processData: true,
                    url: _url, // url where to submit the request
                    type : "POST", // type of action POST || GET
                    contentType: "application/json",
                    dataType : 'json', // data type
                    data :  JSON.stringify({
                        tipoFinanciamento : _tipoFinanciamento,
                        numPrestacoes: _numPrestacoes,
                        valorViatura: _valorViatura
                     }), // post data || get data
                    success : function(result) {
                        // you can see the result from the console
                        // tab of the developer tools
                        console.log(result);

                        $("#success").text(result.valorPrestacao);

                    },
                    error: function(xhr, resp, text) {
                        console.log(xhr, resp, text);
                        if(xhr.status == 500){
                            alert("Erro Servidor")
                        }else{
                            var err = eval("(" + xhr.responseText + ")");
                            alert(err.message);
                        }
                    }
                })
                CalcularPrestacaoFormSent = false;
            }

        })
    };

    return {
        //main function to initiate the module
        init: function () {
            handleValidation();
            CalcularPrestacaoForm();
        }
    };
}();