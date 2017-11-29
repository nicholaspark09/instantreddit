package com.example.vn008xw.myapplication.data

import io.reactivex.functions.Consumer

abstract class NetworkObserver<T> : Consumer<NetworkResource<T>> {

  override fun accept(t: NetworkResource<T>?) {
    when (t) {
      is NetworkResource.Success -> onSuccess(t)
      is NetworkResource.Error -> onError(t)
      is NetworkResource.Loading -> onLoading()
      is NetworkResource.Idle -> onIdle()
      else -> IllegalStateException("Improper value")
    }
  }

  abstract fun onSuccess(result: NetworkResource.Success<T>)
  abstract fun onError(result: NetworkResource.Error<T>)
  abstract fun onLoading()
  abstract fun onIdle()
}