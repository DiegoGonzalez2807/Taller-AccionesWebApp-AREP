/**
 * author: Diego Gonzalez
 */


/**
 * Funcion generada para volver invisibles los botones 
 * en la pagina HTML
 */
function invisibleInformation(){
    document.getElementById('intra').style.visibility = "hidden";
    document.getElementById('daily').style.visibility = "hidden";
    document.getElementById('week').style.visibility = "hidden";
    document.getElementById('month').style.visibility = "hidden";
}

invisibleInformation()


/**
 * Funcion encargada de enviar la señal de peticion
 * al backend para que este pueda traer la informacion
 * de la empresa mediante la API
 */
function loadValues(){
    var URL_API = "http://localhost:4567/search/"+document.getElementById("empresa").value;
    axios.get(URL_API)
        .then(function(res){
            visibleInformation(res)
            console.log(res.data)
        })
        .catch(function (error) {
            console.log(error)
        })
}

/**
 * Funcion encargada de poner visibles los botones en la pagina 
 * con la condicion de que la empresa si este registrada en la API
 */
function visibleInformation(res){
    if(res.data["Meta Data"] != null){
        document.getElementById('intra').style.visibility = "visible";
        document.getElementById('daily').style.visibility = "visible";
        document.getElementById('week').style.visibility = "visible";
        document.getElementById('month').style.visibility = "visible";
    }
    else{
        invisibleInformation()
    }
    
}
