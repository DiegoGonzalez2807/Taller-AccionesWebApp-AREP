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
    document.getElementById("actionInfo").style.visibility = "hidden"
    document.getElementById("allInfo").style.visibility = "hidden"
    var URL_API = "http://localhost:4567/search/"+document.getElementById("empresa").value;
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

/**
 * Funcion generada para retornar valores de redireccion
 * a los botones que se acaban de hacer visibles en la 
 * pagina HTML de acuerdo con la cantidad de tiempo que
 * digan los botones
 * @param {String} date 
 */
function Loading_Per_Date(date){
    var URL_DATE = "http://localhost:4567/search/"+document.getElementById("empresa").value+"/"+date;
    axios.get(URL_DATE)
        .then(function(res){
            printingInformation(res.data["Meta Data"]);
            printingImportantInfo(res.data)
        })
        .catch(function (error) {
            console.log(error)
        })
}

/**
 * Funcion encargada de imprimir en HTML la informacion general
 * del archivo JSON que nos trajo la API 
 * @param {JSON list} list 
 */
function printingInformation(list){
    var info = document.getElementById("actionInfo");
    info.style.visibility = "visible";
    //Insercion a  HTML de la info de la empresa
    info.innerHTML ="<h2>Información acerca de la consulta</h2>"+"<br>"+"Símbolo: "+JSON.stringify(list["2. Symbol"]+"<br>"
    +"Ultima Actualizacion: "+JSON.stringify(list["3. Last Refreshed"])+"<br>"
    +"Zona horaria: "+JSON.stringify(list["4. Time Zone"]));
    
}


