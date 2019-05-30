package com.faro.cubosdesafio

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.faro.cubosdesafio.activitys.OpsOportunidadeActivity
import java.util.*

class ListaDasLojasAdapter(private val context: Context, private var list: List<ListaModel>,
                           private var baseList: List<ListaModel>) :
    RecyclerView.Adapter<ListaDasLojasAdapter.ViewHolder>(), Filterable {

    override fun getFilter(): Filter {
        return object : Filter() {
            var listShop = ArrayList<ListaModel>()

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val results = Filter.FilterResults()

                when {
                    charSequence == null || charSequence.isEmpty() -> {
                        results.values = baseList
                        results.count = baseList.size

                    }
                    else -> {

                        getBaseList().filterTo(listShop) {
                            it.titulo.toLowerCase(Locale.getDefault()).contains(charSequence)

                        }

                        results.count = listShop.size
                        results.values = listShop
                    }
                }

                return results
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                setList(list = filterResults?.values as List< ListaModel>)
                notifyDataSetChanged()
            }

        }
    }

    fun getBaseList(): List<ListaModel> = baseList

    fun setList(list: List<ListaModel>) {
        this.list = list
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var titleTextView: TextView? = null
        var thumbImageView: ImageView? = null
        var subtitleTextView: TextView? = null


        init {
            titleTextView = itemView.findViewById(R.id.title)
            subtitleTextView = itemView.findViewById(R.id.subtituloItem)
            thumbImageView = itemView.findViewById(R.id.foto)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false);
        val card = view.findViewById<CardView>(R.id.card_view) as CardView

        card.maxCardElevation = 2.0F;
        card.radius = 5.0F;
        return ViewHolder(view);
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val album: ListaModel = list.get(position)

        holder.titleTextView?.text = album.titulo

        holder.subtitleTextView?.text = album.subTitulo

        holder.thumbImageView?.setImageResource(album.imagem)

        holder.thumbImageView?.setOnClickListener {

            when (position) {
               0 ->  context.startActivity(Intent(context, OpsOportunidadeActivity::class.java))

            }

        }

    }


    override fun getItemCount(): Int = list.size


}