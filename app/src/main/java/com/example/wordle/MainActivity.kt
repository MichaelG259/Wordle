package com.example.wordle

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wordle.ui.theme.WordleTheme

var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
var guessCounter = 0
class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.layout)

        val button = findViewById<Button>(R.id.guessButton)
        val textField = findViewById<TextView>(R.id.enterGuess)
        val wordRevealLabel = findViewById<TextView>(R.id.revealLabel)
        val checkLabel1 = findViewById<TextView>(R.id.c1)
        val guessLabel1 = findViewById<TextView>(R.id.g1)
        val checkLabel2 = findViewById<TextView>(R.id.c2)
        val guessLabel2 = findViewById<TextView>(R.id.g2)
        val checkLabel3 = findViewById<TextView>(R.id.c3)
        val guessLabel3 = findViewById<TextView>(R.id.g3)
        button.setOnClickListener {
            guessCounter++
            //val labelID = resources.getIdentifier("c$guessCounter","id", "TextView")
            //val curLabel = findViewById<TextView>(labelID)
            //above doesn't work. someone more experienced in android dev could likely get it to work. going to use if statements instead.
            var checkResult = "XXXX"
            if(guessCounter == 0){
                //reset button pressed
                wordRevealLabel.text = ""
                wordToGuess = FourLetterWordList.getRandomFourLetterWord()
                button.text = "GUESS"

                checkLabel1.text = ""
                guessLabel1.text = ""
                checkLabel2.text = ""
                guessLabel2.text = ""
                checkLabel3.text = ""
                guessLabel3.text = ""
            }
            if(guessCounter == 1){
                guessLabel1.text = textField.text
                checkResult = checkGuess(textField.text.toString())
                checkLabel1.text = checkResult
            }
            else if(guessCounter == 2) {
                guessLabel2.text = textField.text
                checkResult = checkGuess(textField.text.toString())
                checkLabel2.text = checkResult
            }
            else if(guessCounter == 3){
                guessLabel3.text = textField.text
                checkResult = checkGuess(textField.text.toString())
                checkLabel3.text = checkResult
                guessCounter = -1
            }
            if(checkResult == "OOOO" || guessCounter == -1)
            {
                guessCounter = -1
                wordRevealLabel.text = wordToGuess
                button.text = "RESET"
            }
        }
    }
}

private fun checkGuess(guess: String) : String {
    var result = ""
    for (i in 0..3) {
        if (guess[i] == wordToGuess[i]) {
            result += "O"
        }
        else if (guess[i] in wordToGuess) {
            result += "+"
        }
        else {
            result += "X"
        }
    }
    return result
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordleTheme {
        Greeting("Android")
    }
}