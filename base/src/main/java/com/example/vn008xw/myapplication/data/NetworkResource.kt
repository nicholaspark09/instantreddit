package com.example.vn008xw.myapplication.data

sealed class NetworkResource<T> {
  data class Success<T>(val data: T? = null) : NetworkResource<T>()
  data class Error<T>(val data: T? = null,
                      val errorCode: Int? = 0,
                      val errorMessage: String? = null) : NetworkResource<T>()
  class Idle<T> : NetworkResource<T>()
  class Loading<T> : NetworkResource<T>()
}