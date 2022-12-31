
var input = document.getElementById("contrasena2")
input.addEventListener('keyup', validarContrasena);
input.addEventListener('blur', validarContrasena);

function validarContrasena(){

    let inputContrasena1 = document.getElementById("contrasena")
    let inputContrasena2 = document.getElementById("contrasena2")
    let parrafoError = document.getElementById("aparecerError")

    if(inputContrasena1.value != inputContrasena2.value && inputContrasena2.value != ""){
       parrafoError.style.display = "inline-block";
       return true


    }else{
       parrafoError.style.display = "none";
       return false
    }
}
function borrarInputs(i){
for (let j = i ; j < 10 ; j++) {
    document.getElementById("usuario"+j).value = "";
    document.getElementById("mail"+j).value = "";
    }
}
function asignarAtributos(i){
  document.getElementById(1).style.display = "inline-block";
  for (let j = 2 ; j < i+1 ; j++) {
    document.getElementById(j).style.display = "inline-block";
    document.getElementById("usuario"+j).required = 'true';
    document.getElementById("mail"+j).required = 'true';
    }
}
function iniciarValidacion(){
    document.getElementById("1").style.display = "none";
    for (let j = 2 ; j < 10 ; j++) {
        document.getElementById(j).style.display = "none";
        document.getElementById("usuario"+j).removeAttribute("required");
        document.getElementById("mail"+j).removeAttribute("required");

        }
}
function accederMiembros(){
   var valorCuadro = document.getElementById("validationTooltip04").value;


   iniciarValidacion();



   switch (valorCuadro) {
     case "one":
       document.getElementById("1").style.display = "inline-block";
       borrarInputs(2);

       break;
     case "two":
       asignarAtributos(2);
       borrarInputs(3);
       break;
     case "three":
       asignarAtributos(3);
       borrarInputs(4);

       break;
     case "four":
       asignarAtributos(4);
       borrarInputs(5);

       break;
     case "five":
       asignarAtributos(5);
       borrarInputs(6);

       break;
     case "six":
       asignarAtributos(6);
       borrarInputs(7);

       break;
     case "seven":
       asignarAtributos(7);
       borrarInputs(8);

       break;
     case "eight":
       asignarAtributos(8);
       borrarInputs(9);

       break;
     case "nine":
       asignarAtributos(9);
       break;

   }
   var valorInputUs = document.getElementById("usuario").value
   var valorInputEm = document.getElementById("mail").value

   document.getElementById("us-one").value = valorInputUs
   document.getElementById("em-one").value = valorInputEm

 }
(() => {
  'use strict'

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {

        event.preventDefault()
        event.stopPropagation()

      }
      if(validarContrasena()){
        event.preventDefault()
        event.stopPropagation()
      }


      form.classList.add('was-validated')
    }, false)
  })
})()