import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import models.PalabraModa
import models.TipoColor
import kotlin.random.Random


private const val MAX_PALABRASMODA = 50

class PalabraModaStateFlow(val nombre: String) {


    private val _directorModa: MutableStateFlow<PalabraModa> = MutableStateFlow(PalabraModa(0, "TECO", TipoColor.NEGRO))
    val directorModa: StateFlow<PalabraModa> = _directorModa.asStateFlow()

    init {
        println("\uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBC $nombre ha llegado!")
        println("\uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBC $nombre -> Estoy diciendo palabras que estan de moda")
    }

    suspend fun emitirPalabrasModa() {

        repeat(MAX_PALABRASMODA) { i ->
            delay((300..700).random().toLong())
            _directorModa.value = PalabraModa(
                id = (i + 1),
                palabra = generarPalabraRandom((5..20).random()),
                color = TipoColor.values().random()
            )
        }
        println("\uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBC $nombre -> He terminado de decir palabras de moda")

    }


    fun generarPalabraRandom(longitud: Int): String {
        val letras = ('a'..'z') + ('A'..'Z') // Agrega las letras que deseas incluir
        val palabra = StringBuilder()

        repeat(longitud) {
            val indice = Random.nextInt(0, letras.size)
            palabra.append(letras[indice])
        }

        return palabra.toString()
    }


}