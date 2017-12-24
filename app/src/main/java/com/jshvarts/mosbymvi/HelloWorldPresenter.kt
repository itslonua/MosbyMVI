package com.jshvarts.mosbymvi

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.jshvarts.mosbymvi.domain.GetHelloWorldTextUseCase
import com.jshvarts.mosbymvi.domain.HelloWorldViewState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber

class HelloWorldPresenter : MviBasePresenter<HelloWorldView, HelloWorldViewState>() {
    override fun bindIntents() {
        val showHelloWorld: Observable<HelloWorldViewState> = intent(HelloWorldView::sayHelloWorldIntent)
                .doOnNext { Timber.d("Received sayHelloIntent") }
                .switchMap { GetHelloWorldTextUseCase.getHelloWorldText() }
                .doAfterNext { Timber.d("Received new state: " + it) }
                .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(showHelloWorld, HelloWorldView::render)
    }
}