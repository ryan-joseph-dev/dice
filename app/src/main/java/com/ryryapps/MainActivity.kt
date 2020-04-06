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
        val diceDescriptionTextView = findViewById<TextView>(R.id.diceDescriptionTextView)
        val dice2DescriptionTextView = findViewById<TextView>(R.id.dice2DescriptionTextView)

        val dice1ImageView = findViewById<ImageView>(R.id.dice1ImageView)
        val dice2ImageView = findViewById<ImageView>(R.id.dice2ImageView)

        val howManyTextView = findViewById<TextView>(R.id.TotalTextView)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)

        var dice1 = sharedPreferences.getString("dice_1", "6")
        var dice2 = sharedPreferences.getString("dice_2", "6")
        diceDescriptionTextView.text = "d" + dice1
        dice2DescriptionTextView.text = "d" + dice2

        var numberOfDice = sharedPreferences.getString("how_many_dice", "1")

        if (numberOfDice == "1") {
            dice2TextView.text = ""
            dice2DescriptionTextView.text = ""
            dice2ImageView.visibility = View.INVISIBLE
        }

        rollButton.setOnClickListener() {

            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)

            // Animate dice shake action.
            dice1TextView.startAnimation(shake)
            dice1ImageView.startAnimation(shake)

            // Set random number on dice.
            val dice1 = Random().nextInt(dice1!!.toInt()) + 1
            dice1TextView.text = dice1.toString()

            if (numberOfDice!!.toInt() >= 2) {
                dice2TextView.startAnimation(shake)
                dice2ImageView.startAnimation(shake)

                val dice2 = Random().nextInt(dice2!!.toInt()) + 1
                dice2TextView.text = dice2.toString()
            }
        }

        settingsButton.setOnClickListener() {
            val intent = Intent(this, SettingsActivity::class.java).apply{}
            startActivity(intent)
        }
    }
}
