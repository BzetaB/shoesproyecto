$(document).on("click", "#btnagregar", function(){
    $("#txtnomrol").val("")
    $("#hddidrol").val("0")
    $("#modalNuevo").modal("show")
})

$(document).on("click", ".btnactualizar", function(){
    $.ajax({
        type: "GET",
        url: "/seguridad/rol/"+$(this).attr("data-idrol"),
        dataType: "json",
        success: function(resultado){
            $("#txtnomrol").val(resultado.nomrol)
            $("#hddidrol").val(resultado.idrol)
            $("#modalNuevo").modal("show")
        }
    })

})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/seguridad/rol",
        contentType: "application/json",
        data: JSON.stringify({
            idrol: $("#hddidrol").val(),
            nomrol: $("#txtnomrol").val(),
        }),
        success: function(resultado){
            if(resultado.resultado){
                listarRol()
            }
            alert(resultado.mensaje)
        }
    });
    $("#modalNuevo").modal("hide");
})

function listarRoles(){
    $.ajax({
        type: "GET",
        url: "/seguridad/rol",
        dataType: "json",
        success: function(resultado){
            $("#tblroles > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblroles > tbody").append("<tr>"+
                `<td>${value.idrol}</td>`+
                `<td>${value.nomrol}</td>`+
                `<td><button type="button" class="btn btn-info btnactualizar" `+
                ` data-idrol="${value.idrol}">Actualizar`+
                `</button></td></tr>`)
            })
        }
    })
}