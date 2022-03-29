package com.example.mycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.ActivityInfo
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.mycalculator.databinding.ActivityProBinding

class ProActivity : AppCompatActivity() {
    private val binding: ActivityProBinding by lazy {
        ActivityProBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        updateDisplay("")
    }

    // operationList contains all the current operations
    val operationList: MutableList<String> = arrayListOf()

    //numberCache contains all the current digits
    val numberCache: MutableList<String> = arrayListOf()

    //This method returns string value of all the prev operations and digits
    fun makeString(list: List<String>, joiner: String = ""): String {
        if (list.isEmpty()) return ""
        return list.reduce { r, s -> r + joiner + s }
    }

    // clearCache method clears all the cash of nums and operations used before
    fun clearCache() {
        numberCache.clear()
        operationList.clear()
    }

    // updateDisplay shows what
    fun updateDisplay(mainDisplayString: String) {
        val fullCalculationString = makeString(operationList, " ")
        binding.fullCalculationText.text = fullCalculationString
        binding.textView1.text = mainDisplayString
        setContentView(binding.root)
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

    fun negateNumber(view: View) {
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
        if (!button.text.equals("pro")) {
            val numberString = button.text
            numberCache.add(numberString.toString())
            val text = makeString(numberCache)
            updateDisplay(text)
        } else {
            val numberString = ""
            numberCache.add(numberString.toString())
            val text = makeString(numberCache)
        }
    }

    //backspace function deletes 1 last dialed digit
    fun backspace(view: View) {
        binding.textView1.text =
            binding.textView1.text.substring(0, binding.textView1.text.length - 1)
        numberCache.removeAt(numberCache.size - 1)
        setContentView(binding.root)
    }

    //pro button makes a new activity
    fun pro(view: View) {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //piClick button types pi number in the console 3.14159265359
    fun piClick(view: View) {
        numberCache.add(3.14159265359.toString())
        binding.textView1.text = 3.14159265359.toString()
    }



}