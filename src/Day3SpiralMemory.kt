fun main(args: Array<String>) {
    println(spiralMemory(277678))
}

fun spiralMemory(square: Int): Int {
    if (square == 1) return 1

    val ringNumber = findRingNumber(square)
    val sideLength = 2 * ringNumber - 1
    val lastNumberInRing = 4 * ringNumber * ringNumber - 4 * ringNumber + 1
    val squarePositionOnSide = (lastNumberInRing - square) % (sideLength - 1)
    val middleOfTheRingSide = Math.ceil(sideLength / 2.0).toInt() - 1

    return ringNumber - 1 + Math.abs(squarePositionOnSide - middleOfTheRingSide)
}

private fun findRingNumber(square: Int): Int {
    var i = 1
    var result = 1
    do {
        result += i++ * 8
    } while (result < square)
    return i
}
