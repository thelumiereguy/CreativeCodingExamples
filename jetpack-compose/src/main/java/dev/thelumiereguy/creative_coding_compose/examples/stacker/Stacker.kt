package dev.thelumiereguy.creative_coding_compose.examples.stacker

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex


private val defaultAlphaProvider = { index: Int ->
    1f / (index + 1)
}

private val defaultOffsetProvider = { index: Int ->
    IntOffset(index, index)
}

@Composable
fun Stacker(
    modifier: Modifier,
    repetitions: Int,
    alignment: Alignment = Alignment.TopStart,
    offsetProvider: (index: Int) -> IntOffset = defaultOffsetProvider,
    alphaProvider: (index: Int) -> Float = defaultAlphaProvider,
    content: @Composable (index: Int, modifier: Modifier) -> Unit
) {

    Box(
        modifier = modifier,
        contentAlignment = alignment
    ) {
        repeat(repetitions) { index ->
            content(
                index,
                Modifier.getModifier(
                    index,
                    repetitions,
                    offsetProvider,
                    alphaProvider
                )
            )
        }
    }
}

private fun Modifier.getModifier(
    index: Int,
    repetitions: Int,
    offsetProvider: (index: Int) -> IntOffset,
    alphaProvider: (index: Int) -> Float
) = then(
    offset { offsetProvider(index) }
        .zIndex(repetitions - index.toFloat())
        .alpha(alphaProvider(index))
)

@Preview
@Composable
fun ZIndexLayoutPreview() {
    Stacker(
        Modifier.fillMaxSize(),
        50,
    ) { index, modifier ->
        Text(text = "Hello there", modifier = modifier)
    }
}