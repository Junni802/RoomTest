package com.example.roomtest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
// entities : Room 라이브러리가 사용할 엔티티(테이블) 클래스 목록
// version : 데이터베이스 버전
// exportSchema : true이면 스키마 정보를 파일로 출력
abstract class RoomHelper: RoomDatabase() {
	abstract  fun roomMemoDao(): RoomMemoDao
}