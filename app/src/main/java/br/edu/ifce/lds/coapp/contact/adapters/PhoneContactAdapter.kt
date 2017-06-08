package br.edu.ifce.lds.coapp.contact.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.R.id.tvName
import br.edu.ifce.lds.coapp.R.id.tvPhoneNumber
import br.edu.ifce.lds.coapp.R.layout.item_phone_contact
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.find

/**
 * Created by ellca on 07/06/2017.
 */

class PhoneContactAdapter(val mContactList: MutableList<ContactInfo>, val mCallback: OnClickPhoneCallback) : RecyclerView.Adapter<PhoneContactAdapter.PhoneContactViewHolder>() {


    var selectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): PhoneContactViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(item_phone_contact, parent, false)

        return PhoneContactViewHolder(v)
    }

    override fun onBindViewHolder(holder: PhoneContactViewHolder, position: Int) {

        holder.phoneNumber.text = mContactList[position].info
        holder.ownerName.text = mContactList[position].name

        if (position == selectedPos) {
            holder.itemView.backgroundResource = R.drawable.selected_item_contact_background
        } else {
            holder.itemView.backgroundResource = R.drawable.login_edit_text_background
        }

    }

    override fun getItemCount(): Int = mContactList.size


    inner class PhoneContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View?) {
            val pos = adapterPosition
            mCallback.onClickPhone(pos)
        }

        init {
            itemView.setOnClickListener(this)
        }

        val phoneNumber: TextView = itemView.find(tvPhoneNumber)
        val ownerName: TextView = itemView.find(tvName)


    }

    interface OnClickPhoneCallback {
        fun onClickPhone(pos: Int)
    }

    fun getSelectedPhone(): String {
        if (selectedPos >= 0)
            return mContactList[selectedPos].info
        else
            return ""
    }


}

