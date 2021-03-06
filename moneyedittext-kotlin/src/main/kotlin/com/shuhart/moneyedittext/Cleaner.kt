package com.shuhart.moneyedittext

interface Cleaner {
    fun clean(text: String, selection: Int): Pair<String, Int>
}

class CleanerImpl : Cleaner {
    override fun clean(text: String, selection: Int): Pair<String, Int> {
        if (text.isEmpty()) return Pair("", 0)

        val sb = StringBuilder()

        val reversedText = text.reversed()
        var reversedSelection = text.length - selection
        var separatorAlreadyAdded = false

        reversedText.forEachIndexed { index, c ->
            if (c.toString() == "." && !separatorAlreadyAdded) {
                sb.append(c.toString())
                separatorAlreadyAdded = true
            } else if (c.isDigit()) {
                sb.append(c.toString())
            } else if (index >= reversedSelection) {
                reversedSelection++
            }
        }

        val realSelection = text.length - reversedSelection
        return Pair(sb.reverse().toString(), realSelection)
    }
}

