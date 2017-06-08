package br.edu.ifce.lds.coapp.contact.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.edu.ifce.lds.coapp.R.id.tvName
import br.edu.ifce.lds.coapp.R.id.tvPhoneNumber
import br.edu.ifce.lds.coapp.R.layout.item_phone_contact
import br.edu.ifce.lds.coapp.contact.entities.ContactInfo
import kotlinx.android.synthetic.main.item_phone_contact.tvName
import org.jetbrains.anko.find

/**
 * Created by ellca on 07/06/2017.
 */

class PhoneContactAdapter(val mContactList: MutableList<ContactInfo>) : RecyclerView.Adapter<PhoneContactViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): PhoneContactViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(item_phone_contact, p0, false)

        return PhoneContactViewHolder(v)
    }

    override fun onBindViewHolder(holder: PhoneContactViewHolder, position: Int) {
        holder.phoneNumber.text = mContactList[position].info
        holder.ownerName.text = mContactList[position].name
    }

    override fun getItemCount(): Int = mContactList.size

}

class PhoneContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val phoneNumber :TextView =  itemView.find(tvPhoneNumber)
    val ownerName : TextView = itemView.find(tvName)
}