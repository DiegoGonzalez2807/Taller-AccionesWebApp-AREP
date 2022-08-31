/**
 * author: Diego Gonzalez
 */

var APIRedirect = "";

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
 * Se deja por defecto la busqueda en polygon para esta
 */
function loadValues(){
    document.getElementById("allInfo").style.visibility = "hidden"
    //var URL_API = "https://serene-bastion-37080.herokuapp.com/search/"+document.getElementById("empresa").value;
    var URL_API = "http://localhost:4567/search/"+APIRedirect+"/"+document.getElementById("empresa").value;

    axios.get(URL_API)
        .then(function(res){
            visibleInformation(res)
        })
        .catch(function (error) {
            console.log(error)
        })
}

/**
 * Funcion encargada de ingresar en la pagina HTML la informacion
 * JSON que se trajo de la API para que el usuario la pueda revisar
 * @param {Stringfy(JSON)} info 
 */
function printingImportantInfo(info){
    div = document.getElementById("allInfo");
    div.style.visibility = "visible"
    div.innerHTML = JSON.stringify(info,null,2);
}

/**
 * Funcion encargada de poner visibles los botones en la pagina 
 * con la condicion de que la empresa si este registrada en la API
 */
function visibleInformation(res){
    if(res.data["Meta Data"]!= null || res.data["queryCount"] != 0){
        document.getElementById('intra').style.visibility = "visible";
        document.getElementById('daily').style.visibility = "visible";
        document.getElementById('week').style.visibility = "visible";
        document.getElementById('month').style.visibility = "visible";
    }
    else{
        invisibleInformation()
    }
}

/**
 * Funcion generada para cambiar el valor de la variable que contiene
 * hacia que API se va a pedir los valores de la empresa y sus acciones
 * @param {String} data 
 */
function setValueAPI(data){
    this.APIRedirect = data;
    loadValues();
}

/**
 * Funcion generada para retornar valores de redireccion
 * a los botones que se acaban de hacer visibles en la 
 * pagina HTML de acuerdo con la cantidad de tiempo que
 * digan los botones
 * @param {String} date 
 */
function Loading_Per_Date(date){
    //var URL_DATE = "https://serene-bastion-37080.herokuapp.com/search/"+document.getElementById("empresa").value+"/"+date;
    var URL_DATE ="http://localhost:4567/search/"+APIRedirect+"/"+document.getElementById("empresa").value+"/"+date;
    axios.get(URL_DATE)
        .then(function(res){
            printingImportantInfo(res.data)
        })
        .catch(function (error) {
            console.log(error)
        })
}


