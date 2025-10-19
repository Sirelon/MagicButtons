package com.sirelon.magicbuttons

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform