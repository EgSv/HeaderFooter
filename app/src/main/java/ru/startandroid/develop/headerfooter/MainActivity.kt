package ru.startandroid.develop.headerfooter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.HeaderViewListAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.Objects

const val LOG_TAG = "myLogs"

class MainActivity : AppCompatActivity() {

    val data = arrayListOf("one", "two", "three", "four", "five")

    var lvMain:ListView? = null
    var adapter:ArrayAdapter<String>? = null

    var header1:View? = null
    var header2:View? = null

    var footer1:View? = null
    var footer2:View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvMain = findViewById(R.id.lvMain)
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)

        header1 = createHeader("header 1")
        header2 = createHeader("header 2")

        footer1 = createHeader("footer 1")
        footer2 = createHeader("footer 2")

        fillList()
    }

    fun fillList() {
        lvMain!!.addHeaderView(header1)
        lvMain!!.addHeaderView(header2, "some text for header 2", false)
        lvMain!!.addFooterView(footer1)
        lvMain!!.addFooterView(footer2, "some text for footer 2", false)
        lvMain!!.adapter = adapter
    }

    fun onClick(v:View?) {
        var obj: Any
        val hvlAdapter: HeaderViewListAdapter = lvMain!!.adapter as HeaderViewListAdapter
        obj = hvlAdapter.getItem(1)
        Log.d(LOG_TAG, "hvlAdapter.getItem(1) = $obj")
        obj = hvlAdapter.getItem(4)
        Log.d(LOG_TAG, "hvlAdapter.getItem(4) = $obj")

        val alAdapter = hvlAdapter.wrappedAdapter as ArrayAdapter<String>
        obj = alAdapter.getItem(1)!!
        Log.d(LOG_TAG, "alAdapter.getItem(1) = $obj")
        obj = alAdapter.getItem(4)!!
        Log.d(LOG_TAG, "alAdapter.getItem(4) = $obj")

    }

    fun createHeader(text: String): View {
        val v: View = layoutInflater.inflate(R.layout.header, null)
        (v.findViewById(R.id.tvText) as TextView).text = text
        return v
    }

    fun createFooter(text:String): View {
        val v: View = layoutInflater.inflate(R.layout.footer, null)
        (v.findViewById(R.id.tvText) as TextView).text = text
        return v
    }
}