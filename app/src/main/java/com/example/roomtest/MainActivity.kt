package com.example.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.roomtest.databinding.ActivityMainBinding
import com.example.roomtest.databinding.ItemRecyclerBinding

class MainActivity : AppCompatActivity() {
	val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	var helper: RoomHelper? = null
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo").allowMainThreadQueries().build()
		// Room라이브러리는 시본적으로 서브 쓰레드에서 동작하게 되어 있기 때문에 allowMainThreadQueries()를 사용함

		val adapter = RecyclerAdapter()
		adapter.helper = helper
		adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())
		// adaprer의 listData는 null을 허용하지 않으므로 getAll()로 받아온 값이 null일 경우'?:' 연산자를 이용해 빈 리스트를 넣어줌

		binding.recyclerMemo.adapter = adapter
		binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

		binding.btnSave.setOnClickListener {
			if(binding.editMemo.text.toString().isNotEmpty()){
				val memo = RoomMemo(binding.editMemo.text.toString(), System.currentTimeMillis())
				helper?.roomMemoDao()?.insert(memo)

				adapter.listData.clear()
				adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())
				adapter.notifyDataSetChanged()

				binding.editMemo.setText("")
			}
		}

	}
}