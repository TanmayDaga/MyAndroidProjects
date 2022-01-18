package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calculator.databinding.ActivityMainBinding
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    var engine = ScriptEngineManager().getEngineByName("rhino")
    var result:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
    }

    public fun buttonClicked(view: View){
        var tagVal = view.getTag()
        var presentText :String = binding.textViewResult.text.toString()
        if(tagVal != "=" && tagVal != "BS" && tagVal != "C"){
            result += tagVal
            binding.textViewResult.setText(presentText + tagVal.toString())
        }
        else if (tagVal == "="){
            try {


                binding.textViewResult.text = engine.eval(presentText).toString()

            }
            catch (e:Exception){
                Toast.makeText(this,"Invalid Sequence",Toast.LENGTH_SHORT).show()
                binding.textViewResult.text = ""
            }
        }
        else if(tagVal == "BS"){
            binding.textViewResult.text= presentText.dropLast(1)
        }
        else if(tagVal == "C"){
            binding.textViewResult.text = ""
        }
    }
}