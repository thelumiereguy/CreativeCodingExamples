package dev.thelumiereguy.creative_coding_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import dev.thelumiereguy.creative_coding_compose.examples.cubic_sphere.CubicSphere
import dev.thelumiereguy.creative_coding_compose.examples.stacker.Stacker
import dev.thelumiereguy.creative_coding_compose.theme.CreativeCodingComposeTheme
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@OptIn(ExperimentalComposeUiApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreativeCodingComposeTheme {
                Stacker(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize(),
                    repetitions = 20,
                    offsetProvider = { index ->
                        IntOffset(
                            cos(index.toFloat()).roundToInt() * 15,
                            sin(index.toFloat()).roundToInt() * 15
                        )
                    }
                ) { index, modifier ->
                    CubicSphere(
                        modifier = modifier,
                    )
                }
            }
        }
    }
}