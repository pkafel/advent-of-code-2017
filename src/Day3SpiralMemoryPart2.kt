fun main(args: Array<String>) {
    println(findFirstElementGreaterThan(270000))
}

private fun findFirstElementGreaterThan(limit: Int):Int {
    var ringNumber = 1
    var x = 1
    var y = -1

    val cache = mutableMapOf(Pair("00", 1))

    while (true) {
        for(i in 0..(2*ringNumber - 1)) {
            y++
            val value = getSumForElem(x, y, cache)
            if(value > limit) return value
            cache.put(x.toString() + y.toString(), value)
        }

        for(i in 0..(2*ringNumber - 1)) {
            x--
            val value = getSumForElem(x, y, cache)
            if(value > limit) return value
            cache.put(x.toString() + y.toString(), value)
        }

        for(i in 0..(2*ringNumber - 1)) {
            y--
            val value = getSumForElem(x, y, cache)
            if(value > limit) return value
            cache.put(x.toString() + y.toString(), value)
        }

        for(i in 0..(2*ringNumber - 1)) {
            x++
            val value = getSumForElem(x, y, cache)
            if(value > limit) return value
            cache.put(x.toString() + y.toString(), value)
        }

        x++
        y--
        ringNumber++
    }
}

fun getSumForElem(x: Int, y: Int, cache: Map<String, Int>): Int {
    return cache.getOrDefault((x-1).toString() + (y-1).toString(), 0) +
            cache.getOrDefault((x-1).toString() + (y).toString(), 0) +
            cache.getOrDefault((x-1).toString() + (y+1).toString(), 0) +
            cache.getOrDefault((x).toString() + (y-1).toString(), 0) +
            cache.getOrDefault((x).toString() + (y+1).toString(), 0) +
            cache.getOrDefault((x+1).toString() + (y-1).toString(), 0) +
            cache.getOrDefault((x+1).toString() + (y).toString(), 0) +
            cache.getOrDefault((x+1).toString() + (y+1).toString(), 0)
}
