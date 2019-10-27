package com.egci428.a10503

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_item.view.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataProvider.getData()
        val cityArrayAdapter = CityArrayAdapter(this, data)
        list.setAdapter(cityArrayAdapter)
        list.setOnItemClickListener { adapterView, view, position, l ->
            val city = data[position]
            displayDetail(city)
        }

        addBtn.setOnClickListener {
            DataProvider.addCity()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun displayDetail(city: City){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("name", city.name)
        intent.putExtra("description", city.description)
        intent.putExtra("part", city.part)
        intent.putExtra("image", city.image)
        startActivity(intent)
    }

    private class CityArrayAdapter(var context: Context, var objects: ArrayList<City>): BaseAdapter(){
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val city = objects[position]
            val view: View

            if(convertView==null){
                val layoutInflater = LayoutInflater.from(parent!!.context)
                view = layoutInflater.inflate(R.layout.city_item, parent, false)
                val viewHolder = ViewHolder(view.nameText, view.imageCity, view.partText, view.cLayout)
                view.tag = viewHolder

            } else {
                view = convertView
            }

            val viewHolder = view.tag as ViewHolder
            viewHolder.nameText.text = city.name
            viewHolder.partText.text = city.part
            val imgName = city.image
            val res = context.resources.getIdentifier(imgName,"drawable",context.packageName)
            viewHolder.imageCity.setImageResource(res)

            return view
        }
        private class ViewHolder(val nameText:TextView, val imageCity:ImageView, val partText:TextView, val cL: ConstraintLayout)

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return objects.size
        }

    }
}
