fun main(args: Array<String>) {
    println(getNumberOfRedestributions("0 2 7 0"))
    println(getNumberOfRedestributions("10\t3\t15\t10\t5\t15\t5\t15\t9\t2\t5\t8\t5\t2\t3\t6", "\t"))

    println(getNumberLoopsBetweenSameState("10\t3\t15\t10\t5\t15\t5\t15\t9\t2\t5\t8\t5\t2\t3\t6", "\t"))
}

fun getNumberOfRedestributions(input: String, delimiter: String = " "): Int {
    return generateRedistributionsUntilRepeated(input, delimiter).size - 1
}

fun getNumberLoopsBetweenSameState(input: String, delimiter: String = " "): Int {
    val result = generateRedistributionsUntilRepeated(input, delimiter)
    return result.size - 1 - result.indexOf(result.last())
}

private fun generateRedistributionsUntilRepeated(input: String, delimiter: String): List<String> {
    val memory = input.split(delimiter).map { it.toInt() }.toMutableList()
    val knownMemoryConfigurations = mutableListOf<String>()

    var config = memory.toString()
    while(!knownMemoryConfigurations.contains(config)) {
        knownMemoryConfigurations.add(config)

        val max = memory.max()!!
        val maxIndex= memory.indexOf(max)
        memory[maxIndex] = 0

        (1..max).map { (maxIndex + it) % memory.size }
                .forEach { memory[it] = memory[it] + 1 }
        config = memory.toString()
    }

    knownMemoryConfigurations.add(config)
    return knownMemoryConfigurations
}
