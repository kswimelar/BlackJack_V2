package edu.rvc.student.blackjack_v2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var Money:String TextView
    var DealerScore:String TextView
    var YourScore:String TextView
    var Bet:String TextView

    private var DealerCard1: ImageSwitcher? = null
    private var DealerCard2: ImageSwitcher? = null
    private var DealerCard3: ImageSwitcher? = null
    private var DealerCard4: ImageSwitcher? = null
    private var DealerCard5: ImageSwitcher? = null
    private var DealerCard6: ImageSwitcher? = null

    private var YourCard1: ImageSwitcher? = null
    private var YourCard2: ImageSwitcher? = null
    private var YourCard3: ImageSwitcher? = null
    private var YourCard4: ImageSwitcher? = null
    private var YourCard5: ImageSwitcher? = null
    private var YourCard6: ImageSwitcher? = null

    var PlaceBet = findViewById<Button>(R.id.PlaceBet) as Button
    var Hit = findViewById<Button>(R.id.Hit) as Button
    var Stand = findViewById<Button>(R.id.Stand) as Button
    var Surrender = findViewById<Button>(R.id.Surrender) as Button
    var BetAmount:Int //SeekBar
    var bet = 0
    var dealerScore = 0
    var playerScore = 0
    var dealerCardNumber = 0
    var playerCardNumber = 0
    var randomNumber: Int = 0
    var dealerNumber: Int = 0

    var buyin = findViewById<TextView>(R.id.Buyin) as TextView
    var editBuyin = findViewById<EditText>(R.id.EditBuyin) as EditText

    //used to make sure no card comes twice
    internal var CardsTracking = ArrayList<Int>()

    //dealer and Player Aces Check
    internal var dealerCardArray = charArrayOf('0', '0', '0', '0', '0', '0')
    internal var playerCardArray = charArrayOf('0', '0', '0', '0', '0', '0')

    //dealer and Player Score Count
    internal var dealerScoreCount = intArrayOf(0, 0, 0, 0, 0, 0)
    internal var playerScoreCount = intArrayOf(0, 0, 0, 0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupVariables()

        imageSwitcher()

        //starting values
        var money = 0
        Money.text = " $ $money"
        BetAmount.max = money
        hidePlayButtons()

        val b = findViewById<Button>(R.id.Help) as Button

        b.setOnClickListener(object : View.OnClickListener {
            fun onClick(v: View) {

                val instruct = Intent(this@BlackJack, Pop::class.java)

                startActivity(instruct)
            }
        })

        var buying = findViewById<Button>(R.id.btnBuyin)
        //var EditBuyin = findViewById(R.id.EditBuyin) as EditText

        buying.setOnClickListener(
            object : View.OnClickListener {
                fun onClick(view: View) {
                    Log.v("EditText", editBuyin.text.toString())

                    val buyinA = editBuyin.text.toString()

                    updateBank(buyinA)

                    editBuyin.setText("")

                    editBuyin.clearFocus()

                    val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    mgr.hideSoftInputFromWindow(EditBuyin.windowToken, 0)
                }

            })
    }

        fun imageSwitcher() {

            DealerCard1!!.setFactory(this)
            DealerCard2!!.setFactory(this)
            DealerCard3!!.setFactory(this)
            DealerCard4!!.setFactory(this)
            DealerCard5!!.setFactory(this)
            DealerCard6!!.setFactory(this)

            YourCard1!!.setFactory(this)
            YourCard2!!.setFactory(this)
            YourCard3!!.setFactory(this)
            YourCard4!!.setFactory(this)
            YourCard5!!.setFactory(this)
            YourCard6!!.setFactory(this)

            DealerCard1!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            DealerCard1!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            DealerCard2!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            DealerCard2!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            DealerCard3!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            DealerCard3!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            DealerCard4!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            DealerCard4!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            DealerCard5!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            DealerCard5!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            DealerCard6!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            DealerCard6!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            YourCard1!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            YourCard1!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            YourCard2!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            YourCard2!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            YourCard3!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            YourCard3!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            YourCard4!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            YourCard4!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            YourCard5!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            YourCard5!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )

            YourCard6!!.inAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_in_left
            )
            YourCard6!!.outAnimation = AnimationUtils.loadAnimation(
                this,
                android.R.anim.slide_out_right
            )
        }

    fun resetEveryThing() {

        DealerCard1!!.setImageResource(R.drawable.square)
        DealerCard2!!.setImageResource(android.R.drawable.square)
        DealerCard3!!.setImageResource(android.R.drawable.square)
        DealerCard4!!.setImageResource(android.R.drawable.square)
        DealerCard5!!.setImageResource(android.R.drawable.square)
        DealerCard6!!.setImageResource(android.R.drawable.square)

        YourCard1!!.setImageResource(android.R.drawable.square)
        YourCard2!!.setImageResource(android.R.drawable.square)
        YourCard3!!.setImageResource(android.R.drawable.square)
        YourCard4!!.setImageResource(android.R.drawable.square)
        YourCard5!!.setImageResource(android.R.drawable.square)
        YourCard6!!.setImageResource(android.R.drawable.square)

        PlaceBet.setVisibility(View.VISIBLE)
        hidePlayButtons()
        dealerCardNumber = 0
        playerCardNumber = 0
        dealerScore = 0
        playerScore = 0
        CardsTracking.clear()

        for (i in 0..5) {
            dealerCardArray[i] = '0'
            dealerScoreCount[i] = 0
            playerCardArray[i] = '0'
            playerScoreCount[i] = 0
        }

        //set max bet to bankroll amount
        BetAmount.max = money

        showTextViews()

        //player is broke
        if (money <= 0) {
            Toast.makeText(
                this@BlackJack, "Add more money to play!",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    //setup variables
    fun setupVariables() {

        layout = findViewById(R.id.parentLayout) as LinearLayout

        Money = findViewById(R.id.Money) as TextView
        DealerScore = findViewById(R.id.Dealer) as TextView
        YourScore = findViewById(R.id.You) as TextView
        Bet = findViewById(R.id.Bet) as TextView

        DealerCard1 = findViewById(R.id.DealerCard1) as ImageSwitcher
        DealerCard2 = findViewById(R.id.DealerCard2) as ImageSwitcher
        DealerCard3 = findViewById(R.id.DealerCard3) as ImageSwitcher
        DealerCard4 = findViewById(R.id.DealerCard4) as ImageSwitcher
        DealerCard5 = findViewById(R.id.DealerCard5) as ImageSwitcher
        DealerCard6 = findViewById(R.id.DealerCard6) as ImageSwitcher


        YourCard1 = findViewById(R.id.YourCard1) as ImageSwitcher
        YourCard2 = findViewById(R.id.YourCard2) as ImageSwitcher
        YourCard3 = findViewById(R.id.YourCard3) as ImageSwitcher
        YourCard4 = findViewById(R.id.YourCard4) as ImageSwitcher
        YourCard5 = findViewById(R.id.YourCard5) as ImageSwitcher
        YourCard6 = findViewById(R.id.YourCard6) as ImageSwitcher

        //Hit = findViewById<Button>(R.id.Hit)
        //Stand = findViewById<Button>(R.id.Stand)
        //Surrender = findViewById<Button>(R.id.Surrender)

        Hit.setOnClickListener(this)
        Stand.setOnClickListener(this)
        Surrender.setOnClickListener(this)

        //PlaceBet = findViewById(R.id.PlaceBet) as Button
        PlaceBet.setOnClickListener(this)
        val Exit = findViewById<Button>(R.id.Exit)
        Exit.setOnClickListener(this)
        val Help = findViewById<Button>(R.id.Help)
        Help.setOnClickListener(this)

        //BetAmount = findViewById(R.id.BetAmount) as SeekBar
        BetAmount.setOnSeekBarChangeListener(this)
    }
    fun onClick(v: View) {

        if (playerCardNumber > 5 || dealerCardNumber > 5) {
            return
        }

        when (v.getId()) {

            R.id.PlaceBet ->

                if (bet > 0) {
                    money -= bet

                    showPlayButtons()
                    PlaceBet.setVisibility(View.INVISIBLE)
                    gameStart()
                } else {
                    Toast.makeText(
                        this@BlackJack, "Bet Some Money First",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            //Hit
            R.id.Hit ->

                HitClick()

            //Stand
            R.id.Stand ->

                StandClick()

            R.id.Surrender -> {

                //Surrender
                money += bet / 2
                Toast.makeText(
                    this@BlackJack,
                    "You Surrendered, You got half of your money back.",
                    Toast.LENGTH_SHORT
                ).show()
                resetEveryThing()
            }

            android.R.id.Exit ->

                finish()
        }

        showTextViews()
    }

    private fun gameStart() {

        //opening card of dealer
        dealerCall()
        calculateDealerScore()

        //opening 1st card of player
        playerCall()

        //opening 2nd card of Player
        playerCall()
        calculatePlayerScore()

        DealerCard2!!.setImageResource(android.R.drawable.default_blue)

        //check for BlackJack
        if (playerScore == 21) {
            blackJack()
        }
    }

    private fun showTextViews() {

        Money.text = " $ $money"
        Bet.text = "Bet - $ $bet"
        DealerScore.text = "Dealer's Score : $dealerScore"
        YourScore.text = "Your Score : $playerScore"

    }

    private fun hidePlayButtons() {

        Hit.setVisibility(View.INVISIBLE)
        Stand.setVisibility(View.INVISIBLE)
        Surrender.setVisibility(View.INVISIBLE)

        BetAmount.visibility = View.VISIBLE
    }

    private fun showPlayButtons() {

        Hit.setVisibility(View.VISIBLE)
        Stand.setVisibility(View.VISIBLE)
        Surrender.setVisibility(View.VISIBLE)

        BetAmount.visibility = View.INVISIBLE
    }

    fun cardsCalling(cardNumberFromRandom: Int, imageView: ImageSwitcher?): Char {

        //card number order: A,2,3,4,5,6,7,8,9,10,J,Q,K
        //card names: A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2
        //clubs
        when (cardNumberFromRandom) {
            0 -> {
                imageView!!.setImageResource(R.drawable.c1)
                return 'A'
            }
            1 -> {
                imageView!!.setImageResource(R.drawable.c2)
                return '2'
            }
            2 -> {
                imageView!!.setImageResource(R.drawable.c3)
                return '3'
            }
            3 -> {
                imageView!!.setImageResource(R.drawable.c4)
                return '4'
            }
            4 -> {
                imageView!!.setImageResource(R.drawable.c5)
                return '5'
            }
            5 -> {
                imageView!!.setImageResource(R.drawable.c6)
                return '6'
            }
            6 -> {
                imageView!!.setImageResource(R.drawable.c7)
                return '7'
            }
            7 -> {
                imageView!!.setImageResource(R.drawable.c8)
                return '8'
            }
            8 -> {
                imageView!!.setImageResource(R.drawable.c9)
                return '9'
            }
            9 -> {
                imageView!!.setImageResource(R.drawable.c10)
                return 'T'
            }
            10 -> {
                imageView!!.setImageResource(R.drawable.cj)
                return 'J'
            }
            11 -> {
                imageView!!.setImageResource(R.drawable.cq)
                return 'Q'
            }
            12 -> {
                imageView!!.setImageResource(R.drawable.ck)
                return 'K'
            }

            //diamonds
            13 -> {
                imageView!!.setImageResource(R.drawable.d1)
                return 'A'
            }
            14 -> {
                imageView!!.setImageResource(R.drawable.d2)
                return '2'
            }
            15 -> {
                imageView!!.setImageResource(R.drawable.d3)
                return '3'
            }
            16 -> {
                imageView!!.setImageResource(R.drawable.d4)
                return '4'
            }
            17 -> {
                imageView!!.setImageResource(R.drawable.d5)
                return '5'
            }
            18 -> {
                imageView!!.setImageResource(R.drawable.d6)
                return '6'
            }
            19 -> {
                imageView!!.setImageResource(R.drawable.d7)
                return '7'
            }
            20 -> {
                imageView!!.setImageResource(R.drawable.d8)
                return '8'
            }
            21 -> {
                imageView!!.setImageResource(R.drawable.d9)
                return '9'
            }
            22 -> {
                imageView!!.setImageResource(R.drawable.d10)
                return 'T'
            }
            23 -> {
                imageView!!.setImageResource(R.drawable.dj)
                return 'J'
            }
            24 -> {
                imageView!!.setImageResource(R.drawable.dq)
                return 'Q'
            }
            25 -> {
                imageView!!.setImageResource(R.drawable.dk)
                return 'K'
            }

            //hearts
            26 -> {
                imageView!!.setImageResource(R.drawable.h1)
                return 'A'
            }
            27 -> {
                imageView!!.setImageResource(R.drawable.h2)
                return '2'
            }
            28 -> {
                imageView!!.setImageResource(R.drawable.h3)
                return '3'
            }
            29 -> {
                imageView!!.setImageResource(R.drawable.h4)
                return '4'
            }
            30 -> {
                imageView!!.setImageResource(R.drawable.h5)
                return '5'
            }
            31 -> {
                imageView!!.setImageResource(R.drawable.h6)
                return '6'
            }
            32 -> {
                imageView!!.setImageResource(R.drawable.h7)
                return '7'
            }
            33 -> {
                imageView!!.setImageResource(R.drawable.h8)
                return '8'
            }
            34 -> {
                imageView!!.setImageResource(R.drawable.h9)
                return '9'
            }
            35 -> {
                imageView!!.setImageResource(R.drawable.h10)
                return 'T'
            }
            36 -> {
                imageView!!.setImageResource(R.drawable.hj)
                return 'J'
            }
            37 -> {
                imageView!!.setImageResource(R.drawable.hq)
                return 'Q'
            }
            38 -> {
                imageView!!.setImageResource(R.drawable.hk)
                return 'K'
            }

            //spades
            39 -> {
                imageView!!.setImageResource(R.drawable.s1)
                return 'A'
            }
            40 -> {
                imageView!!.setImageResource(R.drawable.s2)
                return '2'
            }
            41 -> {
                imageView!!.setImageResource(R.drawable.s3)
                return '3'
            }
            42 -> {
                imageView!!.setImageResource(R.drawable.s4)
                return '4'
            }
            43 -> {
                imageView!!.setImageResource(R.drawable.s5)
                return '5'
            }
            44 -> {
                imageView!!.setImageResource(R.drawable.s6)
                return '6'
            }
            45 -> {
                imageView!!.setImageResource(R.drawable.s7)
                return '7'
            }
            46 -> {
                imageView!!.setImageResource(R.drawable.s8)
                return '8'
            }
            47 -> {
                imageView!!.setImageResource(R.drawable.s9)
                return '9'
            }
            48 -> {
                imageView!!.setImageResource(R.drawable.s10)
                return 'T'
            }
            49 -> {
                imageView!!.setImageResource(R.drawable.sj)
                return 'J'
            }
            50 -> {
                imageView!!.setImageResource(R.drawable.sq)
                return 'Q'
            }
            51 -> {
                imageView!!.setImageResource(R.drawable.sk)
                return 'K'
            }

            else ->

                return 0.toChar()
        }

    }

    fun dealerCall() {

        //to make sure no card comes twice
        do {
            val _random = Random
            randomNumber = _random.nextInt(52)
        } while (CardsTracking.contains(randomNumber))
        CardsTracking.add(randomNumber)

        //deal cards for dealer
        when (dealerCardNumber) {
            0 -> dealerCardArray[dealerCardNumber] = cardsCalling(
                randomNumber,
                DealerCard1
            )
            1 -> dealerCardArray[dealerCardNumber] = cardsCalling(
                randomNumber,
                DealerCard2
            )
            2 -> dealerCardArray[dealerCardNumber] = cardsCalling(
                randomNumber,
                DealerCard3
            )
            3 -> dealerCardArray[dealerCardNumber] = cardsCalling(
                randomNumber,
                DealerCard4
            )
            4 -> dealerCardArray[dealerCardNumber] = cardsCalling(
                randomNumber,
                DealerCard5
            )
            5 -> dealerCardArray[dealerCardNumber] = cardsCalling(
                randomNumber,
                DealerCard6
            )
        }

        dealerNumber = randomNumber
        dealerScoreCount[dealerCardNumber] = getIntValueFromCard(dealerCardArray[dealerCardNumber])
        dealerCardNumber++

    }

    fun playerCall() {

        //to make sure no card comes twice
        do {
            val _random = Random
            randomNumber = _random.nextInt(52)
        } while (CardsTracking.contains(randomNumber))
        CardsTracking.add(randomNumber)

        //deal cards for player
        when (playerCardNumber) {
            0 -> playerCardArray[playerCardNumber] = cardsCalling(
                randomNumber,
                YourCard1
            )
            1 -> playerCardArray[playerCardNumber] = cardsCalling(
                randomNumber,
                YourCard2
            )
            2 -> playerCardArray[playerCardNumber] = cardsCalling(
                randomNumber,
                YourCard3
            )
            3 -> playerCardArray[playerCardNumber] = cardsCalling(
                randomNumber,
                YourCard4
            )
            4 -> playerCardArray[playerCardNumber] = cardsCalling(
                randomNumber,
                YourCard5
            )
            5 -> playerCardArray[playerCardNumber] = cardsCalling(
                randomNumber,
                YourCard6
            )
        }
        playerScoreCount[playerCardNumber] = getIntValueFromCard(playerCardArray[playerCardNumber])
        playerCardNumber++
    }

    fun calculateDealerScore() {

        var j = 0
        for (i in 0..5) {
            j += dealerScoreCount[i]
        }
        dealerScore = j
        DealerScore.text = "Dealer's Score : $dealerScore"
    }

    fun calculatePlayerScore() {

        var j = 0
        for (i in 0..5) {
            j += playerScoreCount[i]
        }
        playerScore = j
        YourScore.text = "Your Score : $playerScore"

    }

    fun youLose() {
        bet = 0
        Toast.makeText(
            this@BlackJack, "You Lose !!!",
            Toast.LENGTH_LONG
        ).show()

        alertBox()
    }

    fun youWon() {
        money += bet * 2

        bet = 0
        Toast.makeText(
            this@BlackJack, "You Won !!!",
            Toast.LENGTH_SHORT
        ).show()

        alertBox()
    }


    fun blackJack() {
        money += bet * 2 + bet / 2
        bet = 0
        Toast.makeText(
            this@BlackJack, "You Hit BLACKJACK! You win big !!!",
            Toast.LENGTH_LONG
        ).show()

        alertBox()
    }

    fun getIntValueFromCard(card: Char): Int {

        when (card) {
            'A' -> return 11
            'K' -> return 10
            'Q' -> return 10
            'J' -> return 10
            'T' -> return 10
            '9' -> return 9
            '8' -> return 8
            '7' -> return 7
            '6' -> return 6
            '5' -> return 5
            '4' -> return 4
            '3' -> return 3
            '2' -> return 2
            else -> return 0
        }
    }

    private fun HitClick() {

        playerCall()
        calculatePlayerScore()

        //aces count as 1
        if (playerScore > 21) {
            for (i in 0..5) {
                if (playerCardArray[i] == 'A' && playerScoreCount[i] == 11) {
                    playerScoreCount[i] = 1
                    Toast.makeText(
                        this@BlackJack,
                        "Aces will be count as 1", Toast.LENGTH_LONG
                    )
                        .show()
                    break
                }
            }
            calculatePlayerScore()
        }

        //if player has no aces
        if (playerScore > 21) {
            youLose()
            showTextViews()
        }

        if (playerCardNumber >= 6 && playerScore < 22) {

            StandClick()

        }

    }

    fun StandClick() {
        //prompts computers deal
        do {
            //opening 1st Card of Dealer
            dealerCall()
            calculateDealerScore()

            //aces count as 1
            if (dealerScore > 21) {
                for (i in 0..5) {
                    if (dealerCardArray[i] == 'A' && dealerScoreCount[i] == 11) {
                        dealerScoreCount[i] = 1
                        break
                    }
                }
                calculateDealerScore()
            }

        } while (dealerScore < 17 && dealerScore <= playerScore
            && dealerCardNumber < 6
        )

        if (dealerScore > 21) {
            youWon()
        } else {
            checkWin()
        }

        if (dealerCardNumber >= 6) {
            checkWin()
        }


    }

    fun checkWin() {
        if (dealerScore > playerScore) {
            youLose()

        } else if (playerScore > dealerScore) {
            youWon()
        } else if (playerScore == dealerScore) {
            money += bet
            alertBox()
            Toast.makeText(
                this@BlackJack, "Game Drawn!!!",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun alertBox() {

        val alert = Builder(this)
        alert.setCancelable(false)
        val message = "Play Again"
        val positive = "Next Hand"

        val t = object : Thread() {
            override fun run() {

                runOnUiThread {
                    alert.setMessage(message)
                    alert.setPositiveButton(
                        positive,
                        DialogInterface.OnClickListener { dialog, which ->
                            resetEveryThing()
                            hidePlayButtons()

                            val prefs = PreferenceManager
                                .getDefaultSharedPreferences(applicationContext)
                        })
                    alert.show()
                }
            }
        }
        t.start()

    }

    //use set bar to determine bet
    override fun onProgressChanged(
        seekBar: SeekBar, progress: Int,
        fromUser: Boolean
    ) {

        bet = progress
        Bet.text = "Bet - $ $bet"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {}

    fun makeView(): View {
        val iView = ImageView(this)
        iView.setScaleType(ImageView.ScaleType.FIT_CENTER)
        iView.setLayoutParams(
            ImageSwitcher.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT
            )
        )
        return iView
    }

    //allow/disallow adding of more money
    fun updateBank(buyinA: String) {
        calculatePlayerScore()

        if (!buyinA.isEmpty() && playerScore == 0) {

            val result = Integer.parseInt(buyinA)

            money = money + result

            Toast.makeText(
                this@BlackJack, "You added $$result",
                Toast.LENGTH_SHORT
            ).show()

            resetEveryThing()

        } else if (buyinA.isEmpty()) {
            Toast.makeText(
                this@BlackJack, "Enter amount first",
                Toast.LENGTH_SHORT
            ).show()
        } else if (playerScore != 0) {
            Toast.makeText(
                this@BlackJack, "Request Denied. Wait till end of deal to rebuy",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {

        var layout: LinearLayout

        //local variables
        internal var money = 0
    }
}
