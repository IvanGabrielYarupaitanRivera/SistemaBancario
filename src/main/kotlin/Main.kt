fun main() {
    println("BIENVENIDO AL SISTEMA DE TU CUENTA BANCARIA")
    println("¿Qué tipo de cuenta te gustaría crear?")
    println("1. Cuenta de débito")
    println("2. Cuenta de crédito")
    println("3. Cuenta corriente")

    var tipoDeCuenta = ""

    while (tipoDeCuenta == "") {
        println("Escoge una opción (1, 2, 3)")
        tipoDeCuenta = readln()
        println("La opción que elegiste es ${tipoDeCuenta}.")

        tipoDeCuenta = when (tipoDeCuenta) {
            "1" -> "Cuenta de débito"
            "2" -> "Cuenta de crédito"
            "3" -> "Cuenta corriente"
            else -> ""
        }
    }

    println("La cuenta creada es una = $tipoDeCuenta")

    //Iniciando la cuenta con una cantidad aleatoria de dinero para la cuenta
    var balanceDeCuenta = (0..1000).random()
    if (tipoDeCuenta=="Cuenta de crédito"){
        balanceDeCuenta = -balanceDeCuenta
        println("La deuda de su ${tipoDeCuenta.lowercase()} es de $balanceDeCuenta soles")
    }
    else{
        println("El balance de su ${tipoDeCuenta.lowercase()} es de $balanceDeCuenta soles")
    }

    //Función de para retirar dinero (Crédito y Corriente)
    fun retiroCuenta(monto: Int): Int {
        balanceDeCuenta -= monto
        println("Retiraste exitosamente $monto soles de tu ${tipoDeCuenta.lowercase()}.")
        println("Ahora tienes $balanceDeCuenta soles.")
        return balanceDeCuenta
    }

    //Función para retirar dinero (Débito)
    fun retiroCuentaDebito(monto: Int): Int{
        if (balanceDeCuenta==0){
            println("No puedes retirar porque no tienes dinero")
            return balanceDeCuenta
        }
        else if (monto>balanceDeCuenta){
            println("No puedes retirar más dinero del que tienes")
            return balanceDeCuenta
        }
        else{
            retiroCuenta(monto)
            return balanceDeCuenta
        }
    }

    //Función para depositar dinero (Débito y Corriente)
    fun depositoCuenta (monto:Int):Int{
        balanceDeCuenta+=monto
        println("Depositaste exitosamente $monto soles de tu ${tipoDeCuenta.lowercase()}.")
        println("Ahora tienes $balanceDeCuenta soles.")
        return balanceDeCuenta
    }

    //Función para saldar deudas (Crédito)
    fun depositoCuentaCredito (monto:Int):Int{
        if (balanceDeCuenta==0){
            println("Todas tus deudas están saldadas")
            println("No necesitas depositar más")
            return balanceDeCuenta
        }
        else if (monto>-balanceDeCuenta){
            println("Tu deuda es más pequeña que la cantidad que quieres depositar")
            return balanceDeCuenta
        }
        else if (monto==-balanceDeCuenta){
            println("Acabas de saldar tu deuda, felicitaciones")
            depositoCuenta(monto)
            return balanceDeCuenta
        }
        else{
            println("Reduciste tu deuda, felicitaciones")
            depositoCuenta(monto)
            return balanceDeCuenta
        }
    }

    //Creando la función de transferencia
    //Engloba retiro y depósito
    fun transferencia(modo:String, dinero:Int){
        when (modo){
            "retiro" -> {
                if (tipoDeCuenta == "Cuenta de débito") {
                    retiroCuentaDebito(dinero)
                }
                else {
                    retiroCuenta(dinero)
                }
            }
            "deposito" -> {
                if (tipoDeCuenta=="Cuenta de crédito"){
                    depositoCuentaCredito(dinero)
                }
                else{
                    depositoCuenta(dinero)
                }
            }
            else -> return
        }
    }

    var elSistemaEstaAbierto = true
    var opcion = ""

    while (elSistemaEstaAbierto == true){
        println("¿Qué te gustaría hacer?")
        println("1. Revisar el balance de mi cuenta")
        println("2. Retirar dinero")
        println("3. Depositar dinero")
        println("4. Cerrar el sistema")
        println("Escoge una opción (1, 2, 3, 4)")

        opcion = readln()
        println("La opción que elegiste es el número ${opcion}.")

        when (opcion){
            "1" -> println("Ahora tienes $balanceDeCuenta soles.")
            "2" -> {
                var dinero: Int
                println("¿Cuánto dinero te gustaría retirar?")
                try {
                    dinero = readln().toInt()
                    transferencia("retiro", dinero)
                }catch (e:Throwable)
                {
                    println("Ingresa un número por favor")
                }

            }
            "3" -> {
                var dinero: Int
                println("¿Cuánto dinero te gustaría depositar?")
                try {
                    dinero = readln().toInt()
                    transferencia("deposito", dinero)
                }catch (e:Throwable)
                {
                    println("Ingresa un número por favor")
                }
            }
            "4" -> {
                elSistemaEstaAbierto = false
                println("Sistema cerrado")
            }
            else -> continue
        }
    }
}