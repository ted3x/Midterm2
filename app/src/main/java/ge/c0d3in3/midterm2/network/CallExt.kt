package ge.c0d3in3.midterm2.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.go(onSuccess: (T) -> Unit, onFail: () -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            onFail.invoke()
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful)
                response.body()?.let { it -> onSuccess.invoke(it) }
            else
                onFail.invoke()
        }

    })
}