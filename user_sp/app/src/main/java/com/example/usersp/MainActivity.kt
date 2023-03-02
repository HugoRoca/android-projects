package com.example.usersp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirstTime")

        if (isFirstTime) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_confirm) { _, _ ->

                }
                .create()

            dialog.show()

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val username =
                    dialogView.findViewById<TextInputEditText>(R.id.etUserName).text.toString()

                if (username.isBlank()) {
                    Toast.makeText(this, R.string.register_invalid, Toast.LENGTH_SHORT).show()
                } else {
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), username).apply()
                    }

                    Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show()

                    dialog.dismiss()
                }
            }
        } else {
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()
        }


        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }

        val swipeHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userAdapter.remove(viewHolder.adapterPosition)
            }
        })

        swipeHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun getUsers(): MutableList<User> {
        val user = mutableListOf<User>()

        val alain = User(1, "Alain", "Nicolas", "https://via.placeholder.com/150")
        val samant = User(2, "Samanta", "Smith", "https://via.placeholder.com/150")
        val person1 = User(3, "John", "Chavez", "https://via.placeholder.com/150")
        val person2 = User(4, "Hugo", "Roca", "https://via.placeholder.com/150")

        user.add(alain)
        user.add(samant)
        user.add(person1)
        user.add(person2)
        user.add(alain)
        user.add(samant)
        user.add(person1)
        user.add(person2)
        user.add(alain)
        user.add(samant)
        user.add(person1)
        user.add(person2)
        user.add(alain)
        user.add(samant)
        user.add(person1)
        user.add(person2)
        user.add(alain)
        user.add(samant)
        user.add(person1)
        user.add(person2)

        return user
    }

    override fun onClick(user: User, position: Int) {
        Toast.makeText(this, "$position: ${user.getFullName()}", Toast.LENGTH_SHORT).show()
    }
}