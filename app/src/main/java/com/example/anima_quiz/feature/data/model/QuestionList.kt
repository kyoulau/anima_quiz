package com.example.anima_quiz.feature.data.model
import com.example.anima_quiz.R
class QuestionList {
    private val questions = listOf(
        Question(
            questionText = "Qual destes personagens da Sanrio é conhecido por sua paixão por cozinhar e por ter um panda de estimação?",
            options = listOf("Pompompurin", "My Melody","Kuromi","Cinnamoroll","Hello Kitty"),
            correctAnswerIndex = 0,
            imageUrl = R.drawable.hk3,
            tips = listOf("Personagem amarelo", "Amigo da Hello Kitty")
        ),
        Question(
            questionText = " Qual personagem adora andar de skate?",
            options = listOf("Chococat", "Badtz-Maru","Hello Kitty","Cinnamoroll","Pompompurin"),
            correctAnswerIndex = 1,
            imageUrl = R.drawable.hk2,
            tips = listOf("É um pinguim muito radical", "Amigo da Hello Kitty")
        ),
        Question(
            questionText = "Qual o nome da cidade onde vivem a maioria dos personagens da Sanrio?",
            options = listOf("Tokyo", "Kyoto","Osaka","Shirokuma","Londres"),
            correctAnswerIndex = 3,
            imageUrl = R.drawable.hk2,
            tips = listOf("É um pinguim muito radical", "Amigo da Hello Kitty")
        ),
        Question(
            questionText = "Qual personagem da Sanrio é conhecido por sua personalidade rebelde e por ter um coração macio por dentro?",
            options = listOf("Kuromi", "Cinnamoroll","Chococat", "Pompompurin", " Bad Badtz Maru"),
            correctAnswerIndex = 0,
            imageUrl = R.drawable.hk1,
            tips = listOf("Coelhinha fofa que usa uma touca preta","Faz aniversário na Halloween")
        ),
        Question(
            imageUrl = R.drawable.jjk1,
            questionText = "Which Jujutsu Kaisen character is known for speaking very little?",
            options = listOf("Panda", "Toge Inumaki", "Maki Zenin", "Yuta Okotsu"),
            correctAnswerIndex = 1,
            tips = listOf("awd")
        ),
        Question(
            imageUrl = R.drawable.jjk2,
            questionText = "Qual desses personagens é conhecido por falar pouco?",
            options = listOf("Nobara Kugisaki", "Megumi Fushiguro", "Satoro Gojou", "Yuji Itadori"),
            correctAnswerIndex = 0,
            tips = listOf("awd")

        ),
        Question(
            imageUrl = R.drawable.jjk3,
            questionText = "Quanto custa a camiseta do Gojo?",
            options = listOf("10,000 yen", "57,000 yen", "250,000 yen", "499,000 yen"),
            correctAnswerIndex = 2,
            tips = listOf("awd")
        ),
        Question(
            imageUrl = R.drawable.bsk1,
            questionText = "Qual destes personagens não aparece no anime da década de 80 mas aparece nos animes após anos 2000 e nos mangás?",
            options = listOf("Guts (com armadura)", "Cascka (manto verde)", "Puck (fada homem)", "Farnese (loira)", "Serpico (loiro)"),
            correctAnswerIndex = 2,
            tips = listOf("awd")
        ),
        Question(
            imageUrl = R.drawable.bsk2,
            questionText = "Qual o nome da espada que Guts usa?",
            options = listOf("Matadora de Dragões", "Espada Grande", "Mortal Blade", "Lâmina do Caos", "Excalibur"),
            correctAnswerIndex = 0,
            tips = listOf("awd")
        ),
        Question(
            imageUrl = R.drawable.bsk3,
            questionText = "Quais efeitos colaterais a armadura de Guts (a armadura Berserk) pode causar quando a usa?",
            options = listOf("Fadiga", "Você perde algum membro do corpo", "Nenhum, a armadura não causa nada", "A armadura consome seu corpo e mente"),
            correctAnswerIndex = 3,
            tips = listOf("awd")
        ),
        Question(
            imageUrl = R.drawable.jojo1,
            questionText = "Qual objeto Dio usa para jogar em cima de Jotaro?",
            options = listOf("Uma lixeira", "Um rolo compressor.", "Um pedaço de concreto", "Um sofá"),
            correctAnswerIndex = 1,
            tips = listOf("awd")
        ),
        Question(
            imageUrl = R.drawable.jojo2,
            questionText = "Qual o Stand de Yukako Yamagishi?",
            options = listOf("Lovers", "The Hand", "Love Deluxe", "Echoes"),
            correctAnswerIndex = 2,
            tips = listOf("awd")
        )
    )
    fun loadQuestion(): List<Question> {
        return questions.shuffled()
    }
}