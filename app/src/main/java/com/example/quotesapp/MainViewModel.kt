package com.example.quotesapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.io.InputStream

class MainViewModel(val context: Context): ViewModel() {

    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0
    init {
        quoteList = loadQuoteFormAssets()
    }

    private fun loadQuoteFormAssets(): Array<Quote> {

        val inputStream = context.assets.open("quotes.json")
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer,Charsets.UTF_8)
        val gson = Gson()
       return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote()= quoteList[index]

    fun nextQuote() = quoteList[++index]
    fun previousQuote() = quoteList[--index]



}