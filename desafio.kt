// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

num class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Usuario (var nome: String, val email: String)

data class ConteudoEducacional(
    var nome: String, 
    val duracao: Int = 60,
    val nivel: Nivel = Nivel.BASICO 
)

data class Formacao(
    var nome: String,
    val nivel: Nivel,
    var conteudos: List<ConteudoEducacional>) {
    
    val inscritos = mutableListOf<Usuario>()
    
    val duracaoTotal: Int
        get()= conteudos.sumOf{it.duracao}  
    
    fun matricular(usuario: Usuario) {
        if(inscritos.contains(usuario)){
            println("X ${usuario.nome} já está matriculado(a) na formação $nome.")
            return
        }
        
        inscritos.add(usuario)
        println("${usuario.nome} matriculado(a) com sucesso na formação $nome!")
    }
    
    fun matricularVarios(vararg novosUsuarios: Usuario){
        novosUsuarios.forEach{ usuario -> 
            matricular(usuario)
        }
    }
}
            
fun main() {
    val kotlinIntro = ConteudoEducacional("Introdução ao Kotlin", 60, Nivel.BASICO)
    val kotlinOO = ConteudoEducacional("Orientação a objetos com Kotlin", 100, Nivel.INTERMEDIARIO)
    val coroutines = ConteudoEducacional("Coroutines Avançadas", 250, Nivel.AVANCADO)
    
    val formacaoBackend = Formacao(
        nome = "Kotlin Back-end Developer",
        nivel = Nivel.AVANCADO,
        conteudos = listOf(kotlinIntro, kotlinOO, coroutines)
    )
    
    val aluno1 = Usuario("Jeferson", "jeferson@dio.me")
    val aluno2 = Usuario("Marcos", "marcos@dio.me")
    val aluno3 = Usuario("Daniela", "daniela@dio.me")
    
    println("---Teste de matricula em: ${formacaoBackend.nome}---")
    
    formacaoBackend.matricular(aluno1)
    formacaoBackend.matricular(aluno2)
    formacaoBackend.matricular(aluno1) // Aluno já matriculado
    
    val outrosAlunos = listOf(aluno3, Usuario("Raquel", "raquel@dio.me")).toTypedArray()
    formacaoBackend.matricularVarios(*outrosAlunos)
    
    println("\n--- Resumo da Formação ---")
    println("Formação: ${formacaoBackend.nome} (${formacaoBackend.nivel})")
    println("Duração Estimada: ${formacaoBackend.duracaoTotal} minutos")
    println("Total de Módulos: ${formacaoBackend.conteudos.size}") 
    
    println("\n**Alunos Matriculados (${formacaoBackend.inscritos.size}):**")
    formacaoBackend.inscritos.forEachIndexed { index, aluno ->
        println("${index + 1}. ${aluno.nome} - Email: ${aluno.email}")
    }
}

