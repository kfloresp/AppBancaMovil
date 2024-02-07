package com.rgk.appbancamovil.feature.accounts_detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rgk.appbancamovil.databinding.ItemMovementBinding
import com.rgk.appbancamovil.domain.model.DataMotion
import com.rgk.appbancamovil.util.FormatMoneyUtil
import com.rgk.appbancamovil.util.FormatString
import com.rgk.appbancamovil.util.extensions.basicDiffUtil

class AccountsDetailAdapter () :
    RecyclerView.Adapter<AccountsDetailAdapter.ViewHolder>() {
    var mData: List<DataMotion> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.operationNumber == new.operationNumber }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                with(mData[position]) {
                    tvName.text = this.operationDescription.toString()
                    tvDate.text = FormatString.parseAndFormatDate(this.operationDate.toString())
                    tvMoney.text = this.operationCurrency.toString()
                    var amountSignal = ""
                    this.operationType?.let {
                        if (it==1){
                            amountSignal+="+"
                            tvMoney.setTextColor(Color.parseColor("#44bd32"))
                            tvAmount.setTextColor(Color.parseColor("#44bd32"))
                        }else{
                            amountSignal+="-"
                            tvMoney.setTextColor(Color.parseColor("#DE3618"))
                            tvAmount.setTextColor(Color.parseColor("#DE3618"))
                        }
                    }
                    tvAmount.text = " $amountSignal${FormatMoneyUtil.formatMoney(this.operationAmount)}"
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun updateItems(it: List<DataMotion>) {
        mData = it
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMovementBinding) :
        RecyclerView.ViewHolder(binding.root)
}