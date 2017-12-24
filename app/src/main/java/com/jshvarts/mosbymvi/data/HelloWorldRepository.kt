package com.jshvarts.mosbymvi.data

import com.jshvarts.mosbymvi.domain.HelloWorldViewState
import io.reactivex.Observable
import java.util.Random

/**
 * In a Production app, inject your Repository into your Use Case instead.
 */
object HelloWorldRepository {

    private val messages = listOf("Hello World", "Hola Mundo", "Hallo Welt", "Bonjour le monde")

    fun loadHelloWorldText(): Observable<HelloWorldViewState> {
        return Observable.just(HelloWorldViewState.DataState(getRandomMessage()))
                .map<HelloWorldViewState> { it }
                .startWith(HelloWorldViewState.LoadingState())
                .onErrorReturn { HelloWorldViewState.ErrorState(it) }
    }

    private fun getRandomMessage(): String {
        return messages[Random().nextInt(messages.size)]
    }
}