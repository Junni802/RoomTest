package com.example.roomtest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
// Room은 DB에 접근하여 DML(select, insert, update, delete) 쿼리를 실행하는 함수들의 모음
interface RoomMemoDao {
	@Insert(onConflict = REPLACE)
	// onConflict = REPLACE를 적용하면 동일한 키를 가진 값을 입력하면 update 쿼리가 실행됨
	fun insert(memo: RoomMemo)

	@Query("select * from room_memo")
	fun getAll(): List<RoomMemo>
	// RoomMemo형 인스턴스가 들어있는 리스트를 리턴하는 함수

	@Delete
	fun delete(memo: RoomMemo)
}