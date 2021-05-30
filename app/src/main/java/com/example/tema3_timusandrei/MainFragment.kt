package com.example.tema3_timusandrei

import android.view.ContextThemeWrapper
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tema3_timusandrei.Adapters.BookAdapter
import com.example.tema3_timusandrei.Interfaces.OnBookItemClick
import com.example.tema3_timusandrei.Models.Book
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseError
import com.google.firebase.database.*

class MainFragment: Fragment() {
    private val books: ArrayList<Book> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val db: DatabaseReference = FirebaseDatabase.getInstance("https://bookdb-b4919-default-rtdb.firebaseio.com/").reference
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val title = view?.findViewById<TextInputEditText>(R.id.title)
        val author = view?.findViewById<TextInputEditText>(R.id.author)
        val details = view?.findViewById<TextInputEditText>(R.id.details)
        val addUpdate = view?.findViewById<Button>(R.id.add_update)
        addUpdate?.setOnClickListener {
            val titleString = title!!.text.toString()
            val authorString = author!!.text.toString()
            val detailsString = details!!.text.toString()
            if(titleString.isEmpty() || authorString.isEmpty() || detailsString.isEmpty()) {
                val builder = AlertDialog.Builder(ContextThemeWrapper(activity, R.style.AlertDialog_AppCompat))
                with(builder)
                {
                    setTitle("Application Alert")
                    setMessage("Title/Author/Details is empty.")
                    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
                        Toast.makeText(requireActivity().applicationContext,
                            android.R.string.yes, Toast.LENGTH_SHORT).show()
                    }
                    setPositiveButton("OK", DialogInterface.OnClickListener(positiveButtonClick))
                    show()
                }
            } else {
                var validate = true
                val book = Book(titleString, authorString, detailsString)
                for(it in books) {
                    if (it.title.equals(book.title) && it.author.equals(book.author)) {
                        validate = false
                    }
                }
                if(validate) {
                    db.child("Books").push().setValue(book)
                    addBookInRecyclerView(view, book)
                }
            }
        }
        setUpRecyclerView(view)
        return view
    }
    private fun setUpRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.book_list)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        val booksRef: DatabaseReference = FirebaseDatabase.getInstance("https://bookdb-b4919-default-rtdb.firebaseio.com/").reference.child("Books")
        recyclerView?.layoutManager = layoutManager
        booksRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val book = dataSnapshot.getValue(Book::class.java)!!
                    books.add(book)
                }
                val adapter = BookAdapter(books, OnBookItemClick { book -> changeFragment(book) })
                recyclerView?.adapter=adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun modifyRecyclerView(view: View, book: Book) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.book_list)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        val booksRef: DatabaseReference = FirebaseDatabase.getInstance("https://bookdb-b4919-default-rtdb.firebaseio.com/").reference.child("Books")
        recyclerView?.layoutManager = layoutManager
        booksRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val it = dataSnapshot.getValue(Book::class.java)!!
                    if(book.title.equals(it.title) && book.author.equals(it.author)) {

                    }
                }
                val adapter = BookAdapter(books, OnBookItemClick { book -> changeFragment(book) })
                recyclerView?.adapter=adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    private fun addBookInRecyclerView(view: View, book: Book) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.book_list)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        val booksRef: DatabaseReference = FirebaseDatabase.getInstance("https://bookdb-b4919-default-rtdb.firebaseio.com/").reference.child("Books")
        recyclerView?.layoutManager = layoutManager
        booksRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                books.add(book)
                val adapter = BookAdapter(books, OnBookItemClick { book -> changeFragment(book) })
                recyclerView?.adapter=adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    private fun changeFragment(book: Book) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.add(R.id.fragment, BookFragment(book))
        fragmentTransaction?.commit()
    }
}
