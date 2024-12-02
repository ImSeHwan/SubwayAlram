package com.junseo.subwayalram.databaseutils

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "selected_subway")
data class SelectedSubway(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val stationName: String,
    val lineName: String,
    val latitude: Double,
    val longitude: Double,
    val statnId: String // 역 ID
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString() ?: ""
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SelectedSubway) return false
        return stationName == other.stationName && lineName == other.lineName
    }

    override fun hashCode(): Int {
        return 31 * stationName.hashCode() + lineName.hashCode()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(stationName)
        parcel.writeString(lineName)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(statnId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SelectedSubway> {
        override fun createFromParcel(parcel: Parcel): SelectedSubway {
            return SelectedSubway(parcel)
        }

        override fun newArray(size: Int): Array<SelectedSubway?> {
            return arrayOfNulls(size)
        }
    }
}
