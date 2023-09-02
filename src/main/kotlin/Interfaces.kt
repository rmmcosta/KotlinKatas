import kotlin.math.exp

interface Remote {
    fun up()
    fun down()
    fun doubleUp() {
        up()
        up()
    }

    fun doubleDown() {
        down()
        down()
    }
}

class TV {
    var volume: Int = 0
}

class TVRemote(val tv: TV) : Remote {
    companion object {
        private const val MAX_VOLUME = 100
    }

    override fun up() {
        if (tv.volume + 1 <= MAX_VOLUME)
            tv.volume++
    }

    override fun down() {
        if (tv.volume - 1 >= 0)
            tv.volume--
    }
}

class CombinedRemote {
    companion object {
        fun combine(remote1: Remote, remote2: Remote): Remote = object : Remote {
            override fun up() {
                remote1.up()
                remote2.up()
            }

            override fun down() {
                remote1.down()
                remote2.down()
            }

        }
    }
}

fun main() {
    val tv1 = TV()
    val tv2 = TV()
    val remote1: Remote = TVRemote(tv1)
    val remote2: Remote = TVRemote(tv2)
    printTVVolume(tv1, 0)
    printTVVolume(tv2, 0)
    remote2.up()
    remote1.up()
    printTVVolume(tv1, 1)
    printTVVolume(tv2, 1)
    remote2.doubleUp()
    remote1.doubleUp()
    printTVVolume(tv1, 3)
    printTVVolume(tv2, 3)
    val twoRemotes: Remote = CombinedRemote.combine(remote1, remote2)
    twoRemotes.doubleDown()
    printTVVolume(tv1, 1)
    printTVVolume(tv2, 1)
    twoRemotes.doubleDown()
    printTVVolume(tv1, 0)
    printTVVolume(tv2, 0)
    twoRemotes.down()
    printTVVolume(tv1,0)
    printTVVolume(tv2,0)
}

fun printTVVolume(tv: TV, expectedVolume:Int) {
    println("tv $tv volume = ${tv.volume}, expected volume = $expectedVolume")
}