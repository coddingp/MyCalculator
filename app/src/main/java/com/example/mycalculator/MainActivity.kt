package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateDisplay("")
    }

    // operationList contains all the current operations
    val operationList: MutableList<String> = arrayListOf()
    //numberCache contains all the current digits
    val numberCache: MutableList<String> = arrayListOf()

    //This method returns string value of all the prev operations and digits
    fun makeString(list: List<String>,joiner: String = "") : String {
        if (list.isEmpty()) return ""
        return list.reduce { r, s -> r + joiner + s }
    }

    // clearCache method clears all the cash of nums and operations used before
    fun clearCache() {
        numberCache.clear()
        operationList.clear()
    }

    // updateDisplay shows what
    fun updateDisplay(mainDisplayString: String){
        val fullCalculationString = makeString(operationList, " ")
        var fullCalculationTextView = findViewById(R.id.fullCalculationText) as TextView
        fullCalculationTextView.text = fullCalculationString
        val mainTextView = findViewById(R.id.textView1) as TextView
        mainTextView.text = mainDisplayString
    }

    fun clearClick(view: View) {
        clearCache()
        updateDisplay("")
    }

    // equalsClick
    fun equalsClick(view: View) {
        operationList.add(makeString(numberCache))
        numberCache.clear()
        val calculator = StringCalculator()
        val answer = calculator.calculate(operationList)
        updateDisplay("=" + answer.toString())
        clearCache()
    }

    fun negateNumber(view: View){
        if (numberCache.isNotEmpty()) {
            if (numberCache.first().equals("-")) {
                numberCache.removeAt(0)
            } else numberCache.add(0, "-")
        } else numberCache.add("-")
        val numberString = makeString(numberCache)
        updateDisplay(numberString)
    }

    fun buttonClick(view: View) {
        val button = view as Button
        if (numberCache.isEmpty()) return
        operationList.add(makeString(numberCache))
        numberCache.clear()
        operationList.add(button.text.toString())
        updateDisplay(button.text.toString())
    }

    fun numberClick(view: View) {
        val button = view as Button
        val numberString = button.text
        numberCache.add(numberString.toString())
        val text = makeString(numberCache)
        updateDisplay(text)
    }

    //backspace function deletes 1 last dialed digit
    fun backspace(view: View) {
        val mainTextView = findViewById(R.id.textView1) as TextView
        mainTextView.text = mainTextView.text.substring(0, mainTextView.text.length - 1)
        numberCache.removeAt(numberCache.size-1)
    }
}