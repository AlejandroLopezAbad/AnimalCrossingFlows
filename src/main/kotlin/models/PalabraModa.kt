package models

data class PalabraModa(
    val id :Int,
    val palabra:String,
    val color:TipoColor

) {


}
enum class TipoColor(val color:String){
    ROJO("rojo"),
    AZUL("azul"),
    VERDE("verde"),
    NEGRO("negro"),
    NARANJA("naranja")

}