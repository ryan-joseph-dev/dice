package com.ryryapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ryryapps.dice.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.rollButton)
        val settingsButton = findViewById<FloatingActionButton>(R.id.settingsButton)

        val dice1TextView = findViewById<TextView>(R.id.dice1TextView)
        val dice2TextView = findViewById<TextView>(R.id.dice2TextView)
        val dice3TextView = findViewById<TextView>(R.id.dice3TextView)
        val dice4TextView = findViewById<TextView>(R.id.dice4TextView)

        val dice1DescriptionTextView = findViewById<TextView>(R.id.dice1DescriptionTextView)
        val dice2DescriptionTextView = findViewById<TextView>(R.id.dice2DescriptionTextView)
        val dice3DescriptionTextView = findViewById<TextView>(R.id.dice3DescriptionTextView)
        val dice4DescriptionTextView = findViewById<TextView>(R.id.dice4DescriptionTextView)

        val dice1ImageView = findViewById<ImageView>(R.id.dice1ImageView)
        val dice2ImageView = findViewById<ImageView>(R.id.dice2ImageView)
        val dice3ImageView = findViewById<ImageView>(R.id.dice3ImageView)
        val dice4ImageView = findViewById<ImageView>(R.id.dice4ImageView)

        val totalTextView = findViewById<TextView>(R.id.TotalTextView)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)

        val dice1Size = sharedPreferences.getString("dice_1", "6")
        val dice2Size = sharedPreferences.getString("dice_2", "6")
        val dice3Size = sharedPreferences.getString("dice_3", "6")
        val dice4Size = sharedPreferences.getString("dice_4", "6")

        dice1DescriptionTextView.text = "d$dice1Size"
        dice2DescriptionTextView.text = "d$dice2Size"
        dice3DescriptionTextView.text = "d$dice3Size"
        dice4DescriptionTextView.text = "d$dice4Size"

        val numberOfDice = sharedPreferences.getString("how_many_dice", "1")

        when (numberOfDice) {
            "1" -> {
                dice2TextView.text = ""
                dice2DescriptionTextView.text = ""
                dice2ImageView.visibility = View.INVISIBLE

                dice3TextView.text = ""
                dice3DescriptionTextView.text = ""
                dice3ImageView.visibility = View.INVISIBLE

                dice4TextView.text = ""
                dice4DescriptionTextView.text = ""
                dice4ImageView.visibility = View.INVISIBLE
            }
            "2" -> {
                dice3TextView.text = ""
                dice3DescriptionTextView.text = ""
                dice3ImageView.visibility = View.INVISIBLE

                dice4TextView.text = ""
                dice4DescriptionTextView.text = ""
                dice4ImageView.visibility = View.INVISIBLE
            }
            "3" -> {
                dice4TextView.text = ""
                dice4DescriptionTextView.text = ""
                dice4ImageView.visibility = View.INVISIBLE
            }

            else -> { // Number of dice == 4
            }
        }

        rollButton.setOnClickListener {

            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)

            // Animate dice shake action.
            dice1TextView.startAnimation(shake)
            dice1ImageView.startAnimation(shake)

            // Set random number on dice.
            val dice1Value = Random().nextInt(dice1Size!!.toInt()) + 1
            var total = dice1Value
            dice1TextView.text = dice1Value.toString()

            if (numberOfDice!!.toInt() >= 2) {
                dice2TextView.startAnimation(shake)
                dice2ImageView.startAnimation(shake)

                val dice2Value = Random().nextInt(dice2Size!!.toInt()) + 1
                dice2TextView.text = dice2Value.toString()

                total += dice2Value
            }
            if (numberOfDice.toInt() >= 3) {
                dice3TextView.startAnimation(shake)
                dice3ImageView.startAnimation(shake)

                val dice3Value = Random().nextInt(dice3Size!!.toInt()) + 1
                dice3TextView.text = dice3Value.toString()

                total += dice3Value
            }
            if (numberOfDice.toInt() == 4) {
                dice4TextView.startAnimation(shake)
                dice4ImageView.startAnimation(shake)

                val dice4Value = Random().nextInt(dice4Size!!.toInt()) + 1
                dice4TextView.text = dice4Value.toString()

                total += dice4Value
            }

            totalTextView.text = "Total: $total"
        }

        settingsButton.setOnClickListener() {
            val intent = Intent(this, SettingsActivity::class.java).apply{}
            startActivity(intent)
        }
    }
}
