package com.ryryapps

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
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
        val diceDescriptionTextView = findViewById<TextView>(R.id.diceDescriptionTextView)
        val dice2DescriptionTextView = findViewById<TextView>(R.id.dice2DescriptionTextView)

        val dice1ImageView = findViewById<ImageView>(R.id.dice1ImageView)
        val dice2ImageView = findViewById<ImageView>(R.id.dice2ImageView)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val howManyTextView = findViewById<TextView>(R.id.howManyTextView)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)

        var numberOfDice = sharedPreferences.getString("how_may_dice", "1")

        var dice1 = sharedPreferences.getString("dice_1", "6")
        var dice2 = sharedPreferences.getString("dice_2", "6")
        diceDescriptionTextView.text = "d" + dice1
        dice2DescriptionTextView.text = "d" + dice2

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                val j = i + 1
                howManyTextView.text = "Dice size: $j"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })

        rollButton.setOnClickListener() {

            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)

            // Animate dice shake action.
            dice1TextView.startAnimation(shake)
            dice1ImageView.startAnimation(shake)
            dice2TextView.startAnimation(shake)
            dice2ImageView.startAnimation(shake)

            // Set random number on dice.
            val dice1 = Random().nextInt(dice1!!.toInt()) + 1
            val dice2 = Random().nextInt(seekBar.progress + 1) + 1
            dice1TextView.text = dice1.toString()
            dice2TextView.text = dice2.toString()
        }

        settingsButton.setOnClickListener() {
            val intent = Intent(this, SettingsActivity::class.java).apply{}
            startActivity(intent)
        }

    }
}
