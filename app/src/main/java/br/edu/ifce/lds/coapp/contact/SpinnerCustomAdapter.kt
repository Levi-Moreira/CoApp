package br.edu.ifce.lds.coapp.contact

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckedTextView
import android.widget.SpinnerAdapter
import android.widget.TextView
import br.edu.ifce.lds.coapp.R
import org.jetbrains.anko.textColor

/**
 * Created by ellca on 07/06/2017.
 */

open class SpinnerCustomAdapter(val context: Context, val titles: MutableList<String>, val itemResouce: Int, val dropDownResource: Int) : BaseAdapter(), SpinnerAdapter {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(itemResouce, parent, false)
        val textView = v.findViewById(R.id.tv_item_spinner) as TextView
        textView.text = titles[position]

        if (position == 0) {
            textView.textColor = Color.parseColor("#55ffffff");
        }

        return v
    }

    override fun getItem(position: Int): Any = titles[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = titles.size

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = LayoutInflater.from(context).inflate(dropDownResource, parent, false)

        val textView = v.findViewById(R.id.tv_dropdown) as CheckedTextView
        textView.text = titles[position]

        if (position == 0) {
            textView.setTypeface(null, Typeface.BOLD)
        }

        return v
    }

}
