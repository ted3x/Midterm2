package ge.c0d3in3.midterm2.presentation.todos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ge.c0d3in3.midterm2.R
import ge.c0d3in3.midterm2.network.ApiService
import ge.c0d3in3.midterm2.network.go
import ge.c0d3in3.midterm2.presentation.todos.adapter.TodosAdapter
import ge.c0d3in3.midterm2.presentation.users.UsersActivity.Companion.USER_KEY
import ge.c0d3in3.midterm2.presentation.users.model.User

class TodosActivity : AppCompatActivity() {

    private lateinit var user: User
    private lateinit var userLayout: ConstraintLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var todosCount: TextView
    private lateinit var adapter: TodosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos)

        user = intent.getParcelableExtra(USER_KEY)!!

        userLayout = findViewById(R.id.userLayout)
        recyclerView = findViewById(R.id.todosRv)
        todosCount = findViewById(R.id.todosCountTv)

        setUserUI()


        setUpRecyclerView()

    }

    private fun setUserUI() {
        userLayout.findViewById<TextView>(R.id.userFullNameTv).text = user.name
        userLayout.findViewById<TextView>(R.id.userEmailTv).text = user.email
        userLayout.findViewById<TextView>(R.id.userPhoneTv).text = user.phone.split(" ")[0]
    }
    private fun setUpRecyclerView() {
        adapter = TodosAdapter()
        recyclerView.adapter = adapter
        getTodos()
    }

    private fun getTodos() {
        ApiService.get().getTodos(user.id).go(
            onSuccess = {
                adapter.setAdapterItems(it)
                todosCount.text = "სულ: ${it.size}"
            },
            onFail = {
                Toast.makeText(baseContext, "ვერ წამოვიღეთ todos", Toast.LENGTH_SHORT).show()
            }
        )
    }
}