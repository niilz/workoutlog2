package de.niilz.kmp.workoutlog2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform