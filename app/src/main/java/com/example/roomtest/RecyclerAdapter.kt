package com.example.roomtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtest.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
	var listData = mutableListOf<RoomMemo>()
	var helper: RoomHelper? = null
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return Holder(binding)
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		val memo = listData.get(position)
		holder.setMemo(memo)
	}

	override fun getItemCount(): Int {
		return listData.size
	}

	inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
		var mMemo: RoomMemo? = null

		init {
			binding.bntDel.setOnClickListener {
				helper?.roomMemoDao()?.delete(mMemo!!)
				listData.remove(mMemo)
				notifyDataSetChanged()
			}
		}

		fun setMemo(memo: RoomMemo) {
			binding.txtIdx.text = "${memo.idx}"
			binding.txtContent.text = memo.content
			val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
			binding.txtDatetime.text = "${sdf.format(memo.datetime)}"
			this.mMemo = memo
		}
	}
}