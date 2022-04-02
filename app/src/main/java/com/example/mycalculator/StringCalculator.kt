package com.example.mycalculator

import kotlin.math.*

class StringCalculator {
    fun calculate(calculationList: List<String>): Double {
        var currentOp: String = ""
        var currentNumber: Double = 0.0

        calculationList.forEach { token ->
            when {
                token.equals("+")
                        || token.equals("/")
                        || token.equals("*")
                        || token.equals("%")
                        || token.equals("√")
                        || token.equals("sin")
                        || token.equals("tan")
                        || token.equals("ctn")
                        || token.equals("cos")
                        || token.equals("log")
                        || token.equals("pow")
                        || token.equals("xY")
                        || token.equals("-") -> currentOp = token
                currentOp.equals("-") -> currentNumber -= token.toDouble()
                currentOp.equals("/") -> currentNumber /= token.toDouble()
                currentOp.equals("*") -> currentNumber *= token.toDouble()
                currentOp.equals("√") -> currentNumber = sqrt(currentNumber)
                currentOp.equals("sin") -> currentNumber = sin(currentNumber)
                currentOp.equals("cos") -> currentNumber = cos(currentNumber)
                currentOp.equals("tan") -> currentNumber = tan(currentNumber)
                currentOp.equals("ctn") -> currentNumber = 1.0 / tan(currentNumber)
                currentOp.equals("log") -> currentNumber = ln(currentNumber)
                currentOp.equals("pow") -> currentNumber = currentNumber.pow(token.toDouble())
                currentOp.equals("%") -> currentNumber *= 0.01
                else -> currentNumber += token.toDouble()
            }
        }
        return currentNumber
    }
}