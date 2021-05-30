package com.example.tema3_timusandrei

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tema3_timusandrei.Models.Book

class BookFragment(book: Book): Fragment() {
    private val book = book
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_fragment, container, false)
        val bookTitleTextView: TextView = view.findViewById<TextView>(R.id.title_book_fragment)
        val bookAuthorTextView: TextView = view.findViewById<TextView>(R.id.author_book_fragment)
        val bookDetailsTextView: TextView = view.findViewById<TextView>(R.id.details_book_fragment)
        val backButton: Button = view.findViewById<Button>(R.id.back)
        bookTitleTextView.text = book.title
        bookAuthorTextView.text = book.author
        bookDetailsTextView.text = book.details
        backButton.setOnClickListener {
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.add(R.id.fragment, MainFragment())
            fragmentTransaction?.commit()
        }
        return view
    }
}