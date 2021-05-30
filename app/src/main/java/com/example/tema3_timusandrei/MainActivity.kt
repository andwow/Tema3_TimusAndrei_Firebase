package com.example.tema3_timusandrei

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tema3_timusandrei.Adapters.BookAdapter
import com.example.tema3_timusandrei.Interfaces.OnBookItemClick
import com.google.android.material.textfield.TextInputLayout
import com.example.tema3_timusandrei.Models.Book
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseError
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment()
    }
    private fun changeFragment() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment, MainFragment())
        fragmentTransaction.commit()
    }
}