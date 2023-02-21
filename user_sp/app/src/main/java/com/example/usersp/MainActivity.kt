package com.example.usersp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(getUsers())
        linearLayoutManager = LinearLayoutManager(this)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User> {
        val user = mutableListOf<User>()

        val alain = User(1, "Alain", "Nicolas", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/robert-downey-jr-iron-man-casting-1563435293.jpg")
        val samant = User(2, "Samanta", "Meza", "https://th-thumbnailer.cdn-si-edu.com/gnVVC1OLQs1cWyFWBciZSwXKon8=/1072x720/filters:no_upscale():focal(792x601:793x602)/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/52/e4/52e44474-c2dc-41e0-bb77-42a904695196/this-image-shows-a-portrait-of-dragon-man-credit-chuang-zhao_web.jpg")
        val person1 = User(3, "Jehidi", "Chavez", "https://discoverymood.com/wp-content/uploads/2020/04/Mental-Strong-Women-min.jpg")
        val person2 = User(4, "Hugo", "Roca", "https://media.npr.org/assets/img/2022/11/08/ap22312071681283-0d9c328f69a7c7f15320e8750d6ea447532dff66.jpg")

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
}