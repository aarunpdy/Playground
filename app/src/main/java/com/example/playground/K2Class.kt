package com.example.playground

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @author arun
 * Created on 14,Dec 2024
 * https://medium.com/@omarsahl/kotlin-language-features-from-kotlinconf-2024-whats-new-and-what-s-next-a4668eae5e9d
 */

class K2Class {

    val title:StateFlow<String>
        field = MutableStateFlow<String>("")

    fun myFun() {
        title.value
    }



}