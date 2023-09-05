import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt
import kotlin.random.Random

data class Score(val playerId: Int, val level: Int, val score: Double)

object Scores {
    private val players = mutableListOf<Player>()
    private val scores = mutableListOf<Score>()
    private val playersPersonalBest = mutableListOf<PlayerPersonalBest>()

    fun addPlayer(playerId: Int, playerName: String): Boolean {
        val hasPlayerSameIdOrName: Boolean = players.any { it.id == playerId || it.name == playerName }
        return if (!hasPlayerSameIdOrName) {
            players += Player(playerId, playerName)
            true
        } else {
            false
        }
    }

    fun addScore(playerId: Int, level: Int, score: Double) {
        //add score and check if is a personal best
        val previousScore: Double = getPersonalBest(playerId, level)?.score ?: 0.0
        scores += Score(playerId, level, score)
        if (score > previousScore) {
            addPersonalBest(playerId, level, score)
        }
    }

    fun getPersonalBest(playerId: Int, level: Int): PlayerPersonalBest? =
        playersPersonalBest.filter { it.playerId == playerId && it.level == level }.getOrNull(0)

    fun addPersonalBest(playerId: Int, level: Int, bestScore: Double) {
        val currentBest: PlayerPersonalBest? =
            playersPersonalBest.filter { it.playerId == playerId && it.level == level }.getOrNull(0)
        val currentBestScore: Double = currentBest?.score ?: 0.0
        if (bestScore > currentBestScore) {
            playersPersonalBest += PlayerPersonalBest(playerId, level, bestScore)
        }
    }

    fun getLevelWinner(level: Int): Pair<Player?, Double>? {
        if (!playersPersonalBest.any { it.level == level }) {
            return null
        }
        val pb = playersPersonalBest.filter { it.level == level }.maxBy { it.score }
        return players.filter { it.id == pb.playerId }.getOrNull(0) to pb.score
    }

    fun viewPlayers() {
        players.forEach { println("${it.id} - ${it.name}") }
    }

    fun viewPlayersScores() {
        val player: Player = getPlayerFromInput()!!
        println("${player.name} - Personal Best Scores:")
        playersPersonalBest.filter { it.playerId == player.id }.forEach { println(it) }
        println("${player.name} - Scores History")
        scores.filter { it.playerId == player.id }.forEach { println(it) }
    }

    fun getPlayerByNameOrId(playerNameOrId: String): Player? {
        val isPlayerId = playerNameOrId.toIntOrNull() != null
        return players.filter { if (isPlayerId) it.id == playerNameOrId.toInt() else it.name == playerNameOrId }
            .getOrNull(0)
    }

    fun getAllPersonalBests() = playersPersonalBest

}

data class Player(val id: Int, val name: String)

data class PlayerPersonalBest(val playerId: Int, val level: Int, var score: Double)

enum class Option(val description: String) {
    E("Exit"),
    V("View Player Score"),
    I("Insert Score"),
    W("Check Winner"),
    P("View Players"),
    A("View all Personal Bests"),
    C("Create Player"),
}

fun main() {
    bootstrapData()
    var option: Option? = null
    while (option !== Option.E) {
        showOptions()
        print("Please choose your option: ")
        option = getAndValidateOption()
        when (option) {
            Option.E -> println("Exiting application")
            Option.V -> viewPlayerScores()
            Option.I -> insertScore()
            Option.W -> checkWinner()
            Option.P -> viewPlayers()
            Option.A -> viewAllPersonalBests()
            Option.C -> createPlayer()
            else -> println("Please choose a valid option")
        }
    }
}

fun viewAllPersonalBests() {
    Scores.getAllPersonalBests().forEach { println(it) }
}

fun bootstrapData() {
    for (i in 0..100) {
        Scores.addPlayer(i, "Player $i")
        for (j in 1..10) {
            var maxScore = 0.0
            for (k in 1..5) {
                val localScore = generateRandomScore()
                if (localScore > maxScore) {
                    maxScore = localScore
                }
                Scores.addScore(i, j, localScore)
            }
            Scores.addPersonalBest(i, j, maxScore)
        }
    }
}

fun generateRandomScore(): Double {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.DOWN
    return df.format((Random.nextDouble() + 1) * 10).toDouble()
}

fun checkWinner() {
    var level: Int? = null
    while (level == null) {
        print("which level: ")
        level = readln().toIntOrNull()
    }
    val winnerAndScore = Scores.getLevelWinner(level)
    if (winnerAndScore == null) {
        println("No winner found for the level $level")
    } else {
        val (winner, score) = winnerAndScore
        if (winner != null) {
            println("$winner won the level with $score")
        } else {
            println("No one has won this level yet")

        }
    }
}

fun viewPlayers() {
    Scores.viewPlayers()
}

fun createPlayer() {
    print("Please enter the player id: ")
    val playerId: Int? = readln().toIntOrNull()
    if (playerId == null) {
        println("Invalid id. The Id must be a number")
        return
    }
    print("Please enter the player name: ")
    val playerName = readln()
    val result = Scores.addPlayer(playerId, playerName)
    if (result) {
        println("Player with $playerId and $playerName created with success!")
    } else {
        println("Player with $playerId and $playerName not created. Player already exists!")
    }
}

fun insertScore() {
    val player = requireNotNull(getPlayerFromInput()) {
        println("Player not found!")
        return
    }

    val level = requireNotNull(getLevelFromInput()) {
        println("Invalid Level")
        return
    }

    val score = requireNotNull(getScoreFromInput()) {
        println("Invalid Score")
        return
    }

    Scores.addScore(player.id, level, score)
}

fun getScoreFromInput(): Double? {
    print("Please enter the score: ")
    return readln().toDoubleOrNull()
}

fun getLevelFromInput(): Int? {
    print("Please enter the level: ")
    return readln().toIntOrNull()
}

private fun getPlayerFromInput(): Player? {
    print("Please input the player name or id: ")
    val player = readln()
    return Scores.getPlayerByNameOrId(player)
}

fun viewPlayerScores() {
    Scores.viewPlayersScores()
}

fun getAndValidateOption(): Option? {
    val option = readln()
    return try {
        Option.valueOf(option)
    } catch (e: Exception) {
        null
    }
}

fun showOptions() {
    Option.values().forEach { println("${it.name} - ${it.description}") }
}
