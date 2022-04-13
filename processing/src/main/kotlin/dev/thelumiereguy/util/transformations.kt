package dev.thelumiereguy.util

import processing.core.PApplet

inline fun PApplet.rotate(angle: Float, block: () -> Unit) {
    pushMatrix()
    rotate(angle)
    block()
    popMatrix()
}