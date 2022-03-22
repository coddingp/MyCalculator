package com.example.mycalculator

class StringCalculator {
    fun calculate(calculationList: List<String>): Double {
        var currentOp: String = ""
        var currentNumber: Double = 0.0

        calculationList.forEach { token ->
            when {
                token.equals("+")
                        || token.equals("/")
                        || token.equals("*")
                        || token.equals("-") -> currentOp = token

                currentOp.equals("-") -> currentNumber -= token.toDouble()
                currentOp.equals("/") -> currentNumber /= token.toDouble()
                currentOp.equals("*") -> currentNumber *= token.toDouble()
                currentOp.equals("%") -> currentNumber *= token.toDouble()
//                    .also { currentNumber *= 0.01}
                else -> currentNumber += token.toDouble()
            }
        }
        return currentNumber
    }
}