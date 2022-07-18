package dev.thelumiereguy.creative_coding_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import dev.thelumiereguy.creative_coding_compose.examples.animated_shapes.AnimatedShapes
import dev.thelumiereguy.creative_coding_compose.examples.stacker.Stacker
import dev.thelumiereguy.creative_coding_compose.theme.CreativeCodingComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreativeCodingComposeTheme {
                Stacker(
                    modifier = Modifier
                        .background(Color.Black)
                        .fillMaxSize(),
                    repetitions = 50,
                    offsetProvider = { index ->
                        IntOffset(index * 10, index * 30)
                    }
                ) { index, modifier ->
                    AnimatedShapes(modifier = modifier)
                }
            }
        }
    }
}