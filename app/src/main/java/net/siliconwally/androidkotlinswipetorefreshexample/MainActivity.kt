package net.siliconwally.androidkotlinswipetorefreshexample

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mRandom: Random
    private lateinit var mHandler: Handler
    private lateinit var mRunnable:Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize a new Random instance
        mRandom = Random()

        // Initialize the handler instance
        mHandler = Handler()


        // Set an on refresh listener for swipe refresh layout
        swiperefresh.setOnRefreshListener {
            // Initialize a new Runnable
            mRunnable = Runnable {
                // Update the text view text with a random number
                message.text = "Refresh and get random number ${mRandom.nextInt(500)}"

                // Change the text view text color with a random color
                message.setTextColor(randomHSVColor())

                // Hide swipe to refresh icon animation
                swiperefresh.isRefreshing = false
            }

            // Execute the task after specified time
            mHandler.postDelayed(
                mRunnable,
                (randomInRange(1,5)*1000).toLong() // Delay 1 to 5 seconds
            )
        }
    }

    // Custom method to generate random HSV color
    fun randomHSVColor(): Int {
        // Generate a random hue value between 0 to 360
        val hue = mRandom.nextInt(361)
        // We make the color depth full
        val saturation = 1.0f
        // We make a full bright color
        val value = 1.0f
        // We avoid color transparency
        val alpha = 255
        // Finally, generate the color
        // Return the color
        return Color.HSVToColor(alpha, floatArrayOf(hue.toFloat(), saturation, value))
    }

    // Custom method to get a random number from the provided range
    private fun randomInRange(min:Int, max:Int):Int{
        // Define a new Random class
        val r = Random()

        // Get the next random number within range
        // Including both minimum and maximum number
        return r.nextInt((max - min) + 1) + min;
    }
}