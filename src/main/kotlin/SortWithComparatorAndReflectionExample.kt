import java.time.Instant
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

fun main() {
    val enrollments = getSampleEnrollments()

    val trainingContentEnrollmentPairs: List<Pair<TrainingContentViewData, Enrollment>> =
        getSampleTrainingContentViewData()
            .mapNotNull { trainingContent ->
                enrollments.find { it.trainingContentId == trainingContent.id }?.let { enrollment ->
                    trainingContent to enrollment
                }
            }
    println("list of pairs size = ${trainingContentEnrollmentPairs.size}")
    val sortByLastViewAsc = SortConfig("/enrollment/lastViewAt", false)
    println("before sorting")
    trainingContentEnrollmentPairs.forEach(::println)
    val response = sortFieldsBasedOnConfiguration(trainingContentEnrollmentPairs, sortByLastViewAsc)
    println("after sorting by ${sortByLastViewAsc.path} asc=${sortByLastViewAsc.asc}")
    response.forEach(::println)

    val sortByLastViewDesc = SortConfig("/enrollment/lastViewAt", true)
    val response2 = sortFieldsBasedOnConfiguration(trainingContentEnrollmentPairs, sortByLastViewDesc)
    println("after sorting by ${sortByLastViewDesc.path} asc=${sortByLastViewDesc.asc}")
    response2.forEach(::println)

    val sortByTitleAsc = SortConfig("/trainingContent/title", true)
    val response3 = sortFieldsBasedOnConfiguration(trainingContentEnrollmentPairs, sortByTitleAsc)
    println("after sorting by ${sortByTitleAsc.path} asc=${sortByTitleAsc.asc}")
    response3.forEach(::println)

    val sortByCompletionRateAsc = SortConfig("/enrollment/completionRate", true)
    val response4 = sortFieldsBasedOnConfiguration(trainingContentEnrollmentPairs, sortByCompletionRateAsc)
    println("after sorting by ${sortByCompletionRateAsc.path} asc=${sortByCompletionRateAsc.asc}")
    response4.forEach(::println)
    val sortByCompletionRateDesc = SortConfig("/enrollment/completionRate", false)
    val response5 = sortFieldsBasedOnConfiguration(trainingContentEnrollmentPairs, sortByCompletionRateDesc)
    println("after sorting by ${sortByCompletionRateDesc.path} asc=${sortByCompletionRateDesc.asc}")
    response5.forEach(::println)
}

fun getSampleTrainingContentViewData(): List<TrainingContentViewData> = listOf(
    TrainingContentViewData(
        id = 1L,
        title = "zcd",
        description = "description 1",
        thumbnail = "thumbnail 1",
        duration = 1000L,
        category = "category 1",
        sourceLink = "sourceLink 1",
        externalIdentifier = "externalIdentifier 1"
    ),
    TrainingContentViewData(
        id = 2L,
        title = "abc",
        description = "description 2",
        thumbnail = "thumbnail 2",
        duration = 2000L,
        category = "category 2",
        sourceLink = "sourceLink 2",
        externalIdentifier = "externalIdentifier 2"
    ),
    TrainingContentViewData(
        id = 3L,
        title = "xpto",
        description = "description 3",
        thumbnail = "thumbnail 3",
        duration = 3000L,
        category = "category 3",
        sourceLink = "sourceLink 3",
        externalIdentifier = "externalIdentifier 3"
    )
)

private fun getSampleEnrollments() = listOf(
    Enrollment(
        id = 1L,
        trainingContentId = 1L,
        completionRate = 7,
        currentChapterId = null,
        startedAt = Instant.now(),
        finishedAt = null,
        lastViewAt = Instant.now().minusSeconds(10),
        creationDate = Instant.now().minusSeconds(100)
    ),
    Enrollment(
        id = 2L,
        trainingContentId = 2L,
        completionRate = 3,
        currentChapterId = null,
        startedAt = Instant.now(),
        finishedAt = null,
        lastViewAt = Instant.now().minusSeconds(20),
        creationDate = Instant.now().minusSeconds(200)
    ),
    Enrollment(
        id = 3L,
        trainingContentId = 3L,
        completionRate = 15,
        currentChapterId = null,
        startedAt = Instant.now(),
        finishedAt = null,
        lastViewAt = Instant.now().minusSeconds(30),
        creationDate = Instant.now()
    )
)

private fun sortFieldsBasedOnConfiguration(
    elements: List<Pair<TrainingContentViewData, Enrollment>>,
    sortConfig: SortConfig
): List<Pair<TrainingContentViewData, Enrollment>> {
    val comparator = generateComparatorWithReflection(
        sortConfig
    )
    return elements.sortedWith(comparator)
}

