package com.example.roomtest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_memo")
// room 라이브러리에서 @Entity 이노테이션이 적용된 클래스를 찾아 테이블로 변환
// 테이블명과 클래스명을 다르게 하려면 @Entity(tableName = "테이블명")과 같이 작성
class RoomMemo {
// 테이블로 변환될 클래스로 멤버변수들이 컬럼으로 사용됨
// 멤버변수를 컬럼으로 사용하지 않으려면 @ColumInfo 대신 @Ignore 어노테이션을 적용하면 됨
	@PrimaryKey(autoGenerate = true)
	// 기본키 이면서 자동증가값으로 지정

	@ColumnInfo
	var idx: Long? = null
	
	@ColumnInfo
	var content: String = ""
	
	@ColumnInfo
	var datetime: Long? = 0

	constructor(content: String, datetime: Long){
		this.content = content
		this.datetime = datetime
	}
}