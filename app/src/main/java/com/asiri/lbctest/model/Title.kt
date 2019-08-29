package com.asiri.lbctest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity to store all titles from the remote api
 */
@Entity
data class Title (
    @PrimaryKey(autoGenerate = false)
    var id: Int,

    var albumId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String
)