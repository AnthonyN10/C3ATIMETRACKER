package com.example.c3atimetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.c3atimetracker.databinding.ActivityCategoriesBinding
import com.google.firebase.database.*

class categories : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    open lateinit var database: DatabaseReference
    open lateinit var itemList: ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Categories")
        itemList = ArrayList()

        itemList = loadItemList()

        binding.addbutton.setOnClickListener {
            val item = binding.categorynametxt.text.toString().trim()
            if (item.isNotEmpty()) {
                itemList.add(item)
                binding.categorynametxt.text.clear()
                saveItemList(itemList)
            }
        }

        binding.viewbutton.setOnClickListener {
            val intent = Intent(this, ViewcategoriesaActivity::class.java)
            intent.putStringArrayListExtra("itemList", itemList)
            startActivity(intent)
        }
    }

    private fun saveItemList(itemList: ArrayList<String>) {
        val categoryRef = database.child("Categories").push()
        categoryRef.setValue(itemList).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Item list saved successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to save item list", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun loadItemList(): ArrayList<String> {
        val itemList = ArrayList<String>()
        database.child("this").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val categoryList =
                        childSnapshot.getValue(object : GenericTypeIndicator<List<String>>() {})
                    categoryList?.let { itemList.addAll(it) }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database error
            }
        })
        return itemList
    }
}