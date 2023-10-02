fun main()
{
    println("BIENVENIDO AL SISTEMA DE TU CUENTA BANCARIA")
    println("¿Qué tipo de cuenta te gustaría crear?")
    println("1. Cuenta de débito")
    println("2. Cuenta de crédito")
    println("3. Cuenta corriente")

    var cuentaTipo = ""
    var opcionUsuario = 0
    while (cuentaTipo == "") {
        println("Escoge una opción (1, 2, 3)")
        opcionUsuario = readln().toInt()
        println("La opción que elegiste es ${opcionUsuario}.")

        when (opcionUsuario) {
            1 -> cuentaTipo = "debit"
            2 -> cuentaTipo = "credit"
            3 -> cuentaTipo = "checking"
            else -> continue
        }
    }
}