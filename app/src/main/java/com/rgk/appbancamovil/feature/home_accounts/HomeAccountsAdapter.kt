package com.rgk.appbancamovil.feature.home_accounts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rgk.appbancamovil.databinding.ItemAccountsBinding
import com.rgk.appbancamovil.databinding.ItemErrorBinding
import com.rgk.appbancamovil.domain.model.DataAccount
import com.rgk.appbancamovil.util.FormatMoneyUtil
import com.rgk.appbancamovil.util.extensions.basicDiffUtil
import com.rgk.appbancamovil.util.extensions.setSafeOnClickListener

class HomeAccountsAdapter(private val listener: (DataAccount) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ACCOUNT = 0
        private const val VIEW_TYPE_ERROR = 1
    }

    var mData: List<Any> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.hashCode() == new.hashCode() }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_ACCOUNT -> {
                val binding = ItemAccountsBinding.inflate(inflater, parent, false)
                ViewHolder(binding)
            }
            VIEW_TYPE_ERROR -> {
                val binding = ItemErrorBinding.inflate(inflater, parent, false)
                ViewHolderError(binding)
            }
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val dataAccount = mData[position] as DataAccount
                with(holder.binding) {
                    tvAmount.text = FormatMoneyUtil.formatMoney(dataAccount.balanceAmount)
                    tvMoney.text = dataAccount.currency
                    tvNameAccount.text = dataAccount.account
                    itemAccount.setSafeOnClickListener { listener(dataAccount) }
                }
            }
            is ViewHolderError -> {
                val errorText = mData[position] as String
                holder.binding.textView.text = errorText
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (mData[position]) {
            is DataAccount -> VIEW_TYPE_ACCOUNT
            is String -> VIEW_TYPE_ERROR
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }
    }

    fun updateItems(it: List<DataAccount>) {
        mData = it
        notifyDataSetChanged()
    }

    fun setDataError(data: String) {
        mData = listOf(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemAccountsBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ViewHolderError(val binding: ItemErrorBinding) :
        RecyclerView.ViewHolder(binding.root)
}
