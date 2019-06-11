package com.aayushf.watchdog

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "http://192.168.4.1:9890/wifi"
        main_rv.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var resp = response.substringAfter('\'')
                resp = resp.substringBeforeLast('\'')
                main_text.text = "Response is: ${resp}"
                val list = resp.split("', '")
                Log.d("MainActivity", list[0])
                val ia = ItemAdapter<WiFiItem>()
                val fa = FastAdapter.with(ia)
                main_rv.adapter = fa
                var items = mutableListOf<WiFiItem>()
                list.forEach {
                    items.add(WiFiItem(it))
                }
                ia.add(items)

            },
            Response.ErrorListener { main_text.text = "That didn't work!" })
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            queue.add(stringRequest)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    class WiFiItem(var ssid:String = "Not Put"): AbstractItem<WiFiItem.ViewHolder>() {
        override val layoutRes: Int
            get() = R.layout.main_rv_card
        override val type: Int
            get() = 0
        override fun getViewHolder(v: View): WiFiItem.ViewHolder {
            return ViewHolder(v)
        }
        class ViewHolder(var iv: View) : FastAdapter.ViewHolder<WiFiItem>(iv) {
            override fun bindView(item: WiFiItem, payloads: MutableList<Any>) {
                iv.findViewById<TextView>(R.id.main_rv_card_text).text = item.ssid
            }

            override fun unbindView(item: WiFiItem) {

            }
        }
    }
}
