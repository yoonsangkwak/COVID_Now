package site.yoonsang.covidnow.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import site.yoonsang.covidnow.databinding.ItemClinicBinding
import site.yoonsang.covidnow.model.Document

class LocationAdapter: PagingDataAdapter<Document, RecyclerView.ViewHolder>(DiffCallback) {

    companion object DiffCallback: DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemClinicBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(document: Document) {
            binding.document = document
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemClinicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as ViewHolder).bind(item)
        }
    }
}