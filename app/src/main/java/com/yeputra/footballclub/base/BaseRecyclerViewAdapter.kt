package com.yeputra.footballclub.base

import android.support.v7.widget.RecyclerView
import android.widget.Filter
import android.widget.Filterable


/**
 * Created by yovi.putra
 *    on 14/Feb/2019 15:36
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface FilterResultListener <T>{
    fun onFilterResult(query: String?): MutableList<T>
}

abstract class BaseRecyclerViewAdapter <VH: RecyclerView.ViewHolder,T>(
        private var items : MutableList<T>
): RecyclerView.Adapter<VH>(), Filterable, FilterResultListener<T> {

    var isSearching: Boolean = false

    abstract fun onBindViewHolder(holder: VH, item: T, position: Int)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): Filter.FilterResults {
                isSearching = true
                val charString = constraint.toString()
                val filterResults = Filter.FilterResults()

                items = onFilterResult(charString)

                filterResults.values = items
                filterResults.count = itemCount
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
                @Suppress("UNCHECKED_CAST")
                items = results.values as MutableList<T>
                notifyDataSetChanged()
                isSearching = false
            }
        }
    }

    override fun onFilterResult(query: String?): MutableList<T> {
        return this.items
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int){
        onBindViewHolder(holder, this.items[position], position)
    }

    fun getItem(position: Int) : T? = items[position]

    fun addItem(item: T) {
        this.items.add(item)
        this.notifyDataSetChanged()
    }

    private fun appendItem(items: MutableList<T>){
        this.items.addAll(items)
        this.notifyDataSetChanged()
    }

    fun replaceItem(items: MutableList<T>){
        this.items.clear()
        this.appendItem(items)
        this.notifyDataSetChanged()
    }
}