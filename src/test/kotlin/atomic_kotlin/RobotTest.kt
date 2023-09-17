package atomic_kotlin

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RobotTest {
    private val robot = Robot()

    @AfterEach
    fun tearDown() {
        robot.reset()
    }

    @Test
    fun shouldGoLeftWhenStepsLessThanMin() {
        robot.right(Robot.MAX_X / 2)
        robot.left(Robot.MAX_X / 4)
        val expectedPosition = "(${Robot.MAX_X / 4}, 0)"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldGoRightWhenStepsLessThanMax() {
        robot.right(Robot.MAX_X / 6)
        val expectedPosition = "(${Robot.MAX_X / 6}, 0)"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldGoUpWhenStepsLessThanMin() {
        robot.down(Robot.MAX_Y / 2)
        robot.up(Robot.MAX_Y / 4)
        val expectedPosition = "(0, ${Robot.MAX_Y / 4})"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldGoDownWhenStepsLessThanMax() {
        robot.down(Robot.MAX_Y / 6)
        val expectedPosition = "(0, ${Robot.MAX_Y / 6})"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldReturnZeroWhenLeftMaxX() {
        robot.left(Robot.MAX_X)
        val expectedPosition = "(0, 0)"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldReturnZeroWhenRightMaxX() {
        robot.right(Robot.MAX_X)
        val expectedPosition = "(0, 0)"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldReturnZeroWhenUpMaxY() {
        robot.up(Robot.MAX_Y)
        val expectedPosition = "(0, 0)"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldReturnZeroWhenDownMaxY() {
        robot.down(Robot.MAX_Y)
        val expectedPosition = "(0, 0)"
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldGoToSamePositionWhenRightAndLeftSameSteps() {
        val initialLocation = robot.getLocation()
        robot.right(Robot.MAX_X * 1 / 4)
        robot.left(Robot.MAX_X * 1 / 4)
        assertEquals(initialLocation, robot.getLocation())
    }

    @Test
    fun shouldGoToSamePositionWhenLeftAndRightSameSteps() {
        val initialLocation = robot.getLocation()
        robot.left(Robot.MAX_X * 1 / 4)
        robot.right(Robot.MAX_X * 1 / 4)
        assertEquals(initialLocation, robot.getLocation())
    }

    @Test
    fun shouldGoToSamePositionWhenLeftStepsOrStepsPlusMaxX() {
        val initialLocation = robot.getLocation()
        robot.left(Robot.MAX_X * 1 / 4)
        val expectedLocation = robot.getLocation()
        robot.right(Robot.MAX_X * 1 / 4)
        assertEquals(initialLocation, robot.getLocation())
        robot.left(Robot.MAX_X * 1 / 4 + Robot.MAX_X)
        assertEquals(expectedLocation, robot.getLocation())
    }

    @Test
    fun shouldReturnToSamePositionWhenGoingLeftAndRightTheSameStepsAboveMax() {
        val expectedPosition = robot.getLocation()
        robot.left(Robot.MAX_X * 7 / 4)
        robot.right(Robot.MAX_X * 7 / 4)
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldGoToSamePositionWhenDownAndUpSameSteps() {
        val initialLocation = robot.getLocation()
        robot.down(Robot.MAX_Y * 1 / 4)
        robot.up(Robot.MAX_Y * 1 / 4)
        assertEquals(initialLocation, robot.getLocation())
    }

    @Test
    fun shouldGoToSamePositionWhenUpAndDownSameSteps() {
        val initialLocation = robot.getLocation()
        robot.up(Robot.MAX_Y * 1 / 4)
        robot.down(Robot.MAX_Y * 1 / 4)
        assertEquals(initialLocation, robot.getLocation())
    }

    @Test
    fun shouldGoToSamePositionWhenUpStepsOrStepsPlusMaxX() {
        val initialLocation = robot.getLocation()
        robot.up(Robot.MAX_Y * 1 / 4)
        val expectedLocation = robot.getLocation()
        robot.down(Robot.MAX_Y * 1 / 4)
        assertEquals(initialLocation, robot.getLocation())
        robot.up(Robot.MAX_Y * 1 / 4 + Robot.MAX_Y)
        assertEquals(expectedLocation, robot.getLocation())
    }

    @Test
    fun shouldReturnToSamePositionWhenGoingUpAndDownTheSameStepsAboveMax() {
        val expectedPosition = robot.getLocation()
        robot.up(Robot.MAX_Y * 7 / 4)
        robot.down(Robot.MAX_Y * 7 / 4)
        assertEquals(expectedPosition, robot.getLocation())
    }

    @Test
    fun shouldReturnCorrectPositionForASpecialTest() {
        robot.right(313)
        robot.down(415)
        robot.up(505)
        robot.left(703)
        assertEquals("(10, 10)", robot.getLocation())
    }
}