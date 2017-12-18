fun main(args: Array<String>) {
    val checksumInput = "1640\t590\t93\t958\t73\t1263\t1405\t1363\t737\t712\t1501\t390\t68\t1554\t959\t79\n" +
            "4209\t128\t131\t2379\t2568\t2784\t2133\t145\t3618\t1274\t3875\t158\t1506\t3455\t1621\t3799\n" +
            "206\t1951\t2502\t2697\t2997\t74\t76\t78\t1534\t81\t2775\t2059\t3026\t77\t2600\t3067\n" +
            "373\t1661\t94\t102\t2219\t1967\t1856\t417\t1594\t75\t100\t2251\t2200\t1825\t1291\t1021\n" +
            "57\t72\t51\t1101\t1303\t60\t1227\t421\t970\t1058\t138\t333\t1320\t1302\t402\t1210\n" +
            "4833\t5427\t179\t3934\t4533\t5124\t4832\t2088\t94\t200\t199\t1114\t4151\t1795\t208\t3036\n" +
            "759\t876\t110\t79\t1656\t1691\t185\t544\t616\t312\t757\t1712\t92\t97\t1513\t1683\n" +
            "1250\t1186\t284\t107\t1190\t1233\t573\t1181\t1041\t655\t132\t547\t395\t146\t119\t515\n" +
            "505\t1726\t79\t180\t86\t1941\t1597\t1785\t1608\t1692\t968\t1177\t94\t184\t91\t31\n" +
            "1366\t2053\t1820\t1570\t70\t506\t53\t415\t717\t1263\t82\t366\t74\t1255\t2020\t1985\n" +
            "2365\t5585\t2285\t4424\t5560\t3188\t3764\t187\t88\t223\t1544\t5023\t4013\t5236\t214\t196\n" +
            "1487\t1305\t1359\t1615\t6579\t2623\t4591\t150\t5030\t188\t146\t4458\t5724\t5828\t1960\t221\n" +
            "3114\t688\t3110\t334\t1921\t153\t4083\t131\t2234\t3556\t3573\t3764\t127\t919\t3293\t104\n" +
            "1008\t78\t1196\t607\t135\t1409\t296\t475\t915\t157\t1419\t1304\t153\t423\t163\t704\n" +
            "235\t4935\t4249\t3316\t1202\t221\t1835\t380\t249\t1108\t1922\t5607\t4255\t238\t211\t3973\n" +
            "1738\t207\t179\t137\t226\t907\t1468\t1341\t1582\t1430\t851\t213\t393\t1727\t1389\t632"
    println(corruptionChecksum(checksumInput))
    println(corruptionChecksumPart2(checksumInput))
}

fun corruptionChecksum(input: String):Int {
    return corruptionChecksumCore(input, {it.max()!!.minus(it.min()!!)})
}

fun corruptionChecksumPart2(input: String):Int {
    return corruptionChecksumCore(input, {
        it.mapIndexed { index, i ->
            (index + 1 until it.size)
                    .map { nextIndex ->
                        when {
                            i > it[nextIndex] && i % it[nextIndex] == 0 -> i / it[nextIndex]
                            it[nextIndex] > i && it[nextIndex] % i == 0 -> it[nextIndex] / i
                            else -> 0
                        }
                    }
                    .filter { it != 0 }
                    .sum()
        }.sum()
    })
}

fun corruptionChecksumCore(input: String, transform: (List<Int>) -> Int):Int {
    return input.split("\n")
            .filter { it.isNotBlank() }
            .map { it.split("\t").map { it.toInt() } }
            .map { transform(it) }
            .sum()
}
