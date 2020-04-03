package com.ryryapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.rollButton)

        val dice1TextView = findViewById<TextView>(R.id.dice1TextView)
        val dice2TextView = findViewById<TextView>(R.id.dice2TextView)

        val dice1ImageView = findViewById<ImageView>(R.id.dice1ImageView)
        val dice2ImageView = findViewById<ImageView>(R.id.dice2ImageView)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val howManyTextView = findViewById<TextView>(R.id.howManyTextView)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                // Display the current progress of SeekBar
                val j = i + 1
                howManyTextView.text = "Dice max roll: $j"
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

            dice1ImageView.startAnimation(shake)
            dice2ImageView.startAnimation(shake)

            val dice1 = Random().nextInt(seekBar.progress + 1) + 1
            val dice2 = Random().nextInt(seekBar.progress + 1) + 1

            dice1TextView.text = dice1.toString()
            dice2TextView.text = dice2.toString()
        }
    }
}
