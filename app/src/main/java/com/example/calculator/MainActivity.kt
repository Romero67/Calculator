package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var tvTotal : TextView
    private var bandera : Boolean = false
    private var auxString : String = ""
    private var number1 : Float? = null
    private var number2 : Float? = null
    private lateinit var operation : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun handleClick(uv: View){
        val tag : String = uv.tag?.toString() ?: ""
        tvTotal = findViewById<TextView>(R.id.tv_total)
        when{
            isNumber(tag[0]) -> {
                tvTotal.text = auxString + tag
                auxString += tag
                if(bandera){
                    number1 = null
                }
            }
            tag == "C" ->{
                resetCalculator()
            }
            tag == "delete" ->{
                auxString = auxString.dropLast(1)
                if(auxString == ""){
                    resetCalculator()
                }else{
                    tvTotal.text = auxString
                }
            }
            tag == "division" ->{
                if(number1 == null){
                    number1 = auxString.toFloat()
                    tvTotal.text = auxString + " ÷"
                    auxString = ""
                }else{
                    try {
                        number2 = auxString.toFloat()
                        val result :Float = calculate(operation, number1, number2)
                        tvTotal.text = result.toString()
                        number1 = result
                        auxString = ""
                        number2 = null
                    }
                    catch (e: java.lang.Exception){
                        println("error: $e")
                        tvTotal.text = number1.toString() + " ÷"
                    }
                }
                operation = "division"
                bandera = false
            }
            tag == "multiply" ->{
                if(number1 == null){
                    number1 = auxString.toFloat()
                    tvTotal.text = auxString + " x"
                    auxString = ""
                }else{
                    try {
                        number2 = auxString.toFloat()
                        val result :Float = calculate(operation, number1, number2)
                        tvTotal.text = result.toString()
                        number1 = result
                        auxString = ""
                        number2 = null
                    }
                    catch (e: java.lang.Exception){
                        println("error: $e")
                        tvTotal.text = number1.toString() + " x"
                    }
                }
                operation = "multiply"
                bandera = false
            }
            tag == "dash" ->{
                if(number1 == null){
                    number1 = auxString.toFloat()
                    tvTotal.text = auxString + " -"
                    auxString = ""
                }else{
                    try {
                        number2 = auxString.toFloat()
                        val result :Float = calculate(operation, number1, number2)
                        tvTotal.text = result.toString()
                        number1 = result
                        auxString = ""
                        number2 = null
                    }
                    catch (e: java.lang.Exception){
                        println("error: $e")
                        tvTotal.text = number1.toString() + " -"
                    }
                }
                operation = "dash"
                bandera = false
            }
            tag == "plus" ->{
                if(number1 == null){
                    number1 = auxString.toFloat()
                    tvTotal.text = auxString + " +"
                    auxString = ""
                }else{
                    try {
                        number2 = auxString.toFloat()
                        val result :Float = calculate(operation, number1, number2)
                        tvTotal.text = result.toString()
                        number1 = result
                        auxString = ""
                        number2 = null
                    }
                    catch (e: java.lang.Exception){
                        println("error: $e")
                        tvTotal.text = number1.toString() + " +"
                    }
                }
                operation = "plus"
                bandera = false
            }
            tag == "equal" ->{
                if(number1 == null) return
                try {
                    number2 = auxString.toFloat()
                }
                catch (e: Exception){
                    println("error $e")
                }
                val result :Float = calculate(operation, number1, number2)
                tvTotal.text = result.toString()
                number1 = result
                auxString = ""
                bandera = true
            }
            else ->{
                Toast.makeText(this, "Error in when block", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calculate(op : String, n1 : Float?, n2 : Float?) : Float{
        var result : Float = 0f
        if(n1 == null || n2 == null) return 0f
        println("op: $op, n1: $n1, n2: $n2")
        when (op) {
            "division" -> {
                result = n1 / n2
            }
            "multiply" -> {
                result = n1 * n2
            }
            "dash" -> {
                result = n1 - n2
            }
            "plus" -> {
                result = n1 + n2
            }
            else -> {
                Toast.makeText(this, "Error in when block", Toast.LENGTH_SHORT).show()
            }
        }
        println("result: $result")
        return result
    }

    private fun resetCalculator(){
        tvTotal.text = "Total = "
        number1 = null
        number2 = null
        auxString = ""
        operation = ""
    }

    private fun isNumber(c: Char): Boolean {
        return c.toString().matches("[0-9.]".toRegex())
    }
}