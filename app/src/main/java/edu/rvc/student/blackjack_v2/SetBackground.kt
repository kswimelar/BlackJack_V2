package edu.rvc.student.blackjack_v2

import android.R
import android.os.Bundle
import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.ImageView


class SetBackground : Activity() {


    internal var iv1: ImageView? = null
    internal var iv2: ImageView? = null
    internal var iv3: ImageView? = null
    internal var iv4: ImageView? = null
    internal var iv5: ImageView? = null
    internal var iv6: ImageView? = null
    internal var iv7: ImageView? = null
    internal var iv8: ImageView? = null
    internal var iv9: ImageView? = null
    internal var iv10: ImageView? = null
    internal var iv11: ImageView? = null


    /** Called when the activity is first created.  */

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.set_background)

        setupVariables()

        // TODO Auto-generated method stub

    }


    private fun setupVariables() {


        iv1 = findViewById<View>(R.id.imageViewbg1) as ImageView

        iv2 = findViewById<View>(R.id.imageViewbg2) as ImageView

        iv3 = findViewById<View>(R.id.imageViewbg3) as ImageView

        iv4 = findViewById<View>(R.id.imageViewbg4) as ImageView


        iv5 = findViewById<View>(R.id.imageViewbg5) as ImageView

        iv6 = findViewById<View>(R.id.imageViewbg6) as ImageView

        iv7 = findViewById<View>(R.id.imageViewbg7) as ImageView

        iv8 = findViewById<View>(R.id.imageViewbg8) as ImageView


        iv9 = findViewById<View>(R.id.imageViewbg9) as ImageView

        iv10 = findViewById<View>(R.id.imageViewbg10) as ImageView

        iv11 = findViewById<View>(R.id.imageViewbg11) as ImageView


        iv1.setOnClickListener(this)

        iv2.setOnClickListener(this)

        iv3.setOnClickListener(this)

        iv4.setOnClickListener(this)


        iv5.setOnClickListener(this)

        iv6.setOnClickListener(this)

        iv7.setOnClickListener(this)

        iv8.setOnClickListener(this)


        iv9.setOnClickListener(this)

        iv10.setOnClickListener(this)

        iv11.setOnClickListener(this)


    }


    internal fun setTextColors(red: Int, green: Int, blue: Int) {

        // Text colors

        MainActivity.tvBet.setTextColor(Color.rgb(red, green, blue))

        MainActivity.tvBet.setTextColor(Color.rgb(red, green, blue))

        MainActivity.tvDealerScore.setTextColor(
            Color

                .rgb(red, green, blue)
        )

        MainActivity.tvHighestScore.setTextColor(
            Color.rgb(
                red, green,

                blue
            )
        )
        MainActivity.tvMoney.setTextColor(Color.rgb(red, green, blue))

        MainActivity.tvYourScore.setTextColor(Color.rgb(red, green, blue))


    }


    fun onClick(v: View) {

        // TODO Auto-generated method stub
        when (v.getId()) {

            R.id.imageViewbg1 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back1)

                setTextColors(255, 255, 255)

                finish()
            }

            R.id.imageViewbg2 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back2)

                setTextColors(255, 255, 255)

                finish()
            }

            R.id.imageViewbg3 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back3)

                setTextColors(222, 14, 30)

                finish()
            }


            R.id.imageViewbg4 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back4)

                setTextColors(0, 0, 0)

                finish()
            }


            R.id.imageViewbg5 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back5)

                setTextColors(12, 45, 52)

                finish()
            }


            R.id.imageViewbg6 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back6)

                setTextColors(0, 0, 0)

                finish()
            }

            R.id.imageViewbg7 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back7)

                setTextColors(145, 0, 0)

                finish()
            }

            R.id.imageViewbg8 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back8)

                setTextColors(0, 0, 150)

                finish()
            }

            R.id.imageViewbg9 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back9)

                setTextColors(255, 255, 255)

                finish()
            }

            R.id.imageViewbg10 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back10)

                setTextColors(255, 255, 255)

                finish()
            }

            R.id.imageViewbg11 -> {


                MainActivity.layout.setBackgroundResource(R.drawable.back11)

                setTextColors(255, 255, 255)

                finish()
            }


            else -> {
            }
        }


    }


}