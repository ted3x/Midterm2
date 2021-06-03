package ge.c0d3in3.midterm2.network

import ge.c0d3in3.midterm2.presentation.todos.model.Todo
import ge.c0d3in3.midterm2.presentation.users.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

object ApiService: Service<ApiServices>(ApiServices::class.java)

interface ApiServices {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}/todos")
    fun getTodos(@Path("id") userId: Int): Call<List<Todo>>
}