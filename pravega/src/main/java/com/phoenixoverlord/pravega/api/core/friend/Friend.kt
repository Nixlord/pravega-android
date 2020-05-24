package com.phoenixoverlord.pravega.api.core.friend

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Friend(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "age") val age: Int
)