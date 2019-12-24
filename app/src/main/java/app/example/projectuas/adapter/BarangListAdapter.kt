package app.example.projectuas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.example.projectuas.R
import app.example.projectuas.model.Barang

class BarangListAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<BarangListAdapter.BarangViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var barangs = emptyList<Barang>() // Cached copy of barangs

    inner class BarangViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val barangNama: TextView = itemView.findViewById(R.id.tx_nama)
        val barangMerk: TextView = itemView.findViewById(R.id.tx_merk)
        val barangQty: TextView = itemView.findViewById(R.id.tx_Qty)
        val barangHarga: TextView = itemView.findViewById(R.id.tx_harga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarangViewHolder {
        val itemView = inflater.inflate(R.layout.item_list_barang, parent, false)
        return BarangViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BarangViewHolder, position: Int) {
        val current = barangs[position]
        holder.barangNama.text  = current.nama
        holder.barangMerk.text  = current.merek
        holder.barangQty.text   = current.qty.toString()
        holder.barangHarga.text = current.harga.toString()
    }

    internal fun setBarangs(words: List<Barang>) {
        this.barangs = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = barangs.size
}