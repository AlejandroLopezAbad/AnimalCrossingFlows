import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.take
import models.TipoColor

//HAY QUE PONER EL RUNBLOCKING
fun main(): Unit = runBlocking {
    //todo Quiero el while de que sigan escribiendo palabras todo el rato y solo si le interesa el color que la cambien
    //es eso posible , dentro del filter le puedo meter mas de un color tipo que todo el mundo mire las palabras negras y luego un color aparte
    palabrasModaStateFlow()
}

suspend fun palabrasModaStateFlow() {

    println()
    println("PalabrasModa StateFlow ...")

    val productor = PalabraModaStateFlow("Alexitto")

    val alexittoProductor = CoroutineScope(Dispatchers.IO).launch {

        productor.emitirPalabrasModa()
    }


    val teco = CoroutineScope(Dispatchers.IO).launch {
        delay((1000..3000).random().toLong())

        println("TECO-> Estoy listo para recibir palabras de moda \uD83D\uDE05")
        productor.directorModa
            .filter { palabramoda ->
                palabramoda.color == TipoColor.NEGRO ||
                palabramoda.color == TipoColor.ROJO

            }.take(3)
            .collect { palabramoda ->
                delay((200..500).random().toLong())
                if (palabramoda.color == TipoColor.NEGRO) {
                    println("TECO -> La palabra de moda es ${palabramoda.palabra} y el color es ${palabramoda.color} ⚫ ")
                }else
                if (palabramoda.color == TipoColor.ROJO) {
                    println("TECO-> La palabra de moda es ${palabramoda.palabra} y el color es ${palabramoda.color} \uD83D\uDD34")
                }
            }


    }

    val xCard = CoroutineScope(Dispatchers.IO).launch {
        delay((1000..3000).random().toLong())

        println("xCard-> Estoy listo para recibir palabras de moda \uD83D\uDE05")
        productor.directorModa
            .filter { palabramoda ->
                //palabramoda.color == TipoColor.NEGRO
                palabramoda.color == TipoColor.ROJO

            }.take(3)
            .collect { palabramoda ->
                delay((200..500).random().toLong())
              /*  if (palabramoda.color == TipoColor.NEGRO) {
                    println("TECO -> La palabra de moda es ${palabramoda.palabra} y el color es ${palabramoda.color} ⚫ ")
                }*/
                if (palabramoda.color == TipoColor.ROJO) {
                    println("XCARD ->La palabra de moda es ${palabramoda.palabra} y el color es ${palabramoda.color} \uD83D\uDD34")
                }
            }
    }


    val joseluis = CoroutineScope(Dispatchers.IO).launch {
        delay((1000..3000).random().toLong())

        println("Jose Luis-> Estoy listo para recibir palabras de moda \uD83D\uDE05")
        productor.directorModa
            .filter { palabramoda ->
                palabramoda.color == TipoColor.VERDE
            }.take(3)
            .collect { palabramoda ->
                delay((200..500).random().toLong())
                if (palabramoda.color == TipoColor.VERDE) {
                    println("Jose Luis- ->La palabra de moda es ${palabramoda.palabra} y el color es ${palabramoda.color} \uD83D\uDFE2")
                }
            }
    }
    teco.join()
    xCard.join()
    joseluis.join()
    alexittoProductor.join()
}
