package `in`.ram.notesapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Each instance of this class represent a row in the table
@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val contact_id : Int,

    var contact_name : String,

    var contact_email : String,
)
