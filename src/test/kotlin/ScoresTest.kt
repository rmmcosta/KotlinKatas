import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class ScoresTest {

    @Test
    fun shouldAddPlayer() {
        val addResult = Scores.addPlayer(101, "Ricardo Costa 1")
        assertTrue(addResult)
    }

    @Test
    fun shouldFail2AddPlayerIfIdExists() {
        val addResult1 = Scores.addPlayer(201, "Ricardo Costa 2")
        assertTrue(addResult1)
        val addResult2 = Scores.addPlayer(201, "Ana Ramos 2")
        assertFalse(addResult2)
    }

    @Test
    fun shouldFail2AddPlayerIfNameExists() {
        val addResult1 = Scores.addPlayer(301, "Ricardo Costa 3")
        assertTrue(addResult1)
        val addResult2 = Scores.addPlayer(302, "Ricardo Costa 3")
        assertFalse(addResult2)
    }

    @Test
    fun shouldAddScoreIfPlayerFound() {
        assertNull(Scores.getPlayerByNameOrId("401"))
        assertTrue(Scores.addPlayer(401, "Ricardo Costa 4"))
        assertTrue(Scores.getPlayerScoresById(401).isEmpty())
        assertTrue(Scores.addScore(401, 1, 35.0))
        assertTrue(Scores.getPlayersScores().isNotEmpty())
        assertTrue(Scores.getPlayerScoresById(401).isNotEmpty())
    }

    @Test
    fun shouldNotAddScoreIfPlayerNotFound() {
        assertNull(Scores.getPlayerByNameOrId("501"))
        assertTrue(Scores.addPlayer(501, "Ricardo Costa 5"))
        assertTrue(Scores.getPlayerScoresById(501).isEmpty())
        assertNull(Scores.getPlayerByNameOrId("111"))
        assertFalse(Scores.addScore(111, 1, 35.0))
        assertTrue(Scores.getPlayerScoresById(501).isEmpty())
    }

    @Test
    fun shouldGetBestScoreIfHasOnlyOneScore() {
        assertNull(Scores.getPlayerByNameOrId("601"))
        assertTrue(Scores.addPlayer(601, "Ricardo Costa 6"))
        assertTrue(Scores.getPlayerScoresById(601).isEmpty())
        assertTrue(Scores.addScore(601, 1, 35.0))
        assertEquals(35.0, Scores.getPersonalBest(601, 1)?.score)
    }

    @Test
    fun shouldGetBestScoreIfRegisteredBetterScore() {
        assertNull(Scores.getPlayerByNameOrId("701"))
        assertTrue(Scores.addPlayer(701, "Ricardo Costa 7"))
        assertTrue(Scores.getPlayerScoresById(701).isEmpty())
        assertTrue(Scores.addScore(701, 1, 35.0))
        assertEquals(35.0, Scores.getPersonalBest(701, 1)?.score)
        assertTrue(Scores.addScore(701, 1, 45.0))
        assertEquals(2, Scores.getPlayerScoresById(701).size)
        assertEquals(45.0, Scores.getPersonalBest(701, 1)?.score)
    }

    @Test
    fun shouldGetBestScoreIfRegisteredWorseScore() {
        assertNull(Scores.getPlayerByNameOrId("801"))
        assertTrue(Scores.addPlayer(801, "Ricardo Costa 8"))
        assertTrue(Scores.getPlayerScoresById(801).isEmpty())
        assertTrue(Scores.addScore(801, 1, 35.0))
        assertEquals(35.0, Scores.getPersonalBest(801, 1)?.score)
        assertTrue(Scores.addScore(801, 1, 25.0))
        assertEquals(35.0, Scores.getPersonalBest(801, 1)?.score)
    }

    @Test
    fun shouldGetCorrectBestScoreForTheLevelIfHasOtherBestScoresForOtherLevels() {
        assertNull(Scores.getPlayerByNameOrId("901"))
        assertTrue(Scores.addPlayer(901, "Ricardo Costa 9"))
        assertTrue(Scores.getPlayerScoresById(901).isEmpty())
        assertTrue(Scores.addScore(901, 1, 35.0))
        assertEquals(35.0, Scores.getPersonalBest(901, 1)?.score)
        assertTrue(Scores.addScore(901, 2, 25.0))
        assertEquals(35.0, Scores.getPersonalBest(901, 1)?.score)
        assertEquals(25.0, Scores.getPersonalBest(901, 2)?.score)
    }

    @Test
    fun shouldGetTheCorrectLevelWinner() {
        assertNull(Scores.getPlayerByNameOrId("1001"))
        assertNull(Scores.getPlayerByNameOrId("1002"))
        assertTrue(Scores.addPlayer(1001, "Ricardo Costa 10"))
        assertTrue(Scores.addPlayer(1002, "Ana Ramos 10"))
        assertTrue(Scores.getPlayerScoresById(1001).isEmpty())
        assertTrue(Scores.getPlayerScoresById(1002).isEmpty())
        assertTrue(Scores.addScore(1001, 1, 35.0))
        assertTrue(Scores.addScore(1002, 1, 75.5))
        assertEquals(35.0, Scores.getPersonalBest(1001, 1)?.score)
        assertEquals(75.5, Scores.getPersonalBest(1002, 1)?.score)
        val winner = Scores.getLevelWinner(1)
        assertEquals(1002, winner?.first?.id)
        assertEquals(75.5, winner?.second)
    }
}