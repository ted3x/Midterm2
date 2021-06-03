package ge.c0d3in3.midterm2.presentation.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ge.c0d3in3.midterm2.App
import ge.c0d3in3.midterm2.R
import ge.c0d3in3.midterm2.presentation.users.model.User
import ge.c0d3in3.midterm2.network.ApiService
import ge.c0d3in3.midterm2.network.go
import ge.c0d3in3.midterm2.presentation.todos.TodosActivity
import ge.c0d3in3.midterm2.presentation.users.adapter.UsersAdapter

class UsersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var usersCount: TextView
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        recyclerView = findViewById(R.id.usersRV)
        swipeLayout = findViewById(R.id.usersSwipeLayout)
        usersCount = findViewById(R.id.usersCountTv)

        setUpRecyclerView()
        setSwipeLayout()
    }

    private fun setUpRecyclerView() {
        adapter = UsersAdapter {
            val intent = Intent(this, TodosActivity::class.java)
            intent.putExtra(USER_KEY, it)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        getUsers()
    }

    private fun setSwipeLayout() {
        swipeLayout.setOnRefreshListener {
            getUsersApi()
        }
    }

    private fun getUsers() {
        val users = App.roomDatabase.userDao().getAll()
        if(users.isEmpty())
            getUsersApi()
        else
            displayUsers(users)
    }

    private fun getUsersApi() {
        ApiService.get().getUsers().go(
            onSuccess = {
                insertUsers(it)
                displayUsers(it)
            },
            onFail = {
                Toast.makeText(baseContext, "ვერ წამოვიღეთ იუზერები", Toast.LENGTH_SHORT).show()
                if(swipeLayout.isRefreshing) swipeLayout.isRefreshing = false
            }
        )
    }

    private fun displayUsers(users: List<User>) {
        usersCount.text = "სულ: ${users.size} მომხმარებელი"
        adapter.setAdapterItems(users)
        if(swipeLayout.isRefreshing) swipeLayout.isRefreshing = false
    }

    private fun insertUsers(users:List<User>) {
        App.roomDatabase.userDao().clear()
        App.roomDatabase.userDao().insert(users)
    }

    companion object {
        const val USER_KEY = "user"
    }
}