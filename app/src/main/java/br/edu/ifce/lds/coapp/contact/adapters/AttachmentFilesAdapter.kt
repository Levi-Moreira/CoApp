package br.edu.ifce.lds.coapp.contact.adapters

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.ifce.lds.coapp.R
import kotlinx.android.synthetic.main.file_list_item.view.*

/**
 * Created by ellca on 08/06/2017.
 */

class AttachmentFilesAdapter(val fileList: List<Uri>) : RecyclerView.Adapter<AttachmentFilesAdapter.AttachmentFileViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, p1: Int): AttachmentFileViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.file_list_item, parent, false)
        return AttachmentFileViewHolder(v)
    }

    override fun onBindViewHolder(holder: AttachmentFileViewHolder, position: Int) {
        if (position == fileList.size - 1){
            holder.icon.setImageResource(R.drawable.ic_add_file)
            holder.title.text = "Anexar\nDocumento"
        }
    }

    override fun getItemCount(): Int = fileList.size


    class AttachmentFileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon = itemView.fileIcon
        val title = itemView.fileName
    }
}