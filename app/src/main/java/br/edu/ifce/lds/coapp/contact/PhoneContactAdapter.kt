package br.edu.ifce.lds.coapp.contact

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import kotlinx.android.synthetic.main.item_phone_contact.view.*

/**
 * Created by ellca on 07/06/2017.
 */

class PhoneContactAdapter(val mContactList: MutableList<ContactInfo>) : RecyclerView.Adapter<PhoneContactViewHolder>() {



    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): PhoneContactViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.item_phone_contact, p0, false)

        return PhoneContactViewHolder(v)
    }

    override fun onBindViewHolder(holder: PhoneContactViewHolder, position: Int) {
        holder.phoneNumber.text = mContactList[position].info
        holder.ownerName.text = mContactList[position].name
    }

    override fun getItemCount(): Int = mContactList.size

}

class PhoneContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val phoneNumber = itemView.tvPhoneNumber
    val ownerName = itemView.tvName
}