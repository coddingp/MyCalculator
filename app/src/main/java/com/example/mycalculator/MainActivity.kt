package com.example.mycalculator

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.databinding.ActivityMainBinding

// operationList contains all the current operations
val operationList: MutableList<String> = arrayListOf()

//numberCache contains all the current digits
val numberCache: MutableList<String> = arrayListOf()


class MainActivity : AppCompatActivity() {
    public val e = 2.7182818284590452353602874713526624977572470936999
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        updateDisplay("")
    }


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
        with(binding) {
            val fullCalculationString = makeString(operationList, " ")
            fullCalculationText.text = fullCalculationString
            textView1.text = mainDisplayString
            setContentView(root)
        }
    }

    //clears display
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
        updateDisplay(answer.toString())
        clearCache()
        numberCache.add(0, answer.toString())
    }

    //turns any negate number into a posetive and backwards
    fun negateNumber(view: View) {
        if (numberCache.isNotEmpty()) {
            if (numberCache.first().equals("-")) {
                numberCache.removeAt(0)
            } else numberCache.add(0, "-")
        } else numberCache.add("-")
        val numberString = makeString(numberCache)
        updateDisplay(numberString)
    }

    //all operations buttons
    fun buttonClick(view: View) {
        val button = view as Button
        if (numberCache.isEmpty()) return
        operationList.add(makeString(numberCache))
        numberCache.clear()
        operationList.add(button.text.toString())
        updateDisplay(button.text.toString())
    }

    //all number buttons
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
        binding.apply {
            textView1.text =
                textView1.text.substring(0, textView1.text.length - 1)
            numberCache.removeAt(numberCache.size - 1)
            setContentView(root)
        }
    }

    //creates new activity
    fun pro(view: View) {
        intent = Intent(this, ProActivity::class.java)
        startActivity(intent)
    }

    //eCklick gives you E number
    fun eClick(view: View) {
        numberCache.add(e.toString())
        binding.textView1.text = e.toString()
    }

    //piClick gives us Pi number
    fun piClick(view: View) {
        numberCache.add(Math.PI.toString())
        binding.textView1.text = Math.PI.toString()
    }
}