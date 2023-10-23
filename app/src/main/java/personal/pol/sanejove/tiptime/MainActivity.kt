package personal.pol.sanejove.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import personal.pol.sanejove.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {

        val cost =
            if (binding.costOfService.text.toString().toDoubleOrNull()==null) 0.0
            else binding.costOfService.text.toString().toDouble()

        binding.tipResult.text = getString(R.string.tip_amount)

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            binding.optionTwentyPercent.id -> 0.20
            binding.optionEighteenPercent.id -> 0.18
            else -> 0.15
        }
        var tipAmount = cost * tipPercentage
        if (binding.roundUpSwitch.isChecked) {
            tipAmount = kotlin.math.ceil(tipAmount)
        }
        displayTip(tipAmount)
    }
    private fun displayTip(tip : Double){
        binding.tipResult.text = getString(
            R.string.tip_amount_value,
            NumberFormat.
                getCurrencyInstance().
                format(tip)
        )
    }
}