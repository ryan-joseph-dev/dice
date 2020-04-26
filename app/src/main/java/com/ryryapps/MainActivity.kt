package com.ryryapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ryryapps.dice.R
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

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

        val theme = sharedPreferences.getString("appearance_theme", "casino")

        updateColorScheme(theme)

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

    private fun updateColorScheme(theme: String?) {

        val backgroundLayout = findViewById<ConstraintLayout>(R.id.backgroundLayout)

        val dice1ImageView = findViewById<ImageView>(R.id.dice1ImageView)
        val dice2ImageView = findViewById<ImageView>(R.id.dice2ImageView)
        val dice3ImageView = findViewById<ImageView>(R.id.dice3ImageView)
        val dice4ImageView = findViewById<ImageView>(R.id.dice4ImageView)

        when (theme) {
            "casino" -> {
                backgroundLayout.setBackgroundColor(ContextCompat.getColor(this.applicationContext, R.color.casinoBackground))
                dice1ImageView.setImageResource(R.drawable.dice_red)
                dice2ImageView.setImageResource(R.drawable.dice_red)
                dice3ImageView.setImageResource(R.drawable.dice_red)
                dice4ImageView.setImageResource(R.drawable.dice_red)
                updateTextColor(ContextCompat.getColor(this.applicationContext, R.color.casinoText))
            }
            "light" -> {
                backgroundLayout.setBackgroundColor(ContextCompat.getColor(this.applicationContext, R.color.lightBackground))
                dice1ImageView.setImageResource(R.drawable.dice)
                dice2ImageView.setImageResource(R.drawable.dice)
                dice3ImageView.setImageResource(R.drawable.dice)
                dice4ImageView.setImageResource(R.drawable.dice)
                updateTextColor(ContextCompat.getColor(this.applicationContext, R.color.lightText))
            }
            "dark" -> {
                backgroundLayout.setBackgroundColor(ContextCompat.getColor(this.applicationContext, R.color.darkBackground))
                dice1ImageView.setImageResource(R.drawable.dice)
                dice2ImageView.setImageResource(R.drawable.dice)
                dice3ImageView.setImageResource(R.drawable.dice)
                dice4ImageView.setImageResource(R.drawable.dice)
                updateTextColor(ContextCompat.getColor(this.applicationContext, R.color.darkText))
            }
            else -> { // default to casino
                backgroundLayout.setBackgroundColor(ContextCompat.getColor(this.applicationContext, R.color.casinoBackground))
                dice1ImageView.setImageResource(R.drawable.dice_red)
                dice2ImageView.setImageResource(R.drawable.dice_red)
                dice3ImageView.setImageResource(R.drawable.dice_red)
                dice4ImageView.setImageResource(R.drawable.dice_red)
                updateTextColor(ContextCompat.getColor(this.applicationContext, R.color.casinoText))
            }
        }
    }

    private fun updateTextColor(textColor: Int) {
        val dice1TextView = findViewById<TextView>(R.id.dice1TextView)
        val dice2TextView = findViewById<TextView>(R.id.dice2TextView)
        val dice3TextView = findViewById<TextView>(R.id.dice3TextView)
        val dice4TextView = findViewById<TextView>(R.id.dice4TextView)

        val dice1DescriptionTextView = findViewById<TextView>(R.id.dice1DescriptionTextView)
        val dice2DescriptionTextView = findViewById<TextView>(R.id.dice2DescriptionTextView)
        val dice3DescriptionTextView = findViewById<TextView>(R.id.dice3DescriptionTextView)
        val dice4DescriptionTextView = findViewById<TextView>(R.id.dice4DescriptionTextView)

        val totalTextView = findViewById<TextView>(R.id.TotalTextView)

        dice1TextView.setTextColor(textColor)
        dice2TextView.setTextColor(textColor)
        dice3TextView.setTextColor(textColor)
        dice4TextView.setTextColor(textColor)

        dice1DescriptionTextView.setTextColor(textColor)
        dice2DescriptionTextView.setTextColor(textColor)
        dice3DescriptionTextView.setTextColor(textColor)
        dice4DescriptionTextView.setTextColor(textColor)

        totalTextView.setTextColor(textColor)
    }
}
