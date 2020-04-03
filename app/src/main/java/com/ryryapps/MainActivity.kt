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


        rollButton.setOnClickListener() {

            val shake = AnimationUtils.loadAnimation(this, R.anim.shake)

            dice1ImageView.startAnimation(shake)
            dice2ImageView.startAnimation(shake)

            val dice1 = Random().nextInt(seekBar.progress) + 1
            val dice2 = Random().nextInt(seekBar.progress) + 1

            dice1TextView.text = dice1.toString()
            dice2TextView.text = dice2.toString()
        }
    }
}