private fun generateComparator(sortConfig: SortConfig): Comparator<Pair<TrainingContentViewData, Enrollment>> {
    // requires the path to be in form /<entity>/<field_name>
    require(sortConfig.path.matches("/\\w+/\\w+".toRegex())) { "Invalid path: ${sortConfig.path}" }

    val (_, entity, sortBy) = sortConfig.path.split("/")

    val comparator: Comparator<Pair<TrainingContentViewData, Enrollment>> = when (entity) {
        "trainingContent" -> when (sortBy) {
            "id" -> compareBy { it.first.id }
            "title" -> compareBy { it.first.title }
            else -> compareBy { it.first.id }
        }

        "enrollment" -> when (sortBy) {
            "creation_date" -> compareBy { it.second.creationDate }
            "completionRate" -> compareBy { it.second.completionRate }
            "lastViewAt" -> compareBy { it.second.lastViewAt }
            else -> compareBy { it.second.creationDate }
        }

        else -> compareBy { it.second.creationDate }
    }

    return if (sortConfig.asc) comparator else comparator.reversed()
}

private fun generateComparatorWithReflection(sortConfig: SortConfig): Comparator<Pair<TrainingContentViewData, Enrollment>> {
    val sortBy = sortConfig.path.split("/")[2]

    val fieldComparator: (Pair<TrainingContentViewData, Enrollment>) -> Comparable<*>? =
        { pair: Pair<TrainingContentViewData, Enrollment> ->
            val fieldInFirst = pair.first::class.memberProperties.find { it.name == sortBy }
            val fieldInSecond = pair.second::class.memberProperties.find { it.name == sortBy }

            @Suppress("UNCHECKED_CAST")
            when {
                fieldInFirst != null -> (fieldInFirst as KProperty1<TrainingContentViewData, *>).get(pair.first) as? Comparable<*>
                fieldInSecond != null -> (fieldInSecond as KProperty1<Enrollment, *>).get(pair.second) as? Comparable<*>
                else -> null
            }
        }

    return if (sortConfig.asc) compareBy(fieldComparator) else compareByDescending(fieldComparator)
}

/*
private fun generateComparator4(sortConfig: SortConfig): Comparator<Pair<TrainingContentViewData, Enrollment>> {
    val (sortBy, asc) = sortConfig.run { path.split("/")[2] to asc }

    return when (sortBy) {
        "title" -> compareBy { if (asc) it.first.title else -it.first.title }
        "creation_date" -> compareBy { if (asc) it.second.creationDate else -it.second.creationDate }
        "completionRate" -> compareBy { if (asc) it.second.completionRate else -it.second.completionRate }
        "lastViewAt" -> compareBy { if (asc) it.second.lastViewAt else -it.second.lastViewAt }
        else -> compareByDescending { it.second.lastViewAt }
    }
}
*/


private fun generateComparator3(sortConfig: SortConfig): Comparator<Pair<TrainingContentViewData, Enrollment>> {
    val sortBy = sortConfig.path.split("/")[2]

    val fields = mapOf(
        "id" to { it: Pair<TrainingContentViewData, Enrollment> -> it.first.id },
        "title" to { it: Pair<TrainingContentViewData, Enrollment> -> it.first.title },
        "creation_date" to { it: Pair<TrainingContentViewData, Enrollment> -> it.second.creationDate },
        "completionRate" to { it: Pair<TrainingContentViewData, Enrollment> -> it.second.completionRate },
        "lastViewAt" to { it: Pair<TrainingContentViewData, Enrollment> -> it.second.lastViewAt }
    )

    val fieldComparator = fields[sortBy] ?: { it: Pair<TrainingContentViewData, Enrollment> -> it.second.lastViewAt }

    return if (sortConfig.asc) compareBy(fieldComparator) else compareByDescending(fieldComparator)
}


private fun generateComparator2(sortConfig: SortConfig): Comparator<Pair<TrainingContentViewData, Enrollment>> {
    // assume path is in form /<entity>/<field_name>
    // review EnrollmentSortOptions.kt for all possibilities
    val entity = sortConfig.path.split("/")[1]
    val sortBy = sortConfig.path.split("/")[2]
    val asc = sortConfig.asc

    return when (sortBy) {
        "id" -> if (asc) compareBy { it.first.id } else compareByDescending { it.first.id }
        "title" -> if (asc) compareBy { it.first.title } else compareByDescending { it.first.title }
        "creation_date" -> if (asc) compareBy { it.second.creationDate } else compareByDescending { it.second.creationDate }
        "completionRate" -> if (asc) compareBy { it.second.completionRate } else compareByDescending { it.second.completionRate }
        "lastViewAt" -> if (asc) compareBy { it.second.lastViewAt } else compareByDescending { it.second.lastViewAt }
        else -> compareByDescending { it.second.lastViewAt } // this is the default
    }
}

data class TrainingContentViewData(
    val id: Long,
    val title: String,
    val description: String?,
    val thumbnail: String?,
    val duration: Long?,
    val category: String?,
    val sourceLink: String?,
    val externalIdentifier: String?
)

data class Enrollment(
    val id: Long,
    val trainingContentId: Long,
    val completionRate: Int = 0,
    val currentChapterId: Long? = null,
    val startedAt: Instant? = null,
    val finishedAt: Instant? = null,
    val lastViewAt: Instant? = null,
    val creationDate: Instant? = null,
)

data class SortConfig(
    val path: String,
    val asc: Boolean
)