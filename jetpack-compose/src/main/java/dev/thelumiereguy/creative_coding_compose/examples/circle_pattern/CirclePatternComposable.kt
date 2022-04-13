package dev.thelumiereguy.creative_coding_compose.examples.circle_pattern

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInteropFilter

@OptIn(ExperimentalComposeUiApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun CirclePatternComposable(modifier: Modifier = Modifier.fillMaxSize()) {

    var touchCoordinates by remember {
        mutableStateOf<Offset?>(null)
    }

    var shouldStartTracking by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier
            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        touchCoordinates = Offset(
                            event.x,
                            event.y
                        )
                        shouldStartTracking = true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        touchCoordinates = Offset(
                            event.x,
                            event.y
                        )
                    }
                    MotionEvent.ACTION_UP -> {
                        shouldStartTracking = false
                    }
                    else -> false
                }
                true
            },
        color = MaterialTheme.colors.background
    ) {
        AnimatedVisibility(
            visible = shouldStartTracking,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(animationSpec = tween(3000, delayMillis = 90)),
            exit = fadeOut(animationSpec = tween(1000))
        ) {
            touchCoordinates?.let { coords ->
                DrawCirclePatternComposable(coords.x, coords.y) {
                    touchCoordinates = null
                }
            }
        }
    }
}