package com.example.dagger2

import dagger.Component

@Component
interface MagicBox {
    fun poke(app: MainActivity)
    fun poke(app: NextActivity)
}