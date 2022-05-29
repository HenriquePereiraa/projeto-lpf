import kotlinx.browser.*
import org.w3c.dom.*

var chances = 5
fun palavras() : String {

    val palavras : Array<String> = arrayOf("mouse", "sagaz", "mexer", "termo","senso","nobre","algoz","afeto", "ponto",
    "plena","sutil","vigor","fazer","audaz","sanar","assim","inato","cerne","ideia","fosse", "round", "abrir", "hiato",
    "desde","poder","moral","torpe","muito","honra","justo","gozar", "anexo","etnia", "sobre", "sonho", "tange", "lapso", 
    "expor", "haver", "amigo", "carma", "velho", "sonsa", "ideal", "claro", "doido", "horda", "inata", "capaz", "xeque")
    val random = Random.nextInt(until = palavras.size)
    return palavras[random]

}

val palavraSecreta = palavras()

fun ehIgual(s: String): Boolean {
	return s.equals(palavraSecreta)
}

fun temLetrasEmComum(s: String): List<String> {

	return s.mapIndexed {i, x -> 
		if(x == palavraSecreta[i]) {
			//na ordem certa
			"<span class=\"green\">" + x + "</span>"
		} else if(palavraSecreta.contains(x)) {
			//fora de ordem
			"<span class=\"yellow\">" + x + "</span>"
		} else { 
			"<span>" + x + "</span>"
		}
	}
}

@JsName("jogar")
fun jogar() {

	val area = document.getElementById("palavras")
	if(chances == 0) {
		area!!.innerHTML +=  "<h1> Você perdeu!</h1>"
	} else {
		val entrada = document.getElementById("entrada") as HTMLInputElement
		val palavra = entrada.value

		if(ehIgual(palavra)) {
			area!!.innerHTML +=  """
				<h1> Você ganhou! </h1>
				<form method="POST" action="salvar_pontuacao">
				Nome: <input name="nome_usuario" type="text">
				<input name ="pontos" type="hidden" value="${chances*100}"> 
				<input type="submit" value="Enviar">
				</form>
			"""
		} else {
			val letrasEmComum = temLetrasEmComum(palavra)
			val novaPalavra = letrasEmComum.toString().replace(",", " ")
			area!!.innerHTML +=  "<center><h1> " + novaPalavra.uppercase() + "</h1></center>"
		}

		entrada.value = ""
		chances--
	}
}

fun main() {

}
